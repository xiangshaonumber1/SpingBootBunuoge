package cn.xiangshao.bunuoge.service;



import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblArticleInfo;

import java.util.Map;

public interface ArticleInfoService {

    /**
     * 新增一篇文章
     */
    ResponseResult add(TblArticleInfo tblArticleInfo);

    /**
     * 删除指定文章
     */
    ResponseResult delete(Map<String, Object> params);

    /**
     * 批量删除文章
     */
    ResponseResult deleteBatch(String[] articleIds);

    /**
     * 修改指定文章或批量文章
     */
    ResponseResult update(Map<String, Object> params);

    /**
     * 批量修改文章活动状态
     */
    ResponseResult updateBatch(String[] articleIds, String status);

    /**
     * 获取指定文章
     */
    ResponseResult get(Map<String, Object> params);

    /**
     * 获取指定文章集合
     */
    ResponseResult findList(Map<String, Object> params);

//    //获取热门文章，待完成
//    public Page<com.personal.myblog.entity.response.ArticleInfo> get_hot_article(){
//      return articleDao.get_hot_article();
//    }
//
//    //获取最新文章列表，待完成
//    public Page<com.personal.myblog.entity.response.ArticleInfo> get_newest_article(){
//        Page<com.personal.myblog.entity.response.ArticleInfo> articleListInfo = articleDao.get_newest_article();
//        if (articleListInfo == null){
//            log.info("没有获取到任何文章信息！");
//            return null;
//        }
//        /*
//         * 遍历ArticleInfoList,为count值不为空的articleInfo赋值like,dislike,watch属性
//         * 如果为空则不做任何处理
//         */
//        for (com.personal.myblog.entity.response.ArticleInfo articleInfo : articleListInfo){
//            ArticleStatusCount count = redisService.getArticleStatusCount(articleInfo.getArticleID());
//            if (count != null){//只有当count不为空时，才为articleInfo设置值，否则不做任何操作（有默认值，为0）
//                articleInfo.setLike(count.getLike())
//                        .setDislike(count.getDislike())
//                        .setWatch(count.getWatch())
//                        .setCollection(count.getCollection());
//            }
//        }
//        return articleListInfo;
//    }
//
//    //获取指定文章ID的基本信息，用户信息和，
//    public com.personal.myblog.entity.response.ArticleInfo get_articleInfo(String articleID){
//        //这里只能获取存放在数据库中的文章的信息
//        com.personal.myblog.entity.response.ArticleInfo articleInfo =  articleDao.get_articleInfo(articleID);
//        if (articleInfo == null) { //如果获取到的数据为空，则直接返回，不用再去获取redis中的count信息
//            log.info("ID为【"+articleID+"】 的文章并不存在！");
//            return null;
//        }
//        //关于文章的喜欢、不喜欢、浏览量等更新比较频繁的信息，需要从redis中获取
//        ArticleStatusCount count = redisService.getArticleStatusCount(articleID);
//        if (count == null){ //如果从redis中获取到count信息为空,则直接返回articleInfo
//            log.info("从redis中获取count内容为空");
//            return articleInfo;
//        }
//
//        //count中watch(浏览量)+1,然后重新存储到redis中
//        count.setWatch(count.getWatch()+1);
//        redisService.saveArticleStatusCount(count);
//
//        //当count信息不为空，则继续赋值，并返回
//        log.info("get_articleInfo 获取到的count 数据："+count);
//        articleInfo.setLike(count.getLike())
//                .setDislike(count.getDislike())
//                .setWatch(count.getWatch())
//                .setCollection(count.getCollection());
//
//        return  articleInfo;
//    }
//
//    //获取指定ID的日记信息
//    public com.personal.myblog.entity.response.DiaryInfo get_diaryInfo(String diaryID){
//        return articleDao.get_diaryInfo(diaryID);
//    }
//
//    //获取指定openID的所有文章
//    public Page<com.personal.myblog.entity.response.ArticleInfo> get_userArticle(String openID){
//        Page<com.personal.myblog.entity.response.ArticleInfo> articleInfoList = articleDao.get_userArticle(openID);
//        //然后去获取文章的数据状态信息
//        for (com.personal.myblog.entity.response.ArticleInfo articleInfo : articleInfoList){
//            ArticleStatusCount count = redisService.getArticleStatusCount(articleInfo.getArticleID());
//            if (count != null){//只有当count不为空时，才为articleInfo设置值，否则不做任何操作（有默认值，为0）
//                articleInfo.setLike(count.getLike())
//                        .setDislike(count.getDislike())
//                        .setWatch(count.getWatch());
//            }
//        }
//        return articleInfoList;
//    }
//
//    //获取指定openID的所有笔记
//    public Page<com.personal.myblog.entity.response.DiaryInfo> get_userDiary(String openID){
//        return articleDao.get_userDiary(openID);
//    }
//
//    //删除指定文章
//    public boolean delete_article(String openID, String deleteID,String type){
//        boolean result = articleDao.delete_article(openID,deleteID,type);
//        //MySQL数据库，删除文章成功，接下来应该删除redis中的ArticleStatusCount，日记并没有记录ArticleStatusCount,所以不需要
//        if (result && type.equals("article")){
//            redisService.deleteRedisArticleInfo(deleteID);
//        }
//        return  result;
//    }
//
//    //发布新文章
//    public int write_article(TblArticleInfo article){
//        return articleDao.write_article(article);
//    }
//
//    //根据article_id,更改现有的文章的信息
//    public int update_article(TblArticleInfo article){
//        return articleDao.update_article(article);
//    }
//
//    //保存新发布的文章的状态信息
//    public void article_save_count(TblArticleInfo article){
//        ArticleStatusCount count = new ArticleStatusCount();
//        count.setArticleID(article.getArticleID());
//        redisService.saveArticleStatusCount(count);
//    }
//
//    //发布新的日记
//    public int write_diary(Diary diary){
//      return articleDao.write_diary(diary);
//    }
//
//    //根据diary_id,更改现有的日记信息
//    public int update_diary(Diary diary){
//        return articleDao.update_diary(diary);
//    }
//
//    //根据关键字对文章进行搜索
//    public Object get_search(int page,String key_word, String type){
//
//        JSONObject data = new JSONObject();
//
//        //保存经过分词器拆分后的结果
//        List key_word_list = CommonUtils.key_split(key_word);
//        data.put("key_word_list",key_word_list);
//
//        //初始化pagehelper
//        PageHelper.startPage(page,10,true,true,true);
//        //对搜索类型进行筛选
//        switch (type){
//            case "article"://用户搜索文章
//                Page<com.personal.myblog.entity.response.ArticleInfo> articleInfoList = articleDao.get_search_articleInfo(key_word_list);
//                //循环获取每个文章的附加信息统计
//                for (com.personal.myblog.entity.response.ArticleInfo articleInfo : articleInfoList){
//                    ArticleStatusCount count = redisService.getArticleStatusCount(articleInfo.getArticleID());
//                    if (count != null){//只有当count不为空时，才为articleInfo设置值，否则不做任何操作（有默认值，为0）
//                        articleInfo.setLike(count.getLike())
//                                .setDislike(count.getDislike())
//                                .setWatch(count.getWatch())
//                                .setCollection(count.getCollection());
//                    }
//                }
//                data.put("total",articleInfoList.getTotal());
//                data.put("result",articleInfoList);
//
//                break;
//            case "user"://用户搜索关键字查找用户
//                Page<UserInfo> userInfoList = articleDao.get_search_userInfo(key_word_list);
//                data.put("total",userInfoList.getTotal());
//                data.put("result",userInfoList);
//                break;
//        }
//        return data;
//    }
//
//    //获取近三日热门推荐文章，emmmmmmm,,,,需要考虑下如何实现
//    public List<com.personal.myblog.entity.response.ArticleInfo> getTop(){
//        return articleDao.getTop();
//    }
//
//    // executeType = false : 用户点赞行为
//    // executeType = true : 取消点赞行为
//    public void clickLike(String articleID,String openID,boolean executeType){
//        ArticleStatusCount count = redisService.getArticleStatusCount(articleID);
//        //如果redis中没有，则初始化
//        if (count==null){
//         count = new ArticleStatusCount();
//         //同时articleID作为redis中的hashkey，必须存在
//         count.setArticleID(articleID);
//        }
//        //
//        if (executeType){
//            log.info("openID为【"+openID+"】的用户正在给articleID为【"+articleID+"】的文章取消点赞");
//            //如果传递过来的value是true，表示用户是在已点赞的状态下再次单击，即视为取消点赞的行为
//            count.setLike(count.getLike()-1);//点赞量-1
//        }else {
//            log.info("openID为【"+openID+"】的用户正在给articleID为【"+articleID+"】的文章取新增点赞");
//            //如果传递过来的value是false，.....则视为点赞行为
//            count.setLike(count.getLike()+1);//点赞量+1
//        }
//        redisService.clickLike(articleID,openID,executeType);//根据executeType 去决定是取消点赞记录，还是新增点赞记录
//        //然后重新写入count信息
//        redisService.saveArticleStatusCount(count);
//    }
//
//    // executeType = true : 即取消收藏行为
//    // executeType = false : 用户收藏行为
//    public void clickCollection(String articleID,String openID, boolean executeType){
//        ArticleStatusCount count = redisService.getArticleStatusCount(articleID);
//        //如果redis中没有，则初始化
//        if (count == null){
//            count = new ArticleStatusCount();
//            //同时articleID作为redis中的hashkey，必须存在
//            count.setArticleID(articleID);
//        }
//        //判断行为
//        if (executeType){
//            //取消收藏的行为
//            log.info("执行取消收藏操作！！！");
//            count.setCollection(count.getCollection()-1);//收藏量+1
//        }else {
//            //收藏行为
//            log.info("执行收藏操作！！！");
//            count.setCollection(count.getCollection()+1);//收藏量+1
//        }
//        redisService.clickCollect(articleID,openID,executeType);//根据executeType 去决定是删除收藏记录，还是新增收藏记录
//        redisService.saveArticleStatusCount(count);
//    }
//
//    //只判断用户对该篇文章是否点过赞，和是否收藏过
//    public Object getLikeAndCollectStatus(String articleID,String openID){
//        return redisService.getLikeAndCollectStatus(articleID,openID);
//    }
//
//    //获取请求用户的粉丝数量和关注的人的数量
//    public JSONObject getFansCountAndMarkedCount(String openID){
//        return redisService.getFansInfoAndMarkedInfo(openID);
//    }
//
//    //根据articleID,去获取当前
//    public Page<CommentAndReplyInfo> getCommentAndReplyInfo(String articleID){
//        return commentAndReplyDao.getCommentAndReplyInfo(articleID);
//    }
//
//    //为指定文章新增一条评论
//    public boolean write_comment(TblCommentInfo comment){
//        return commentAndReplyDao.write_comment(comment);
//    }
//
//    //为指定文章下的评论（或评论下的回复），新增一条回复信息
//    public boolean write_reply(TblReplyInfo reply){
//        return commentAndReplyDao.write_reply(reply);
//    }

}

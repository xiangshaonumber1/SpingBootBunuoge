package cn.xiangshao.bunuoge.controller;

import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblArticleInfo;
import cn.xiangshao.bunuoge.service.ArticleInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/article")
@Api(value = "ArticleController",tags = {"文章类相关操作"})
public class ArticleInfoController {

    @Autowired
    ArticleInfoService articleInfoService;

    @ApiOperation("新增文章")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody TblArticleInfo tblArticleInfo){
        return articleInfoService.add(tblArticleInfo);
    }

    @ApiOperation("删除指定文章")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody Map<String,Object> params){
        return articleInfoService.delete(params);
    }

    @ApiOperation("修改指定文章或文章集合")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Map<String,Object> params){
        return articleInfoService.update(params);
    }

    @ApiOperation("获取指定文章信息")
    @GetMapping("/get")
    public ResponseResult get(@RequestParam Map<String,Object> params){
        return articleInfoService.get(params);
    }

    @ApiOperation("获取指定文章集合")
    @GetMapping("/findList")
    public ResponseResult findList(@RequestParam Map<String,Object> params){
        return articleInfoService.findList(params);
    }


//    @ApiOperation(value = "获取首页最新文章列表信息")
//    @GetMapping("/get_article_list")
//    public Object get_article_list(@ApiParam(required = true,value = "页号")@RequestParam(defaultValue = "1") int page,
//                                   @ApiParam(required = true,value = "每页数量")@RequestParam(defaultValue = "5") int pageSize) {
//        PageHelper.startPage(page,pageSize);
//        Page<com.personal.myblog.entity.response.ArticleInfo> newest_article = articleService.get_newest_article();
//        JSONObject data = new JSONObject();
//        //如果没有获取到数据，返回的不是null,而是[]，所以必须加上数组长度的判断
//        data.put("total",newest_article.getTotal());
//        data.put("newest_article",newest_article);
//        return MessageData.Success().setData(data);
//    }
//
//
//    @ApiOperation("用户发布文章")
//    @PostMapping("/write_article")
//    @RequiresAuthentication //表示必须经过登录才允许接入该接口
//    public Object write_article(HttpServletRequest request,
//                                @ApiParam(value = "文章标题", required = true) @RequestParam String title,
//                                @ApiParam(value = "文章内容", required = true) @RequestParam String content,
//                                @ApiParam(value = "文章类型", required = true) @RequestParam String type,
////                                @ApiParam(value = "文章标签", required = true) @RequestParam String label,
//                                @ApiParam(value = "原文链接") @RequestParam(required = false) String origin_link) {
//
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//
//        TblArticleInfo article = new TblArticleInfo();
//        article.setOpenID(openID).setTitle(title).setContent(content).setType(type).setStatus("待审核");
//
//        if (StringUtils.isNotBlank(origin_link)){
//            article.setOriginLink(origin_link);
//        }
//        //保存文章到数据库
//        articleService.write_article(article);
//        //如果发布成功，保存文章状态信息
//        articleService.article_save_count(article);
//        JSONObject data = new JSONObject();
//        data.put("articleID",article.getArticleID());
//        data.put("authorID",article.getOpenID());
//        //由系统为粉丝发起新文章推送通知
//        nettySocketIoService.notification_article(openID);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("修改以及发布的文章")
//    @PostMapping("/update_article")
//    @RequiresAuthentication
//    public Object update_article(HttpServletRequest request,
//                                 @ApiParam(value = "文章ID", required = true) @RequestParam String articleID,
//                                 @ApiParam(value = "文章标题", required = true) @RequestParam String title,
//                                 @ApiParam(value = "文章内容", required = true) @RequestParam String content,
//                                 @ApiParam(value = "文章类型", required = true) @RequestParam String type,
////                               @ApiParam(value = "文章标签", required = true) @RequestParam String label,
//                                 @ApiParam(value = "原文链接") @RequestParam(required = false) String origin_link){
//
//        TblArticleInfo article = new TblArticleInfo();
////        JSONArray filepath = null;
//
//        //首先判断是否有文件上传,这里有个BUG,如果body中有 form-data上传，但是只有key，value为空，抛出空指针
////        if (request.getContentType().contains("multipart/form-data")) {
////            log.info("有文件上传");
////            filepath = new UpLoadFilesUtil().start_upload(request, Status.resources_article_picture);
////        }
////
////        if (filepath != null) {
////            article.setPictureCover(String.valueOf(filepath));
////        }
//        //初始化值
//        article.setArticleID(articleID).setTitle(title).setContent(content).setType(type).setStatus("待审核");
//        if (StringUtils.isNotBlank(origin_link)){
//            article.setOriginLink(origin_link);
//        }
//        int result = articleService.update_article(article);
//        return MessageData.Success().setStatus(result>0);
//    }
//
//    //该接口在只会在文章详情页面调用，如果调用该接口，表示一定是浏览了该篇文章，所以在这里接口里，增加浏览量
//    @ApiOperation("根据文章ID（articleID）获取相应的文章信息")
//    @GetMapping("/get_articleInfo")
//    public Object get_articleInfo(@ApiParam(value = "文章ID",required = true)@RequestParam String articleID){
//        com.personal.myblog.entity.response.ArticleInfo articleInfo = articleService.get_articleInfo(articleID);
//        return MessageData.Success().setData(articleInfo);
//    }
//
//
//    @ApiOperation("根据用户openID获取该用户所写的文章信息")
//    @GetMapping("/get_userArticle")
//    public Object get_userArticle(HttpServletRequest request, @ApiParam(required = true,value = "用户openID")@RequestParam String openID,
//                                  @ApiParam(required = true,value = "页号")@RequestParam(defaultValue = "1")int page, @ApiParam(required = true,value = "每页数量")@RequestParam(defaultValue = "10")int pageSize){
//
//        //获取token,然后从token中获取openID
//        PageHelper.startPage(page,pageSize);
//        Page<com.personal.myblog.entity.response.ArticleInfo> articleInfoList = articleService.get_userArticle(openID);
//        JSONObject data = new JSONObject();
//        data.put("total",articleInfoList.getTotal());
//        data.put("articleInfoList",articleInfoList);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("发布新的日记信息")
//    @PostMapping("/write_diary")
//    @RequiresAuthentication //表示必须经过登录才允许接入该接口
//    public Object write_diary(HttpServletRequest request,
//                              @ApiParam(required = true,value = "日记标题")@RequestParam String title,
//                              @ApiParam(required = true,value = "日记内容")@RequestParam String content,
//                              @ApiParam(required = true,value = "日记类型")@RequestParam String type,
//                              @ApiParam(value = "标签")@RequestParam(required = false)String label){
//        //从request中获取token,然后jwt解码，获取openID
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        Diary diary = new Diary();
//        diary.setOpenID(openID).setTitle(title).setContent(content).setType(type);
//        if (StringUtils.isNotBlank(label)){
//            diary.setLabel(label);
//        }
//        //保存到数据库
//        articleService.write_diary(diary);
//        return MessageData.Success().setData(diary.getDiaryID());
//    }
//
//
//    @ApiOperation("修改日记内容并重新发布")
//    @PostMapping("/update_diary")
//    @RequiresAuthentication
//    public Object update_diary(HttpServletRequest request,
//                               @ApiParam(required = true,value = "日记ID")@RequestParam String diaryID,
//                               @ApiParam(required = true,value = "日记标题")@RequestParam String title,
//                               @ApiParam(required = true,value = "日记内容")@RequestParam String content,
//                               @ApiParam(required = true,value = "日记类型")@RequestParam String type,
//                               @ApiParam(value = "标签")@RequestParam(required = false)String label){
//        Diary diary = new Diary();
//        diary.setDiaryID(diaryID).setTitle(title).setContent(content).setType(type);
//        if (StringUtils.isNotBlank(label)){
//            diary.setLabel(label);
//        }
//        //修改数据库数据
//        articleService.update_diary(diary);
//        return MessageData.Success().setData(diary.getDiaryID());
//    }
//
//
//    @ApiOperation("根据日记ID(diaryID)获取相应的日记信息")
//    @GetMapping("/get_diaryInfo")
//    public Object get_diaryInfo(@ApiParam(required = true,value = "日记ID")@RequestParam String diaryID){
//        com.personal.myblog.entity.response.DiaryInfo diaryInfo = articleService.get_diaryInfo(diaryID);
//        return MessageData.Success().setData(diaryInfo);
//    }
//
//
//    @ApiOperation("根据用户ID,获取该用户所写的日记信息")
//    @GetMapping("/get_userDiary")
//    public Object get_userDiary(HttpServletRequest request,
//                                @ApiParam(required = true,value = "用户openID")@RequestParam String openID,
//                                @ApiParam(required = true,value = "页号")@RequestParam(defaultValue = "1") int page,
//                                @ApiParam(required = true,value = "每页总量")@RequestParam(defaultValue = "10") int pageSize){
//        PageHelper.startPage(page,pageSize);
//        Page<com.personal.myblog.entity.response.DiaryInfo> diaryInfoList = articleService.get_userDiary(openID);
//        JSONObject data = new JSONObject();
//        data.put("total",diaryInfoList.getTotal());
//        data.put("diaryInfoList",diaryInfoList);
//        return MessageData.Success().setData(data);
//    }
//
//
//    @ApiOperation("删除指定的类型的文章(日记)")
//    @PostMapping("/delete_article")
//    public Object delete(HttpServletRequest request,
//                         @ApiParam(required = true, value = "需要删除的文章(日记)的ID") @RequestParam String deleteID,
//                         @ApiParam(required = true, value = "删除类型", allowableValues = "article,diary") @RequestParam String type) {
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        boolean resultStatus = articleService.delete_article(openID,deleteID,type);
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("按照输入的关键字对文章进行搜索和筛选，返回符合的")
//    @GetMapping("/get_search")
//    public Object get_search(@ApiParam(value = "当前页数", required = true) @RequestParam(defaultValue = "1") int page,
//                                 @ApiParam(value = "关键字", required = true) @RequestParam String key_word,
//                                 @ApiParam(value = "搜索类型",required = true)@RequestParam(defaultValue = "article")String type) {
//        JSONObject data = (JSONObject) articleService.get_search(page,key_word,type);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("获取近三日热门推荐文章(待完成)")
//    @GetMapping("/getTop")
//    public Object getTop() {
//        List<com.personal.myblog.entity.response.ArticleInfo> articleInfoList = articleService.getTop();
//        return null;
//    }
//
//
//    @ApiOperation("文章点赞/取消点赞")
//    @PostMapping("/clickLike")
//    public Object clickLike(@ApiParam(required = true,value = "文章ID")@RequestParam String articleID, @ApiParam(required = true,value = "用户openID")@RequestParam String openID,
//                            @ApiParam(required = true,value = "当前点赞前状态")@RequestParam boolean executeType){
//        articleService.clickLike(articleID,openID,executeType);
//        //返回 !executeType, 表示执行后的状态,false 则表示已取消点赞,true表示点赞成功
//        return MessageData.Success().setStatus(!executeType);
//    }
//
//    @ApiOperation("文章收藏/取消收藏")
//    @PostMapping("/clickCollect")
//    public Object clickCollect(@ApiParam(required = true,value = "文章ID")@RequestParam String articleID, @ApiParam(required = true,value = "用户openID")@RequestParam String openID,
//                               @ApiParam(required = true,value = "当前收藏状态")@RequestParam boolean executeType){
//        articleService.clickCollection(articleID,openID,executeType);
//        //返回 !executeType, 表示执行后的状态,false 则表示已取消收藏,true表示收藏成功
//        return MessageData.Success().setStatus(!executeType);
//    }
//
//    @ApiOperation("判断用户是否对该篇文章进行有点赞记录或者收藏记录")
//    @GetMapping("/getLikeAndCollectStatus")
//    public Object getLikeAndCollectStatus(@ApiParam(required = true,value = "文章ID")@RequestParam String articleID, @ApiParam(required = true,value = "用户openID")@RequestParam String openID){
//        JSONObject data = (JSONObject) articleService.getLikeAndCollectStatus(articleID,openID);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("获取指定ID的文章的评论和评论下的回复信息")
//    @GetMapping("/getCommentAndReplyInfo")
//    public Object getCommentAndReplyInfo(@ApiParam(required = true,value = "指定评论和回复的articleID")@RequestParam String articleID, @ApiParam(required = true,value = "页数")@RequestParam(defaultValue = "1") int page,
//                                         @ApiParam(required = true,value = "每页数量")@RequestParam(defaultValue = "6") int pageSize){
//        PageHelper.startPage(page,pageSize);
//        Page<CommentAndReplyInfo> commentAndReplyInfoList = articleService.getCommentAndReplyInfo(articleID);
//        JSONObject data = new JSONObject();
//        data.put("total",commentAndReplyInfoList.getTotal());
//        data.put("commentAndReplyInfoList",commentAndReplyInfoList);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("为指定文章新增一条评论信息")
//    @PostMapping("/write_comment")
//    public Object write_comment(HttpServletRequest request,
//                                @ApiParam(required = true,value = "目标文章ID")@RequestParam String articleID, @ApiParam(required = true,value = "作者ID")@RequestParam String authorID,
//                                @ApiParam(required = true,value = "发起者openID")@RequestParam String from_openID, @ApiParam(required = true,value = "评论文章内容")@RequestParam String comment_content){
//        TblCommentInfo comment = new TblCommentInfo()
//                .setArticleID(articleID)
//                .setAuthorID(authorID)
//                .setFrom_openID(from_openID)
//                .setComment_content(comment_content);
//        boolean resultStatus = articleService.write_comment(comment);
//        if (resultStatus){
//            //评论添加成功，则为作者发送一条新评论消息通知
//            nettySocketIoService.notification_commentAndReply(from_openID,authorID,comment_content,"comment");
//        }
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("为指定文章下的评论(或评论下的回复)，新增一条回复信息")
//    @PostMapping("/write_reply")
//    public Object write_reply(HttpServletRequest request,
//                                @ApiParam(required = true,value = "目标文章ID")@RequestParam String articleID, @ApiParam(required = true,value = "目标评论ID")@RequestParam String commentID,
//                                @ApiParam(required = true,value = "发起者openID")@RequestParam String from_openID, @ApiParam(required = true,value = "回复内容详情")@RequestParam String reply_content,
//                                @ApiParam(required = true,value = "回复目标openID")@RequestParam String to_openID){
//        TblReplyInfo reply = new TblReplyInfo()
//                .setArticleID(articleID)
//                .setCommentID(commentID)
//                .setFrom_openID(from_openID)
//                .setReply_content(reply_content)
//                .setTo_openID(to_openID);
//        boolean resultStatus = articleService.write_reply(reply);
//        if (resultStatus){
//            //回复添加成功，则为回复目标发送一条新回复消息通知
//            nettySocketIoService.notification_commentAndReply(from_openID,to_openID,reply_content,"reply");
//        }
//        return MessageData.Success().setStatus(resultStatus);
//    }

}
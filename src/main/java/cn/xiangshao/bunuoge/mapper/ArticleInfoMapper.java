package cn.xiangshao.bunuoge.mapper;


import cn.xiangshao.bunuoge.entity.request.TblArticleInfo;
import cn.xiangshao.bunuoge.entity.response.TblArticleInfoResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

public interface ArticleInfoMapper extends Mapper<TblArticleInfo, TblArticleInfoResult>{

    @Override
    @Options(keyColumn = "article_id",keyProperty = "articleId")
    @Insert("insert into tbl_article_info(open_id,title,content,type,label,creat_time,status) " +
            "values(#{openId},#{title},#{content},#{type},#{label},#{creatTime},#{status})")
    void add(TblArticleInfo entity);

    //批量删除指定文章
    void deleteBatch(@Param("articleIds") String[] ids);

    //批量修改状态
    void updateBatch(@Param("articleIds") String[] ids, @Param("status") String status);

    //
//    //获取所有文章
////    @Select("SELECT user.nickname,user.usericon,article.* FROM article,user WHERE user.openID = article.openID ")
////    List<ArticleInfo> getAllArticle();
//
//    //发布新文章
//    @Insert("INSERT INTO article(openID,pictureCover,title,content,type,originLink,label,status) " +
//            "VALUES(#{openID},#{pictureCover},#{title},#{content},#{type},#{originLink},#{label},#{status})")
//    @Options(useGeneratedKeys = true,keyProperty = "articleID",keyColumn = "articleID")
//    int write_article(TblArticleInfo article);
//
//    //修改新的文章
//    @Update("UPDATE article SET title=#{title},content=#{content},type=#{type},originLink=#{originLink},label=#{label} WHERE articleID=#{articleID}")
//    int update_article(TblArticleInfo article);
//
//    //发布新日记
//    @Insert("INSERT INTO diary(openID,title,content,type,label) VALUES (#{openID},#{title},#{content},#{type},#{label})")
//    @Options(useGeneratedKeys = true,keyProperty = "diaryID",keyColumn = "diaryID")
//    int write_diary(Diary diary);
//
//    //发布新日记
//    @Update("UPDATE diary SET title=#{title},content=#{content},type=#{type},label=#{label} WHERE diaryID=#{diaryID}")
//    int update_diary(Diary diary);
//
//    //删除指定文章
//    @DeleteProvider(type = SqlProvider.class,method = "delete_article")
//    boolean delete_article(String openID,String deleteID,String type);
//
//
//    //搜索满足条件的文章
//    @SelectProvider(type = SqlProvider.class,method = "get_search_articleInfo")
//    Page<com.personal.myblog.entity.response.ArticleInfo> get_search_articleInfo(@Param("key_value_list") List key_value_list);
//
//    //搜索满足条件的用户
//    @SelectProvider(type = SqlProvider.class,method = "get_search_userInfo")
//    Page<UserInfo> get_search_userInfo(@Param("key_value_list") List key_value_list);
//
//    //查找指定ID的文章信息
//    @Select("SELECT user.nickname,user.userIcon,article.* FROM article,user WHERE user.openID = article.openID AND article.articleID = #{articleID} AND article.status <> '不通过'")
//    com.personal.myblog.entity.response.ArticleInfo get_articleInfo(@Param("articleID") String articleID);
//
//    //查询指定ID的日记信息
//    @Select("SELECT user.nickname,user.userIcon,diary.* FROM diary,user WHERE user.openID = diary.openID AND diary.diaryID = #{diaryID}")
//    com.personal.myblog.entity.response.DiaryInfo get_diaryInfo(@Param("diaryID")String diaryID);
//
//    //查询指定ID的用户的所有文章列表
//    @Select("SELECT * FROM article WHERE article.openID = #{openID} AND status <> '不通过'")
//    Page<com.personal.myblog.entity.response.ArticleInfo> get_userArticle(@Param("openID")String openID);
//
//    //查询指定ID的用户的所有日记信息列表
//    @Select("SELECT * FROM diary WHERE diary.openID = #{openID}")
//    Page<com.personal.myblog.entity.response.DiaryInfo> get_userDiary(@Param("openID")String openID);
//
//    //最新文章
//    @Select("SELECT user.nickname,user.userIcon,article.* FROM article,user WHERE user.openID = article.openID AND status <> '不通过' ORDER BY time DESC")
//    Page<com.personal.myblog.entity.response.ArticleInfo> get_newest_article();
//
//    //推荐文章,按照热度(这里即为浏览量)，待完成
//    @Select("")
//    Page<com.personal.myblog.entity.response.ArticleInfo> get_hot_article();
//
//    //在用户尚未进行喜欢或差评的操作情况下，新增用户对指定文章的态度
////    @Insert("INSERT INTO article_like_and_dislike(articleID,openID,state) VALUES(#{articleID},#{openID},#{state})")
////    void insert_like_or_dislike(@Param("articleID") String articleID,
////                                @Param("openID") String openID,
////                                @Param("state") String state);
//
//    //在用户已经对该篇文章表态的情况下，进行修改
////    @Update("UPDATE article_like_and_dislike SET state = #{state} WHERE openID=#{openID} AND articleID=#{articleID}")
////    void update_like_or_dislike( @Param("articleID") String articleID,
////                                 @Param("openID") String openID,
////                                 @Param("state")String state);
//
//    //获取近三日热门文章
//    @Select("SELECT")
//    List<com.personal.myblog.entity.response.ArticleInfo> getTop();

}
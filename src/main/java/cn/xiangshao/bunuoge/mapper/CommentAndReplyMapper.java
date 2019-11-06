package cn.xiangshao.bunuoge.mapper;//package com.personal.myblog.mapper;
//
//import com.github.pagehelper.Page;
//import com.personal.myblog.entity.request.TblCommentInfo;
//import com.personal.myblog.entity.request.TblReplyInfo;
//import com.personal.myblog.entity.response.CommentAndReplyInfo;
//import org.apache.ibatis.annotations.*;
//
///**
// * @author xiangshao
// * @date 2019/3/21 11:11
// * @describe 评论和回复相关的dao操作
// * property：对应实体类中的参数名称
// * column：需要往子表传递的字段
// * javaType：该参数类型
// * ofType：该参数泛型
// * select：子查询id名称
// */
//public interface CommentAndReplyMapper {
//
//    //查询指定文章的评论和评论和评论下的回复信息
//    @Select("SELECT A.*,B.nickname AS from_nickname,B.userIcon AS from_userIcon FROM article_comment A " +
//            "LEFT JOIN user B ON (A.from_openID = B.openID)" +
//            "WHERE A.articleID = #{articleID} ORDER BY comment_time DESC ")
//    @Results({
//            @Result(id = true,column = "commentID",property = "commentID"),//加上这一句的目的是为了把commentID传递给下一个方法时，避免被消费掉
//            @Result(property ="replyInfoList" ,column = "commentID", many = @Many(select = "getReplyInfo"))
//    })
//    Page<CommentAndReplyInfo> getCommentAndReplyInfo(@Param("articleID") String articleID);
//
//    //查询评论表中指定commentID的的所有回复信息
//    @Select("SELECT A.*, B.nickname AS from_nickname, C.nickname AS to_nickname FROM comment_reply A " +
//            " LEFT JOIN user B ON (A.from_openID = B.openID)" +
//            " LEFT JOIN user C ON (A.to_openID = C.openID)" +
//            " WHERE commentID = #{commentID} ORDER BY reply_time ASC")
//    Page<com.personal.myblog.entity.response.ReplyInfo> getReplyInfo(@Param("commentID") String commentID);
//
//    //为指定文章新增一条评论
//    @Insert("INSERT INTO article_comment(articleID,authorID,from_openID,comment_content) " +
//            "VALUES(#{articleID},#{authorID},#{from_openID},#{comment_content})")
//    boolean write_comment(TblCommentInfo comment);
//
//    //为指定文章下的评论（或评论下的回复），新增一条回复信息
//    @Insert("INSERT INTO comment_reply(articleID,commentID,from_openID,reply_content,to_openID) " +
//            "VALUES(#{articleID},#{commentID},#{from_openID},#{reply_content},#{to_openID})")
//    boolean write_reply(TblReplyInfo reply);
//}

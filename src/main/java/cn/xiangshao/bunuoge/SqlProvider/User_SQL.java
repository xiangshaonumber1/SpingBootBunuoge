package cn.xiangshao.bunuoge.SqlProvider;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author xiangshao
 * @date 2019/5/22 9:42
 * @describe
 */
@Slf4j
public class User_SQL {

    //获取执行类型的消息详情
    public String getReplyMessageDetails(String openID){
        String sql_1 = new SQL(){
            {
                //如果是comment类型消息 文章 --> 评论
                SELECT("A.commentID as messageID, A.from_openID, B.nickname as from_nickname, A.comment_content as content, C.title as aim_content, null as to_openID, null as to_nickname, A.comment_time as time,'comment' as type");
                FROM("article_comment A");
                LEFT_OUTER_JOIN(" user B ON (A.authorID = B.openID) ");
                LEFT_OUTER_JOIN(" article C ON (C.openID = A.authorID and C.articleID = A.articleID) ");
                WHERE(" A.authorID = #{openID}");
            }
        }.toString();
        String sql_2 = new SQL(){
            {
                //如果是reply类型消息,①评论-->回复 / ②回复-->回复(虽然不太恰当，但还是给评论的发起者发消息)
                //注意，这里使用了unino all
                SELECT("A.replyID as messageID, A.from_openID, B.nickname as from_nickname, A.reply_content as content, C.comment_content as aim_content, A.to_openID, D.nickname as to_nickname, A.reply_time as time, 'reply' as type");
                FROM(" comment_reply A ");
                LEFT_OUTER_JOIN(" user B ON (A.from_openID = B.openID) ");
                LEFT_OUTER_JOIN(" article_comment C ON (C.commentID = A.commentID and C.articleID = A.articleID) ");
                LEFT_OUTER_JOIN(" user D ON (A.to_openID = D.openID) ");
                WHERE(" A.to_openID = #{openID} ");
                ORDER_BY("time desc");
            }
        }.toString();
        String sql = sql_1 +" UNION ALL "+ sql_2;
        log.info("执行SQL：\n"+sql);
        return  sql;
    }
}

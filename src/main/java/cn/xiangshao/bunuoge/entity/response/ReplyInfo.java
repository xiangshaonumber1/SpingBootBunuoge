package cn.xiangshao.bunuoge.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xiangshao
 * @date 2019/3/21 11:40
 * @describe
 */
@Data
@Accessors(chain=true) //支持链式set
public class ReplyInfo implements Serializable {
    String replyID;         //自增长主键ID
    String articleID;       //上上级，该条回复信息是哪篇文章下的
    String commentID;       //关联的评论表的ID
    String from_openID;     //回复发起的用户的ID
    String from_nickname;   //回复发起的用户的昵称
    String reply_content;   //回复的具体内容
    String to_openID;       //回复对象的ID
    String to_nickname;     //回复对象的昵称
    String reply_time;      //回复发起的时间
}

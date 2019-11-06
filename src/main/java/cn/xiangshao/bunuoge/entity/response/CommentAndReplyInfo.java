package cn.xiangshao.bunuoge.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiangshao
 * @date 2019/3/21 10:43
 * @describe 后台返回给前端的评论和评论之下的回复信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true) //支持链式set
public class CommentAndReplyInfo implements Serializable {
    String commentID;       //自增长主键ID
    String articleID;       //关联article表的主键ID
    String from_openID;     //评论发布者ID
    String from_nickname;   //评论发布者昵称
    String from_userIcon;   //评论发布者用户头像
    String comment_content; //评论内容
    String comment_time;    //评论时间
    List<ReplyInfo> replyInfoList;  //该篇评论的所有的回复信息
}

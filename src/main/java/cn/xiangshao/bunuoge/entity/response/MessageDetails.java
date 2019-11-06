package cn.xiangshao.bunuoge.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xiangshao
 * @date 2019/5/22 9:22
 * @describe 消息详情
 */
@Data
@Accessors(chain = true)
public class MessageDetails implements Serializable {
    String messageID; //消息编号
    String from_openID; //发起者openID
    String from_nickname;   //发起者昵称
    String aim_content; //回复目标消息内容,如果是查询comment类型消息，则为文章标题；如果是查询reply类型消息，则为评论具体内容
    String to_openID;   //回复目标openID（用于 回复->回复 类型）
    String to_nickname; //回复目标昵称（用于 回复->回复 类型）
    String content; //发布消息详情
    String time;    //评论发布时间
    String type;    //消息类型是评论还是回复
}

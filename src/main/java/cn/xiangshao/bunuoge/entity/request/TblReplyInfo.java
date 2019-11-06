package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * replyInfo 评论回复信息 实体类
 */
@Data
@Accessors(chain = true)
public class TblReplyInfo implements Serializable {
    String replyId;
    String commentId;
    String formId;
    String from_content;
    String to_id;
    String creatTime;
    String status;
}

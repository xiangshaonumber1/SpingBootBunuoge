package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *  CommentInfo 文章评论信息 实体类
 */
@Data
@Accessors(chain = true)
public class TblCommentInfo implements Serializable {
    String commentId;
    String articleId;
    String formId;
    String content;
    String creatTime;
    String status;
}

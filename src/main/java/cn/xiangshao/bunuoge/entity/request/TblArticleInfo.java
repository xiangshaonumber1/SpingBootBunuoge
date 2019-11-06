package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * ArticleInfo 文章信息 实体类
 */

@Data
@Accessors(chain=true)
public class TblArticleInfo implements Serializable {
    String articleId;
    String openId;
    String title;
    String content;
    String type;
    String label;
    String creatTime;
    String status;
}
package cn.xiangshao.bunuoge.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 文章的状态信息统计
 */
@Data
@Accessors(chain = true)
public class ArticleInfoCountResult implements Serializable {
    String articleId;
    String type;
    int watchCount;
    int collectionCount;
    int likeCount;
}

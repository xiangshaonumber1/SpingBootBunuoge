package cn.xiangshao.bunuoge.entity.response;


import cn.xiangshao.bunuoge.entity.request.TblArticleInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *  文章基本的主体信息
 *  文章的流量统计
 *  作者昵称和头像
 */


@Data
@Accessors(chain = true)
public class TblArticleInfoResult extends TblArticleInfo implements Serializable {
    String nickname;
    String avatar;
}

package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * LinkInfo 链接信息 实体类
 */
@Data
@Accessors(chain = true)
public class TblLinkInfo implements Serializable {
    String id;
    String type;
    String describe;
    String website;
    String creatTime;
}

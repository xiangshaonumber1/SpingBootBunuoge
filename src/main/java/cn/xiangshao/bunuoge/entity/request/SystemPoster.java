package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SystemPoster 轮播海报信息
 */
@Data
@Accessors(chain = true)
public class SystemPoster implements Serializable {
    String id;
    String posterSrc;
}

package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * SystemMessage 站内系统消息 实体类
 */
@Data
@Accessors(chain = true)
public class SystemMessage implements Serializable {
    String id;
    String senderId;
    String title;
    String content;
    String creatTime;
}

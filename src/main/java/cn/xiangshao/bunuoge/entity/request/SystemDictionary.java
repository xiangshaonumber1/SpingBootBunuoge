package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * SystemDictionary 数据字典实体类
 */
@Data
@Accessors(chain = true)
public class SystemDictionary implements Serializable {
    String id;
    String typeName;
    String type;
    String describe;
    String value;
}

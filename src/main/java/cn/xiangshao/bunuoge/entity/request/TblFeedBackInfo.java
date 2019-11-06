package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * FeedBack 回馈信息 实体类
 */
@Data
@Accessors(chain = true)
public class TblFeedBackInfo implements Serializable {
    String feedId;
    String openId;
    String content;
    String contactType;
    String contactInfo;
    String userType;
    String dealTime;
    String creatTime;
    String status;
}

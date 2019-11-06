package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Account 账号信息 实体类
 */
@Data
@Accessors(chain = true)
public class TblAccountInfo implements Serializable {
    String openId;
    String username;
    String encryptPassword;
    String salt;
    String status;
    String role;
    String permission;
    String creatTime;
}
package cn.xiangshao.bunuoge.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * UserInfo 用户信息 实体类
 */
@Data
@Accessors(chain = true)
public class TblUserInfo implements Serializable {
    String openId;
    String nickname;
    String gender;
    String avatar;
    String email;
    String phone;
    String myDescribe;
    String wishDescribe;
}

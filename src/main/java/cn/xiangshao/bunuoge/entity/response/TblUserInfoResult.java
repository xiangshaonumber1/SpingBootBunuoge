package cn.xiangshao.bunuoge.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class TblUserInfoResult implements Serializable {
    String openId;
    String username;
    String nickname;
    String status;
    String role;
    String permission;
    String creatTime;
    String gender;
    String email;
    String phone;
    String avatar;
}

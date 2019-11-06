package cn.xiangshao.bunuoge.entity.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminRole implements Serializable {
    private int id;
    private String openId;
    private String role;
    private String permission;
}

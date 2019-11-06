package cn.xiangshao.bunuoge.config.shiro;

import org.apache.shiro.authc.HostAuthenticationToken;

/**
 * 创建JwtToken 替换 Shrio 原生 token
 * 1. Shiro原生的token中存在用户名和密码以及其他信息【验证码，记住我】
 * 2. 在JWT的Token中因为已将用户名和密码通过加密处理整合到一个加密串中，所以只需要一个token字段即可
 */

public class JWTToken implements HostAuthenticationToken {
    //密钥
    private String token;
    private String host;

    public JWTToken(String token) {
        this(token, null);
    }

    private JWTToken(String token, String host) {
        this.token = token;
        this.host = host;
    }
    public String getToken(){
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public String getHost() {
        return null;
    }
}

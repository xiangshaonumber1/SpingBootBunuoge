package cn.xiangshao.bunuoge.config.shiro;


import cn.xiangshao.bunuoge.Utils.JwtUtil;
import cn.xiangshao.bunuoge.Utils.Status;
import cn.xiangshao.bunuoge.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class UserShiroRealm extends AuthorizingRealm {

    @Autowired
    private RedisService redisService;

    /** ************ 划重点 ****************
     * 使用JWT替代原生Token，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    /** 认证,即登录验证
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可
     * 这一步我们根据token给的用户名，去数据库查出加密过用户密码，然后把加密后的密码和盐值一起发给shiro，让它做比对
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException{
        String token = (String) authenticationToken.getCredentials();
        String openId = JwtUtil.getOpenId(token);
        String salt = redisService.getSalt(openId);
        //第一步：先判断token是否是安全的token
        if (JwtUtil.verifyTokenSecure(token,salt)){
            //第二步：判断token是否还在有效期
            if (JwtUtil.verifyTokenExpired(token)){
                //token合法，且在有效期内,无需任何刷新操作
                return new SimpleAuthenticationInfo(token,token,getName());
            }else {
                //token合法，但不在有效期内，返回需要刷新提示
                throw new AuthenticationException(String.valueOf(Status.code_token_expired));
            }
        }else {
            //token不合法，返回非法信息
            throw new AuthenticationException(String.valueOf(Status.code_token_error));
        }
    }


    /** 授权，登录成功之后，进行角色的权限验证
     *  角色授权和对应权限添加
     *  只有当需要检测用户权限的时候采用调用此方法，例如checkRole，checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        String token = (String) principals.getPrimaryPrincipal();
//        String openID = JwtUtil.getopenID(token);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        //获取用户的权限
//        RolePermission rolePermission = userService.getRoleAndPermission(openID);
//        //添加角色
//        info.addRole(rolePermission.getRole());
//        //添加权限
//        try {
//            JSONArray permissionArray = JSON.parseArray(rolePermission.getPermission());
//            for (int i = 0; i < permissionArray.size(); i++) {
//                info.addStringPermission(permissionArray.getString(i));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //返回添加权限和角色的信息
//        return info;
        return null;
    }

}

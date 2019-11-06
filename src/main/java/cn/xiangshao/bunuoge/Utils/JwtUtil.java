package cn.xiangshao.bunuoge.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.util.Calendar;
import java.util.Date;

/**
 * jwt工具类
 * 在这个工具类中可以实现对用户名和密码加密处理，检验token是否正确，获取用户名等操作
 * Algorithm algorithm = Algorithm.HMAC256(password) 是对密码进行加密后再与用户名混淆在一起
 * 在签名时可以通过.withExpiresAt(date)指定token过期时间
 */
@Slf4j
public class JwtUtil {

    //Token 有效期，默认 30 分钟
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 获得token中的信息无需secret解密也能获得
     */
    public static Date getIssuedAt(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getIssuedAt();
    }

    /**
     * 获取openID
     */
    public static String getOpenId(String token){
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("openId").asString();
    }

    /**
     * 生成签名,token
     */
    public static String generateToken(String openId,String salt){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //这里使用password+salt混淆加密
        Algorithm algorithm = Algorithm.HMAC256(salt);
        //返回结果(token)
        return JWT.create()
                .withClaim("openId",openId)
                .withExpiresAt(date)//到期时间
                .withIssuedAt(new Date())
                .sign(algorithm);//创建一个新的JWT，并使用给定的算法进行标记
    }

    /**
     * 检验token是否过期
     */
    public static boolean verifyTokenExpired(String token){
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 校验 token 是否合法
     */
    public static boolean verifyTokenSecure(String token,String salt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(salt);
            //在token中附带了username信息
            String openID = getOpenId(token);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("openId", openID)
                    .build();
            verifier.verify(token);
            return true;
        }catch (Exception e){
            log.error(CommonUtils.GetTrace(e));
            return false;
        }
    }

    /**
     * 生成随机盐,长度32位
     */
    public static String generateSalt(){
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(16).toHex();
    }

}

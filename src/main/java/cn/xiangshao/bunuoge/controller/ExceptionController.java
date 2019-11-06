package cn.xiangshao.bunuoge.controller;

import cn.xiangshao.bunuoge.Utils.CommonUtils;
import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.Utils.Status;
import com.auth0.jwt.exceptions.JWTVerificationException;

import io.lettuce.core.RedisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ShiroException.class)
    public Object ShiroException(ShiroException e) {
        log.error("Shiro 未知系统异常信息：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public Object AuthenticationFailedException(JWTVerificationException e){
        log.info("Token认证失败，需重新登录");
        return ResponseResult.Exception(Status.code_token_refresh_error);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public Object UnauthenticatedException(UnauthenticatedException e){
        log.error("Shiro 系统身份未认证：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Object UnauthorizedException(UnauthorizedException e) {
        log.error("Shiro 系统权限未认证：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public Object AuthenticationException(AuthenticationException e){
        log.error("Shiro 认证异常：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public Object  AuthorizationException(AuthorizationException e){
        log.error("Shiro 授权异常：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(SQLException.class)
    public Object SQLException(SQLException e){
        log.error("SQL 执行异常：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(RedisException.class)
    public Object RedisException(RedisException e){
        log.error("Redis 执行异常：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(MailException.class)
    public Object JavaMailException(MailException e){
        log.error("JavaMail 执行异常：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

    @ExceptionHandler(Exception.class)
    public Object Exception(Exception e) {
        log.error("其他异常信息：\n" + CommonUtils.GetTrace(e));
        return ResponseResult.Exception(Status.code_service_error);
    }

}

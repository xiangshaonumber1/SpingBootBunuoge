package cn.xiangshao.bunuoge.config.shiro;


import cn.xiangshao.bunuoge.Utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 整个Filter还有很多需要优化的地方，现在想不到比较好的处理方法，以后再做优化
 */

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter{

    //重新方法的执行过程 preHandle->isAccessAllowed->isLoginAttempt->executeLogin

//    private UserInfoService userInfoService;
    /* ******************************** @@@ 打死不动系列 @@@ ***************************************/
//    JWTFilter(UserInfoService userInfoService){ this.userInfoService = userInfoService; }
    /* ******************************* @@@ 打死不动系列 @@@ **************************************/

    /**
     * 判断用户是否想要登入。
     * 检测到header中是否包含指定字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String token = servletRequest.getHeader(Status.LOGIN_SIGN);
        return token != null;
    }

    /**
     * 获取token，并进行登录验证
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(Status.LOGIN_SIGN);
        JWTToken jwtToken = new JWTToken(token);
        //提交给realm进行登录，如果错误他会抛出异常，并被捕获
        getSubject(request,response).login(jwtToken);
        //如果没有抛出异常，则代表登入成功
        return true;
    }

    /**
     * 执行登录认证
     * isAccessAllowed决定是否继续执行
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        if (isLoginAttempt(request,response)){
            return executeLogin(request,response);
        }
        return true;
    }

    /**
     * 拦截信息————对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //允许跨域
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        //跨域时会首先放一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){ //对于OPTION请求做拦截，不做token校验
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request,response);
    }


    /**
     * onAccessDenied做后续的操作，如重定向到另外一个地址、添加一些信息到request域等等。
     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
//        Subject subject = getSubject(request,response);
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader(Status.LOGIN_SIGN);
//        //这里和isAccessAllowed中一样，在做一次token的判断是为区分是token刷新还是去登录
//        //先检查token是否存在，如果没有，肯定是先去登录，如果token存在，而登录认证并没有通过，表示token过期或者非法token，并做相应处理
//        if (token==null){
//            log.info("需要进行登录后再继续");
//            MessageData data = MessageData.Refuse().setCode(Status.code_shiro_error_authentication);
//            RequestResponseUtil.responseWrite(data,response);
//        } else if (null == subject || !subject.isAuthenticated()){
//            //一般表示token过期或者非法token，都返回需要重新刷新token的提示
//            log.info("token 需要刷新");
//            MessageData data = MessageData.Refuse().setCode(Status.code_token_expired);
//            RequestResponseUtil.responseWrite(data,response);
//        } else {
//            log.info("系统权限不足,已拒绝访问");
//            MessageData data = MessageData.Refuse().setCode(Status.code_shiro_error_authorization);
//            RequestResponseUtil.responseWrite(data,response);
//        }
//        // 过滤链终止
//        log.info("过滤链终止");
//        return false;
//    }



}

package cn.xiangshao.bunuoge.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiangshao
 * @date 2019/4/26 9:57
 * @describe 拦截器
 */
@Slf4j
@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {

    //preHandle 在业务处理器请求之前被调用
    //postHandle 在业务处理器请求执行完成后，生成试图之前执行
    //afterCompletion 在 DispatcherServlet 完全初期请求后被调用

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //对访问成功的请求,记录访问ip
//        log.info("请求 IP : "+ request.getRemoteAddr());
//        log.info("请求的方式 : "+request.getMethod());
    }
}

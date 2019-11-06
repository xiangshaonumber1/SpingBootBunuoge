package cn.xiangshao.bunuoge.controller;


import cn.xiangshao.bunuoge.service.RedisService;
import cn.xiangshao.bunuoge.service.UserInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/Authentication")
@Api(value = "AuthenticationController",tags ={"用户登录认证等操作管理"})
public class AuthenticationController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RedisService redisService;


//    @PostMapping("/login")
//    @ApiOperation("用户登录")
//    public Object login(HttpServletResponse response,
//                        @ApiParam(value = "账号",required = true) @RequestParam String username, @ApiParam(value = "密码",required = true) @RequestParam String password){
//        JSONObject data = (JSONObject) userService.login(username,password);
//        return MessageData.Success().setData(data);
//    }
//
//
//    @ApiOperation(value = "用户注册申请")
//    @PostMapping("/register")
//    public Object register(HttpServletResponse response,
//                           @ApiParam(value = "用户名",required = true)@RequestParam String username, @ApiParam(value = "密码",required = true)@RequestParam String password,
//                           @ApiParam(value = "绑定邮箱",required = true)@RequestParam String userMail, @ApiParam(value = "邮箱验证码",required = true)@RequestParam String MailVerificationCode){
//
//        //获取redis中存储的邮箱验证码
//        String redis_MailVerificationCode = redisService.get_MailVerificationCode(userMail);
//        boolean resultStatus = MailVerificationCode.equals(redis_MailVerificationCode);
//        JSONObject data = null;
//        if (resultStatus){
//            //验证码正确无误
//             data = (JSONObject) userService.login(username,password);
//        }
//        return MessageData.Success().setStatus(resultStatus).setData(data);
//    }
//
//
//    @ApiOperation("客户端调用该接口，申请刷新token")
//    @GetMapping("/refreshToken")
//    public Object refreshToken(HttpServletRequest request){
//        String token = request.getHeader(Status.LOGIN_SIGN);
//        JSONObject data = (JSONObject) userService.refresh_token(token);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("用户登出,注销处理，清理redis中保存的缓存的信息，无需验证")
//    @PostMapping("/logout")
//    public Object logout(HttpServletRequest request){
//        String token = request.getHeader(Status.LOGIN_SIGN);
//        String openID = JwtUtil.getopenID(token);
//        //成功登出，则删除保存的socket链接
//        nettySocketIoService.notification_logout(openID);
//        return MessageData.Success();
//    }
//
//    //判断该账号是否已注册
//    @GetMapping("/isExist")
//    @ApiOperation("用于判断该账号是否已被注册，无需验证")
//    public Object isExist(@ApiParam(required = true) @RequestParam String username){
//        boolean resultStatus = redisService.isExistInSet(Status.redis_base_Username,username);
//        //result 结果为 false, 表示未注册，返回 !result（true）表示可用。反之亦然
//        return MessageData.Success().setStatus(!resultStatus);
//    }
//
//
//    /***********************************************  测试代码  ********************************************************************/
//    /**
//     * 判断目前是哪个用户登录的
//     */
//    @RequestMapping(path = "/whichUser",method = {RequestMethod.POST,RequestMethod.GET})
//    public Object whichUser(HttpServletRequest request){
//        String token = request.getHeader(Status.LOGIN_SIGN);
//        log.info("header中的token:"+token);
//        String ipone = IPUtils.getClientIpAddress(request);
//        log.info("获取请求用户ip地址 ipone ："+ipone);
//        String iptwo = IPUtils.getIpAddr(request);
//        log.info("获取请求用户ip地址 iptwo ："+iptwo);
//        String ipthree = IPUtils.getRemoteAddr(request);
//        log.info("获取请求用户ip地址 ipthree ："+ipthree);
//        Subject subject = SecurityUtils.getSubject();
//        Object getPrincipals = subject.getPrincipals();
//        //结果表明，获取的是token
//        log.info("getPrincipals:"+ getPrincipals);
//        Object  getPrincipal = subject.getPrincipal();
//        //结果表明，获取的是token
//        log.info("getPrincipal:"+ getPrincipal);
//        boolean isAuthenticated =  subject.isAuthenticated();
//        log.info("判断用户是否验证："+isAuthenticated);
//        return null;
//    }

}

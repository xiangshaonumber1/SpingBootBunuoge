package cn.xiangshao.bunuoge.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "UserController",tags = {"用户相关操作"})
public class UserController {


//    @ApiOperation("修改密码")
//    @PostMapping("/update_pw")
//    @RequiresAuthentication //必须登录
//    public Object update_pw(HttpServletRequest request,
//                                      @ApiParam(required = true,value = "新密码") @RequestParam String new_password,
//                                      @ApiParam(value = "旧密码") @RequestParam(required = false) String old_password,
//                                      @ApiParam(value = "邮箱验证码") @RequestParam(required = false) String mail_code,
//                                      @ApiParam(value = "修改方式",required = true,allowableValues = "password,email")@RequestParam String type){
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        UpdateInfo updateInfo = new UpdateInfo();
//        updateInfo.setOpenID(openID).setNew_pw(new_password).setType(type);
//        switch (type){
//            case "password": //旧密码修改(能正常登录情况下)
//                updateInfo.setOld_pw(old_password);
//                break;
//            case "email": case "emailReset":   //邮箱验证码修改(能正常登录情况下) || 邮箱重置密码(忘记密码情况下)
//                updateInfo.setMail_code(mail_code);
//                break;
//        }
//        boolean resultStatus = userService.update_pw(updateInfo);
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//
//    //待测试
////    @ApiOperation("通过现有邮箱验证，更换绑定邮箱（能正常登录情况下）")
////    @PostMapping("/update_email")
////    public Object update_email(HttpServletRequest request,
////                               @ApiParam("目前绑定的邮箱") @RequestParam String nowEmail,
////                               @ApiParam("目前绑定的邮箱的收到的验证码")@RequestParam String nowVerificationCode,
////                               @ApiParam("需要重新绑定的邮箱")@RequestParam String newEmail,
////                               @ApiParam("需要重新绑定的邮箱收到的验证码")@RequestParam String newVerificationCode){
////        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
////        String redis_now_VerificationCode = redisService.get_MailVerificationCode(nowEmail);
////        String redis_new_VerificationCode = redisService.get_MailVerificationCode(newEmail);
////        if (redis_now_VerificationCode.equals(nowVerificationCode)){//储存的验证码和用户填写的验证码一致
////            if (newVerificationCode.equals(redis_new_VerificationCode)){//新邮箱的code和用户输入的一致
////                userService.update_email(openID,newEmail);
////                return new MessageData(true);
////            }
////        }
////        return new MessageData().setCode(Status.code_success).setMsg(Status.msg_fail_message).setData(false);
////    }
//
//    @ApiOperation("判断该邮箱是否能在现有数据库中找到")
//    @PostMapping("/isExist")
//    public Object isExist(String userMail){
//        boolean resultStatus = userService.isExist_mail(userMail);
//        // true，表示该邮箱存在，已经被注册
//        // false,表示该邮箱不存在，并未被注册
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("请求系统邮箱验证码")
//    @GetMapping("/request_mail")
//    public Object mail_system_verification(@ApiParam(required = true,value = "目标用户邮箱")@RequestParam String value,
//                                         @ApiParam(required = true,value = "请求类型")@RequestParam String type) throws MessagingException {
//        /* 有单独的判断邮箱是否存在的接口，所以如果是用于注册，判断邮箱是否被注册的操作就不在这里
//         * 该接口只负责发送根据不同的模板发送不同类型的验证码 */
//        //请求系统邮箱验证码
//        switch (type){
//            case "register":    //用户注册,这里实际上传过来的就是邮箱
//                 mailService.mail_system_verification(value,type);
//                break;
//            case "update_encrypt":  //用户通过邮箱修改密码，这里实际上传过来的是openID
//                String email = userService.getUserInfoByopenID(value).getEmail();
//                mailService.mail_system_verification(email,type);
//                break;
//            case "emailReset":    //用户忘记密码，请求通过邮箱实现重置密码
//                mailService.mail_system_verification(value,type);
//                break;
//            default:
//                mailService.mail_system_verification(value,type);
//        }
//        return MessageData.Success();
//    }
//
//    @ApiOperation("根据openID获取用户公开信息")
//    @GetMapping("/getUserInfo")
//    public Object getMyUserInfo(HttpServletRequest request, @ApiParam(required = true,value = "openID")@RequestParam String openID){
//        UserInfo userInfo = userService.getUserInfoByopenID(openID);
//        return MessageData.Success().setData(userInfo);
//    }
//
//    @ApiOperation("保存修改后的个人信息")
//    @PostMapping("/updateUserInfo")
//    public Object updateUserInfo(HttpServletRequest request, HttpServletResponse response,
//                                   @ApiParam(required = true,value = "用户昵称")@RequestParam String nickname,
//                                   @ApiParam(required = true,value = "心愿墙")@RequestParam String wishCard,
//                                   @ApiParam(required = true,value = "性别")@RequestParam String gender,
//                                   @ApiParam(required = true,value = "个人描述")@RequestParam String myDescribe){
//        JWTToken token = new JWTToken(request.getHeader(Status.LOGIN_SIGN));
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        UserInfo userInfo = new UserInfo();
//
//        //手动进行shiro登录，实现检测用户是否有admin或者root的身份，如果有，则对昵称进行特殊添加
//        Subject subject = SecurityUtils.getSubject();
//        subject.login(new JWTToken(request.getHeader(Status.LOGIN_SIGN)));
//        List<String> roles = new ArrayList<>();
//        roles.add("超级管理员");
//        roles.add("管理员");
//        for(boolean result : subject.hasRoles(roles)){
//            if (result){
//                nickname = userService.isAdmin(nickname);
//                break;// 当 result为true时，终止循环，后面的不再执行
//            }
//        }
//        userInfo.setOpenID(openID).setNickname(nickname).setWishCard(wishCard).setGender(gender).setMyDescribe(myDescribe);
//        int result = userService.updateUserInfo(userInfo);
//        return MessageData.Success().setStatus(result>0);
//    }
//
//    @ApiOperation("保存新的用户头像")
//    @PostMapping("/updateUserIcon")
//    @RequiresAuthentication
//    public Object updateUserIcon(HttpServletRequest request){
//        JSONArray userIconPath = CommonUtils.upload_files(request,"image/userIcon/");
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        assert userIconPath != null;
//        int result = userService.updateUserIcon(userIconPath.toString(),openID);
//        return MessageData.Success().setStatus(result>0).setData(userIconPath);
//    }
//
//
//    @ApiOperation("根据executeType对用户进行新增关注还是取消关注")
//    @PostMapping("/clickMark")
//    public Object clickMark(@ApiParam(required = true,value = "请求用户openID")@RequestParam String openID,
//                            @ApiParam(required = true,value = "mark目标用户")@RequestParam String aim_openID,
//                            @ApiParam(required = true,value = "决定是新增关注，还是取消关注")@RequestParam boolean executeType){
//        userService.clickMark(openID,aim_openID,executeType);
//        return MessageData.Success().setData(!executeType);
//    }
//
//    @ApiOperation("判断请求用户是否有关注指定目标用户")
//    @GetMapping("/getMarkStatus")
//    public Object getMarkStatus(@ApiParam(required = true,value = "文章ID")@RequestParam String openID,
//                                @ApiParam(required = true,value = "用户openID")@RequestParam String aim_openID){
//        boolean result = userService.getMarkStatus(openID,aim_openID);
//        return MessageData.Success().setData(result);
//    }
//
//    @ApiOperation("获取未读消息")
//    @GetMapping("/getUnreadMessageCount")
//    @RequiresAuthentication
//    public Object getUnreadSystemMessageCount(HttpServletRequest request){
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        JSONObject unreadCount = userService.getUnreadSystemMessageCount(openID);
//        return MessageData.Success().setData(unreadCount);
//    }
//
//
//    @ApiOperation("获取系统信息详情列表")
//    @GetMapping("/getSystemMessageDetails")
//    public Object getSystemMessageDetails(HttpServletRequest request,
//                                          @ApiParam(required = true,value = "页号")@RequestParam(defaultValue = "1") int page,
//                                          @ApiParam(required = true,value = "每页查询量")@RequestParam(defaultValue = "8") int pageSize){
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        /**
//         * count：设置为true时,page 可以通过getTotal获取数总和
//         * reasonable： 分页合理化参数，默认值为false，true 时，pageNum<=0 时会查询第一页，pageNum>pages（超过总数时），会查询最后一页
//         * pageSizeZero： 默认为false, 为true时，pageSize 为0, 查询全部
//          */
//        PageHelper.startPage(page,pageSize);
//        Page<SystemMessage> systemMessageInfos = userService.getSystemMessageDetails(openID);
//        JSONObject data = new JSONObject();
//        data.put("total",systemMessageInfos.getTotal());
//        data.put("system_message_infos",systemMessageInfos);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("获取回复类消息详情")
//    @GetMapping("/getReplyMessageDetails")
//    public Object getReplyMessageDetails(HttpServletRequest request,
//                                          @ApiParam(required = true,value = "页号")@RequestParam(defaultValue = "1") int page,
//                                          @ApiParam(required = true,value = "每页查询量")@RequestParam(defaultValue = "8") int pageSize){
//        String openID = JwtUtil.getopenID(request.getHeader(Status.LOGIN_SIGN));
//        PageHelper.startPage(page,pageSize);
//        Page<MessageDetails> reply_messageDetails = userService.getReplyMessageDetails(openID);
//        JSONObject data = new JSONObject();
//        data.put("reply_message_details",reply_messageDetails);
//        data.put("total",reply_messageDetails.getTotal());
//        return MessageData.Success().setData(data);
//    }

}

package cn.xiangshao.bunuoge.controller;


import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/mail")
@Api(value = "MailController" ,tags={"邮件相关操作"})
public class MailController {

    @Autowired
    MailService mailService;

    @ApiOperation("请求邮箱验证码")
    @GetMapping("/getVerifyCode")
    public Object getVerifyCode(@Param("username")@RequestParam String username) throws MessagingException {
       String verifyCode = mailService.generateVerifyCode(username);
       return ResponseResult.Success().setData(verifyCode);
    }

}

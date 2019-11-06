package cn.xiangshao.bunuoge.controller;

import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblLinkInfo;
import cn.xiangshao.bunuoge.service.CommonService;
import cn.xiangshao.bunuoge.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/common")
@Api(value = "CommonController",tags = {"Web无权限相关操作"})
public class CommonController {

    @Autowired
    CommonService commonService;

    @Autowired
    MailService mailService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseResult register(@ApiParam(required = true,value = "注册信息") @RequestParam Map<String,Object> params){
        return commonService.register(params);
    }

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public ResponseResult login(@ApiParam(required = true,value = "登录信息")@RequestParam Map<String,Object> params){
        return commonService.login(params);
    }

    @ApiOperation("检查账号（邮箱）是否已存在")
    @GetMapping("/checkUsername")
    public ResponseResult checkUsername(@ApiParam(required = true,value = "邮箱(账号)") @RequestParam String username){
        return commonService.checkUsername(username);
    }

    @ApiOperation("新增网站链接信息")
    @PostMapping("/addLinkInfo")
    public ResponseResult addLinkInfo(@RequestBody TblLinkInfo linkInfo){
        return commonService.addLinkInfo(linkInfo);
    }

    @ApiOperation("删除网站链接信息")
    @PostMapping("/deleteLinkInfo")
    public ResponseResult deleteLinkInfo(@RequestBody TblLinkInfo linkInfo){
        return commonService.deleteLinkInfo(linkInfo);
    }

    @ApiOperation("更新网站链接信息")
    @PostMapping("/updateLinkInfo")
    public ResponseResult updateLinkInfo(@RequestBody TblLinkInfo linkInfo){
        return commonService.updateLinkInfo(linkInfo);
    }

    @ApiOperation("获取网站链接信息")
    @GetMapping("/getLinkInfo")
    public ResponseResult getLinkInfo(){
        return commonService.getLinkInfo();
    }


//    @ApiOperation("获取网站首页海报图片信息")
//    @GetMapping("/getPosterList")
//    public Object getPosterList(){
//        List<String> posterList = commonService.getPosterList();
//        return MessageData.Success().setData(posterList);
//    }
//
//    @ApiOperation("独立的上传图片的接口")
//    @PostMapping("/uploadPicture")
//    public Object uploadPicture(HttpServletRequest request, @ApiParam(required = true,value = "保存路径") @RequestParam String savePath){
//        JSONArray jsonArrayResult = CommonUtils.upload_files(request,"image/"+savePath+"/");
//        return MessageData.Success().setData(jsonArrayResult);
//    }
//
//    @ApiOperation("提交反馈意见")
//    @PostMapping("/writeFeedBackMessage")
//    public Object writeFeedBackMessage(@ApiParam(value = "用户ID(或游客临时ID)",required = true)@RequestParam String openID, @ApiParam(value = "反馈内容",required = true)@RequestParam String content,
//                                       @ApiParam(value = "联系方式类型",required = true)@RequestParam String contactType, @ApiParam(value = "联系方式详情",required = true)@RequestParam String contactInfo,
//                                       @ApiParam(value = "反馈类型",required = true)@RequestParam String feedbackType){
//        TblFeedBackInfo feedInfo = new TblFeedBackInfo();
//        feedInfo.setOpenID(openID).setContent(content).setContactType(contactType).setContactInfo(contactInfo).setFeedbackType(feedbackType);
//        boolean resultStatus = commonService.writeFeedBackMessage(feedInfo);
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("忘记密码,通过邮箱获取验证码修改密码")
//    @PostMapping("/resetPassword")
//    public Object resetPassword(@ApiParam(required = true,value = "用户邮箱")@RequestParam String email,
//                                @ApiParam(required = true,value = "邮箱验证码")@RequestParam String emailCode,
//                                @ApiParam(required = true,value = "用户名(账号)")@RequestParam String username) throws MessagingException {
//        boolean resultStatus = commonService.resetPassword(username,email,emailCode);
//        if (resultStatus){
//            //修改成功发送 密码修改提示邮件
//            mailService.updatePasswordRemind(email);
//        }
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("获取网站底部导航栏信息")
//    @GetMapping("/getBottomNavBar")
//    public Object getBottomNavBar(){
//        List<TblLinkInfo> linkList = commonService.getBottomNavBar();
//        return MessageData.Success().setData(linkList);
//    }

}

package cn.xiangshao.bunuoge.controller;//package com.personal.myblog.controller;


import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.service.AdminService;
import cn.xiangshao.bunuoge.service.ArticleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin")
@Api(value = "AdminController",tags = {"管理员后台管理操作"})
@RequiresRoles(logical = Logical.OR,value = {"超级管理员","管理员"})//表示所有接口都需要admin身份
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ArticleInfoService articleInfoService;

    public ResponseResult resetPasswordBatch (@RequestBody String[] openIds){
        return adminService.resetPasswordBatch(openIds);
    }


    @ApiOperation("批量删除指定文章")
    @PostMapping("/deleteBatch")
    public ResponseResult deleteBatch(@RequestBody String[] articleIds){
        return articleInfoService.deleteBatch(articleIds);
    }

    @ApiOperation("批量修改指定文章工作状态")
    @PostMapping("/updateBatch")
    public ResponseResult updateBatch(@RequestBody String[] articleIds,@RequestParam String status){
        return articleInfoService.updateBatch(articleIds,status);
    }

//
//    @ApiOperation("管理员登录")
//    @PostMapping("/service_login")
//    public Object service_login(){
//        //该类中的所有方法皆需要验证admin身份，否则无法通过
//        return MessageData.Success();
//    }
//
//    @ApiOperation("获取用户信息（主要是基本信息和权限）")
//    @GetMapping("/getUserRoleAndPermissionList")
//    public Object getUserInfo(@ApiParam(required = true,value = "查询页号")@RequestParam(defaultValue = "1") int page,
//                              @ApiParam(required = true,value = "每页查询量")@RequestParam(defaultValue = "10") int pageSize,
//                              @ApiParam(value = "key_word")@RequestParam(required = false)String key_word){
//        AdminUserRoleAndPermission userRoleAndPermission = adminService.getInfo(page,pageSize,key_word);
//        JSONObject data = new JSONObject();
//        data.put("total",userRoleAndPermission.getUserPartInfoList().getTotal());
//        data.put("userRoleAndPermission",userRoleAndPermission);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("更改用户身份 或 用户权限分配")
//    @PostMapping("/update_role_or_permission")
//    public Object update_role_or_permission(@ApiParam(required = true,value = "修改目标用户ID")@RequestParam String aim_openID,
//                              @ApiParam(required = true,value = "修改的值")@RequestParam String new_value,
//                              @ApiParam(required = true,value = "修改类型")@RequestParam String type){
//        boolean resultStatus = adminService.update_role_or_permission(aim_openID,new_value,type);
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("获取用户反馈的信息")
//    @GetMapping("/getFeedBackMessage")
//    public Object getFeedBackMessage(@ApiParam(required = true,value = "查询页号")@RequestParam(defaultValue = "1") int page,
//                                     @ApiParam(required = true,value = "查询总数")@RequestParam(defaultValue = "10") int pageSize){
//        PageHelper.startPage(page,pageSize);
//        Page<FeedBackMessage> feedBackMessageList = adminService.getFeedBackMessage();
//        JSONObject data = new JSONObject();
//        data.put("total",feedBackMessageList.getTotal());
//        data.put("feedBackMessageList",feedBackMessageList);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("修改反馈信息状态")
//    @PostMapping("/updateFeedBackStatus")
//    public Object updateFeedBackStatus(@ApiParam(required = true,value = "需要修改的反馈信息的序列号")@RequestParam String feedID,
//                                       @ApiParam(required = true,value = "需要修改的状态")@RequestParam String status){
//        boolean resultStatus = adminService.updateStatus(feedID,status);
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("修改文章审核状态")
//    @PostMapping("/updateArticleStatus")
//    public Object updateArticleStatus(@ApiParam(required = true,value = "目标文章ID")@RequestParam String articleID,
//                                      @ApiParam(required = true,value = "修改的状态")@RequestParam String new_status){
//        boolean resultStatus = adminService.updateArticleStatus(articleID,new_status);
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("管理员获取审核文章信息")
//    @GetMapping("/getAdminArticleInfo")
//    public Object getAdminArticleInfo(@ApiParam(required = true,value = "查询页数")@RequestParam(defaultValue = "1") int page,
//                                      @ApiParam(required = true,value = "每页查询量")@RequestParam(defaultValue = "10") int pageSize,
//                                      @ApiParam("查询关键字")@RequestParam(required = false) String key_word){
//        AdminArticleInfo adminArticleInfo = adminService.getAdminArticleInfo(page,key_word,pageSize);
//        JSONObject data = new JSONObject();
//        data.put("total",adminArticleInfo.getAdminArticleList().getTotal());
//        data.put("adminArticleInfo",adminArticleInfo);
//        return MessageData.Success().setData(data);
//    }
//
//    @ApiOperation("管理员重置指定用户密码")
//    @PostMapping("/adminResetPassword")
//    public Object adminResetPassword(@ApiParam(required = true,value = "目标用户ID")@RequestParam String aim_openID,
//                                     @ApiParam(required = true,value = "用户绑定邮箱")@RequestParam String email) throws MessagingException {
//        boolean resultStatus = adminService.resetPassword(aim_openID);
//        if (resultStatus){
//            mailService.updatePasswordRemind(email);
//        }
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//
////    @ApiOperation("对评论和回复信息的处理")
////    @PostMapping("/dealComment")
////    public Object dealComment(@ApiParam(required = true,value = "处理目标ID(评论/评论下的回复)")@RequestParam String aim_ID,
////                                      @ApiParam(required = true,value = "数据类型(comment,reply)")@RequestParam String data_type,
////                                      @ApiParam(required = true,value = "操作类型")@RequestParam String deal_type){
////        boolean result = adminService.dealCommentAndReply(aim_ID,data_type,deal_type);
////        if (result){
////            return new MessageData(true).setData(true);
////        }else {
////            return new MessageData().setCode(Status.code_refuse).setMsg(Status.msg_fail).setData(false);
////        }
////    }
//
//    @ApiOperation("编辑底部链接信息")
//    @PostMapping("/edit_link")
//    public Object edit_link(@ApiParam("链接ID")@RequestParam(required = false) String linkID, @ApiParam(required = true,value = "链接类型")@RequestParam String type,
//                            @ApiParam(required = true,value = "标签名")@RequestParam String label, @ApiParam(required = true,value = "网站链接")@RequestParam String website,
//                            @ApiParam(required = true,value = "操作类型")@RequestParam String operation){
//        Link linkInfo = new Link();
//        linkInfo.setType(type).setLabel(label).setWebsite(website);
//        if (StringUtils.isNotBlank(linkID)){
//            linkInfo.setLinkID(linkID);
//        }
//        boolean resultStatus = adminService.edit_link(linkInfo,operation);
//        return MessageData.Success().setStatus(resultStatus);
//    }
//
//    @ApiOperation("发送全体系统消息")
//    @PostMapping("/sendSystemMessage")
//    public Object sendSystemMessage(@ApiParam(required = true,value = "发送类型(全体/指定)")@RequestParam String sendType, @ApiParam(required = true,value = "消息主题")@RequestParam String title,
//                                    @ApiParam(required = true,value = "消息内容")@RequestParam String content, @ApiParam(required = true,value = "接收人openID")@RequestParam String acceptor){
//        adminService.sendSystemMessage(sendType,title,content,acceptor);
//        return MessageData.Success();
//    }



}

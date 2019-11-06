package cn.xiangshao.bunuoge.service;

import cn.xiangshao.bunuoge.entity.request.TblUserInfo;


import java.util.Map;

public interface UserInfoService {

    /**
     * 新增用户信息
     */
    void add(TblUserInfo userInfo);

    /**
     * 通过指定参数，获取对应的用户信息
     */
    TblUserInfo getUserInfo(Map<String, Object> params);


//
//    /**
//     * 修改密码
//     */
//    public boolean update_pw(UpdateInfo updateInfo){
//
//        boolean allowed = false; //判断是否满足修改密码的条件
//        //获取数据库中用户信息
//        TblAccountInfo user = userDao.getUserByopenID(updateInfo.getOpenID());
//        String type = updateInfo.getType();
//        switch (type){
//            case "password":    //如果是通过密码修改(能正常登录情况下)
//                //获取数据库中的salt
//                String pw_salt = user.getSalt();
//                //将加密后的旧密码与数据库中的密码做比较
//                String old_encrypt = new Sha256Hash(updateInfo.getOld_pw(),pw_salt).toHex();
//                //如果密码一致，则表示明文密码相同，允许修改密码
//                allowed = old_encrypt.equals(user.getEncrypt());
//                break;
//            case "email":   //如果是通过邮箱验证修改密码(能正常登录情况下)
//                //先从Redis中获取邮箱验证码
//                String redis_mail_code = redisService.get_MailVerificationCode(user.getEmail());
//                log.info("后台获取到的邮箱验证码："+redis_mail_code);
//                //然后将传递到后台的验证码与从redis中获取的验证对比，一致则允许刷新
//                allowed = redis_mail_code.equals(updateInfo.getMail_code());
//                //然后立即将redis中的验证码删除
//                redisService.delete_MailVerificationCode(user.getEmail());
//                break;
//            case "emailReset":  //通过邮箱重置密码(忘记密码情况下)
//                //先从Redis中获取邮箱验证码
//                String reset_redis_mail_code = redisService.get_MailVerificationCode(user.getEmail());
//                log.info("后台获取到的邮箱验证码："+reset_redis_mail_code);
//                allowed = reset_redis_mail_code.equals(updateInfo.getMail_code());
//                updateInfo.setNew_pw("123456789"); //默认初始化密码为123456789
//                //然后立即将redis中的验证码删除
//                redisService.delete_MailVerificationCode(user.getEmail());
//                break;
//            default:
//                break;
//        }
//        //如果上面的某一条通过，都允许修改密码
//        if (allowed){
//            String new_pw_salt = JwtUtil.generateSalt(); //获取新的随机数
//            String new_pw = new Sha256Hash(updateInfo.getNew_pw(),new_pw_salt).toHex();//生成新的加密密码
//            return userDao.update_pw(updateInfo.getOpenID(),new_pw,new_pw_salt);
//        }
//        return false;
//    }
//
//    /**
//     * 修改用户绑定的邮箱
//     */
//    public void update_email(String openID,String newEmail){
//        int rows = userDao.update_email(openID,newEmail);
//        log.info("update_email rows:"+rows);//输出影响条数
//    }
//
//    /**
//     * 发送系统邮件时，先查询该邮箱是否存在
//     */
//    public boolean isExist_mail(String userMail){
//        String result = userDao.IsExist_mail(userMail);
//        log.info("isExist_mail:"+result);
//        return result != null;
//    }
//
//    /**
//     * 修改用户基本信息
//     */
//    public int updateUserInfo(UserInfo userInfo){
//        return userDao.updateUserInfo(userInfo);
//    }
//
//    /**
//     * 修改用户头像
//     */
//    public int updateUserIcon(String userIcon,String openID){
//        return userDao.updateUserIcon(userIcon,openID);
//    }
//
//    //检测是否是admin或者root身份的用户，如果是，则为昵称设置特殊属性
//    public String isAdmin(String nickname){
//        return "<span style=\"color:;font-weight:bold\">"+nickname+"</span>"+
//                "<span class=\"glyphicon glyphicon-ok\" style=\"color:limegreen;\"></span>";
//    }
//
//    // executeType = true : 即取消关注行为
//    // executeType = false : 用户新增关注行为
//    public void clickMark(String openID,String aim_openID,boolean executeType){
//        redisService.clickMark(openID,aim_openID,executeType);
//    }
//
//    //判断发起用户是否有关注该用户
//    public boolean getMarkStatus(String openID,String aim_openID){
//        return redisService.getMarkStatus(openID,aim_openID);
//    }
//
//    //确认消息已阅读(即新增系统消息已读记录)
////    public void confirmSystemMessage(String openID,String messageID){
////        redisService.confirmSystemMessage(openID,messageID);
////    }
//
//    //获取未读系统信息概括, 主要用于前端统计未读数量, 以及点击查看后, 返回具体的需要确认已读的messageID
//    public JSONObject getUnreadSystemMessageCount(String openID){
//        /*
//         * 未读系统消息解析：
//         * 数据库中有保存所有系统消息，redis中保存的是已读系统消息的用户ID
//         * 在指定messageID下，如果能找到用户ID，说明当前用户已读该条消息
//         */
//        //初始化系统消息类数量
//        int unread_system_message_count = 0;
//        //系统消息类：第一步，获取数据库中，指定账号创建后才发布的所有系统消息
//        List<SystemMessage> systemMessageList = userDao.getSystemMessage(openID);
//        //系统消息类：第二步，从redis中，判断从数据库中获取的系统消息中，该messageID下，是否有指定用户ID(即是否已读)
//        JSONObject count = new JSONObject();
//        for (SystemMessage data : systemMessageList){
//            boolean result = redisService.checkSystemMessage(openID,data.getMessageID());
//            if (!result) {
//                //如果是未读消息(false，不存在，即未确认已读)，则+1
//                unread_system_message_count++;
//            }
//        }
//
//        //初始化个人消息
//        int unread_personal_message_count = 0;
//
//        //获取回复消息总数
//        int unread_reply_message_count = redisService.getUnReadReplyMessage(openID);
//
//        //全部放到count中
//        count.put("system",unread_system_message_count);
//        count.put("personal",unread_personal_message_count);
//        count.put("reply",unread_reply_message_count);
//        return count;
//    }
//
//    //获取系统信息详情
//    public Page<SystemMessage> getSystemMessageDetails(String openID){
//        Page<SystemMessage> systemMessageList = userDao.getSystemMessage(openID);
//        //循环 将获取到的messageID放到请求用户的已读列表中
//        for (SystemMessage message : systemMessageList){
//            redisService.readSystemMessage(openID,message.getMessageID());
//        }
//        return systemMessageList;
//    }
//
//    //获取指定消息详情
//    public Page<MessageDetails> getReplyMessageDetails(String openID){
//        int unreadCount = redisService.getUnReadReplyMessage(openID);
//        if (unreadCount>0){
//            //如果还有未读消息,则全部删除(即为已读)
//            redisService.readReplyMessage(openID);
//        }
//        return userDao.getReplyMessageDetails(openID);
//    }

}

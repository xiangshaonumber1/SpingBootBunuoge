package cn.xiangshao.bunuoge.service;

import javax.mail.MessagingException;

public interface MailService {

    /**
     * 生成验证码
     * @param userEmail 申请用户openId
     */
    String generateVerifyCode(String userEmail) throws MessagingException;

    String getVerifyCode(String username);

//    // 获取JavaMailSender bean
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    RedisService redisService;
//
//    @Autowired
//    UserService userService;
//
//   //用户密码变动提醒
//    public void updatePasswordRemind(String aim_email) throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        //true表示需要创建一个multipart message
//        MimeMessageHelper mail_helper;
//        mail_helper = new MimeMessageHelper(message,true);
//        mail_helper.setFrom(Status.email_from);
//        mail_helper.setTo(aim_email);
//        mail_helper.setSubject("密码修改提示");
//        mail_helper.setText(mail_passwordChangeRemind(),true);
//        //发送邮件
//        javaMailSender.send(message);
//    }
//
//    /**
//     * 请求发送验证码
//     * 用于一些安全操作的防范，比如忘记密码，可以通过该方式重新修改密码
//     * 也可以通过该方式注册账号
//     */
//    public void mail_system_verification(String userMail,String type) throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        //true表示需要创建一个multipart message
//        MimeMessageHelper mail;
//        mail = new MimeMessageHelper(message,true);
//        mail.setFrom(Status.email_from);
//        mail.setTo(userMail);
//        mail.setSubject("系统验证码");
//        //生成验证码
//        String VerificationCode = CommonUtils.MailVerificationCode();
//        //将验证码保存到redis中，并设置有效过期时间，超时未使用必须重新申请发送，使用后必须立即删除
//        redisService.save_MailVerificationCode(userMail,VerificationCode);
//
//        switch (type){
//            case "register":
//                mail.setText(mail_register_verification(VerificationCode),true);
//                break;
//            case "update_encrypt":
//                mail.setText(mail_update_encrypt_verification(VerificationCode),true);
//                break;
//            case "emailReset":
//                mail.setText(mail_update_email_verification(VerificationCode),true);
//                break;
//        }
//        //发送邮件
//        javaMailSender.send(message);
//    }
//
//    /**
//     * 发送验证码模板，适用于注册时使用
//     */
//    private String mail_register_verification(String VerificationCode){
//        return "<html>\n" +
//                "<body>\n" +
//                "<font size='5px'> ok博客欢迎您的到来，ok有你更精彩。 </font>\n" +
//                "<br><br>"+
//                "这是您的注册验证码，请在5分钟内使用，如果非你本人亲自操作，请忽略该邮件"+
//                "<br><br>" +
//                "您的验证码：<font size='3px' style='font-weight: bold'>" + VerificationCode + "</font>" +
//                "<br><br><hr>"+
//                "(这是一封由系统发送的邮件，请勿回复。)\n"+
//                "<br>"+
//                "<font color='#555555'>如有任何疑问，可发送邮件至： 【zxcvbnmm126@qq.com】 </font>"+
//                "</body>\n" +
//                "</html>";
//    }
//
//    /**
//     * 发送验证码模板，适用于修改密码时使用
//     */
//    private String mail_update_encrypt_verification(String VerificationCode){
//        return "<html>\n" +
//                "<body>\n" +
//                "<font size='5px'> ok博客正在为你服务。 </font>\n" +
//                "<br><br>"+
//                "系统提醒你：我们监测到您正在申请修改密码，这是您的验证码，如果非你本人亲自操作，请忽略该邮件"+
//                "<br><br>" +
//                "验证码：<font size='3px' style='font-weight: bold'>" + VerificationCode + "</font>" +
//                "<br><br><hr>"+
//                "(这是一封由系统发送的邮件，请勿回复。)\n"+
//                "<br>"+
//                "<font color='#555555'>如有任何疑问，可发送邮件至： 【zxcvbnmm126@qq.com】 </font>"+
//                "</body>\n" +
//                "</html>";
//    }
//
//    /**
//     * 发送验证码模板，适用于修改绑定邮箱
//     */
//    private String mail_update_email_verification(String VerificationCode){
//        return "<html>\n" +
//                "<body>\n" +
//                "<font size='5px'> ok博客正在为你服务。 </font>\n" +
//                "<br><br>"+
//                "系统提醒你：这是您的验证码，如果非你本人亲自操作，请忽略该邮件"+
//                "<br><br>" +
//                "验证码：<font size='3px' style='font-weight: bold'>" + VerificationCode + "</font>" +
//                "<br><br><hr>"+
//                "(这是一封由系统发送的邮件，请勿回复。)\n"+
//                "<br>"+
//                "<font color='#555555'>如有任何疑问，可发送邮件至： 【zxcvbnmm126@qq.com】 </font>"+
//                "</body>\n" +
//                "</html>";
//    }
//
//    /**
//     *  发送密码改动提醒模板
//     */
//    private String mail_passwordChangeRemind(){
//        return "<html>\n" +
//                "<body>\n" +
//                "<font size='5px'> Getok 正在为你服务。 </font>\n" +
//                "<br><br>"+
//                "系统提醒你：您的账号密码已修改，如果非您本人亲自操作，或者是您不知情的情况下，请引起重视！ 可立即联系管理员 【jingxiangemail@163.com】 获取帮助。"+
//                "<br><br>" +
//                "<br><br><hr>"+
//                "(这是一封由系统发送的邮件，请勿回复。)\n"+
//                "<br>"+
//                "<font color='#555555'>如有任何疑问，可发送邮件至： 【zxcvbnmm126@qq.com】 </font>"+
//                "</body>\n" +
//                "</html>";
//    }

}

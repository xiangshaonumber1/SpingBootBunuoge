package cn.xiangshao.bunuoge.serviceImpl;

import cn.xiangshao.bunuoge.Utils.CommonUtils;
import cn.xiangshao.bunuoge.Utils.Status;
import cn.xiangshao.bunuoge.service.MailService;
import cn.xiangshao.bunuoge.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    RedisService redisService;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public String generateVerifyCode(String userEmail) throws MessagingException {
        //生成一个验证码，并保存到redis中
        String verifyCode = CommonUtils.generateVerifyCode();
        redisService.saveVerifyCode(userEmail,verifyCode);

        //保存完毕后，将验证码发送到申请者邮箱里
        this.sendEmail(userEmail,verifyCode);

        return verifyCode;
    }

    @Override
    public String getVerifyCode(String username) {
        return redisService.getVerifyCode(username);
    }

    private void sendEmail(String userEmail,String verifyCode) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        //true表示需要穿件一个multipart message
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message,true);
        helper.setFrom(Status.systemEmail);
        helper.setTo(userEmail);
        helper.setSubject("布诺阁");
        helper.setText(this.verifyCodeTemplate(verifyCode),true);
        javaMailSender.send(message);
    }

    /**
     * 系统验证码模板
     */
    private String verifyCodeTemplate(String verifyCode){
        return "<html>\n" +
                "<body>\n" +
                "<font size='5px'> 布诺阁 正在为你服务。 </font>\n" +
                "<br><br>"+
                "系统提醒你：这是您的验证码，如果非你本人亲自操作，请忽略该邮件"+
                "<br><br>" +
                "验证码：<font size='3px' style='font-weight: bold'>" + verifyCode + "</font>" +
                "<br><br><hr>"+
                "(这是一封由系统发送的邮件，请勿回复。)\n"+
                "<br>"+
                "<font color='#555555'>如有任何疑问 可通过以下方式联系站主</font>"+
                "<br>"+
                "<font style='font-weight: bold'>QQ：821940979</font>"+
                "</body>\n" +
                "</html>";
    }

}

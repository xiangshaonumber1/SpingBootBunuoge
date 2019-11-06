package cn.xiangshao.bunuoge.Utils;

public class Status {

    //系统初始化默认超级管理员账号
    public static String superAdminUsername = "821940979@qq.com";
    //系统发送邮件账号和昵称，网易163邮箱
    public static String systemEmail = "GetOk<jingxiangemail@163.com>";
    //token 标志
    public static String LOGIN_SIGN = "X-Auth-Token";

    //服务器响应状态码
    static int code_service_success = 10200; //无异常
    public static int code_service_error = 10201;   //操作异常
    static int code_service_refuse = 10202; //操作失败(拒绝)

    public static int code_shiro_error = 10300; //shiro执行异常
    public static int code_shiro_error_authentication = 10301;  //认证失败
    public static int code_shiro_error_authorization = 10302;   //授权失败

    public static int code_token_error = 10400;  //非法token提醒
    public static int code_token_expired = 10401;   //token过期提醒
    public static int code_token_refresh_error = 10402;  //token刷新失败，重新登录

    public static int code_mail_error = 10001;   //邮件发送异常

    //状态基本描述信息
    public static String msg_sql_error = "SQL 执行异常，请注意查看！";
    public static String msg_redis_error = "Redis 执行异常，请注意查看！";

    public static String msg_mail_error = "邮件发送异常，请注意查看！";
    public static String msg_mail_codeError = "验证码错误，请重新输入";

    public static String msg_token_refresh = "Token 已刷新，请注意保存";
    public static String msg_token_expired = "Token 已失效，请刷新";
    public static String msg_token_refresh_error = "Token 认证已过期，请重新登录";

    public static String msg_user_locked = "该账号已被冻结!";
    public static String msg_user_exist = "该账号已被注册，不妨换一个试试";
    public static String msg_user_password_update = "密码修改成功，请重新登录";


    public static String msg_shiro_error = "shiro 未知异常";
    public static String msg_shiro_authorization_error = "shiro authorization 系统授权 执行异常";
    public static String msg_shiro_authentication_error = "shiro authencation 系统认证 执行异常";
    public static String msg_shiro_authorization_fail = "当前用户权限不足";
    public static String msg_shiro_authentication_fail = "用户名或密码验证失败";
    public static String msg_shiro_unAuthorization = "需要进行授权验证";
    public static String msg_shiro_unAuthentication = "需要进行系统认证";


    //Redis表名
    public static String redis_base_account = "Accounts";
    //
    public static String redis_base_token = "AccountTokens";
    //
    public static String redis_base_salt = "TokenSalts";
    //
    public static String redis_base_verify_code = "VerifyCodes";
//    public static String redis_base_RolePermission = "RolePermission"; //存放 '角色' 和 '权限'
//    public static String redis_base_Token = "Token"; //存放用户token信息
//    public static String redis_base_Token_Salt = "Token_Salt"; //存放用于token 的 salt值
//    public static String redis_base_Username = "Username"; //存放用户账号
//    public static String redis_user_MailVerificationCode = "MailVerificationCode";
//    public static String redis_user_ArticleStatusCount = "ArticleStatusCount";
//    public static String redis_user_LikeInfo = "LikeInfo"; //用户是否对某篇文章喜欢信息记录
//    public static String redis_user_CollectInfo = "CollectInfo"; //用户是否收藏谋篇文章记录
//    public static String redis_user_MarkedInfo = "MarkedInfo"; //存放关注信息
//    public static String redis_user_FansInfo = "FansInfo"; //存放关注信息
//    public static String redis_socket_ConnectInfo = "Socket_ConnectInfo";   //socket链接地址
//    public static String redis_socket_ConnectInfo_onLine = "Socket_ConnectInfo_onLine";   //当前在线socket链接地址
//    public static String redis_message_System = "Message_System";  //系统消息（系统通知等）
//    public static String redis_message_Reply = "Message_Reply";   //回复我的消息（回复，评论等）
//    public static String redis_message_Personal = "Message_Personal"; //私聊消息

    //资源存放路径
    public static String resources_article_picture = "image/article_image/";
    public static String resources_diary_picture = "image/diary_image/";

}
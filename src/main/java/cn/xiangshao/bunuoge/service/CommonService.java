package cn.xiangshao.bunuoge.service;



import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblLinkInfo;

import java.util.Map;

/**
 * @author xiangshao
 * @date 2019/3/5 14:14
 * @describe
 */

public interface CommonService {

    /**
     * 用户登录
     */
    ResponseResult login(Map<String, Object> params);

    /**
     * 用户注册
     */
    ResponseResult register(Map<String, Object> params);

    /**
     * token 刷新
     */
    ResponseResult refreshToken(String token);

    /**
     * 账号(邮箱)是否已存在
     */
    ResponseResult checkUsername(String username);


    /**
     * 新增网站链接信息
     */
    ResponseResult addLinkInfo(TblLinkInfo linkInfo);
    /**
     * 删除网站链接信息
     */
    ResponseResult deleteLinkInfo(TblLinkInfo linkInfo);
    /**
     * 更新网站链接信息
     */
    ResponseResult updateLinkInfo(TblLinkInfo linkInfo);
    /**
     * 获取网站底部的链接信息
     */
    ResponseResult getLinkInfo();


//    @Autowired
//    CommonDao commonDao;
//    @Autowired
//    RedisService redisService;
//    @Autowired
//    UserService userService;
//
//    //获取首页海报图片信息
//    public List<String> getPosterList(){
//        return commonDao.getPosterList();
//    }
//
//    //提交反馈意见
//    public boolean writeFeedBackMessage(TblFeedBackInfo feedInfo){
//        return commonDao.writeFeedBackMessage(feedInfo);
//    }
//
//    //通过邮箱验证码实现重置用户密码
//    public boolean resetPassword(String username,String email,String emailCode) {
//        String redis_emailCode = redisService.get_MailVerificationCode(email);
//        TblAccountInfo userInfo = userService.getUserInfo(username);
//        if (userInfo !=null && redis_emailCode.equals(emailCode) && userInfo.getEmail().equals(email)){
//            //获取新的随机数
//            String new_pw_salt = JwtUtil.generateSalt();
//            //生成新的加密密码
//            String new_pw = new Sha256Hash("123456789",new_pw_salt).toHex();
//            //然后形生成新的token(并将 salt 和 token 保存到redis中,不然重置后，验证的salt 和 token 还是 以前redis中保存的，不过这里不用返回给前端
//            String resetToken =  userService.generateJwtToken(userInfo.getOpenID(),new_pw_salt);
//            //然后立即将redis中的验证码删除
//            redisService.delete_MailVerificationCode(userInfo.getEmail());
//            return commonDao.resetPassword(new_pw,new_pw_salt,email);
//        }
//        return false;
//    }
//
//    //获取网站底部链接信息
//    public List<TblLinkInfo> getBottomNavBar(){
//       return commonDao.getBottomNavBar();
//    }

}

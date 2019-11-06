package cn.xiangshao.bunuoge.serviceImpl;

import cn.xiangshao.bunuoge.Utils.JwtUtil;
import cn.xiangshao.bunuoge.Utils.Status;
import cn.xiangshao.bunuoge.entity.request.TblAccountInfo;
import cn.xiangshao.bunuoge.entity.request.TblUserInfo;
import cn.xiangshao.bunuoge.entity.response.ArticleInfoCountResult;
import cn.xiangshao.bunuoge.mapper.AccountInfoMapper;
import cn.xiangshao.bunuoge.mapper.AdminMapper;
import cn.xiangshao.bunuoge.mapper.UserInfoMapper;
import cn.xiangshao.bunuoge.service.RedisService;
import com.alibaba.fastjson.JSON;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initInfos() {

        //初始化一————系统启动时，固定初始化一个超级管理员账户
        Map<String,Object> params = new HashMap<>();
        params.put("username", Status.superAdminUsername);
        if (accountInfoMapper.getAccountInfo(params) == null) {
            //如果当前不存在 Status 中预存的超级管理员，则注册一个

            String salt_password = JwtUtil.generateSalt();
            String encrypt_password = new Sha256Hash("123456789",salt_password).toHex();
            // **************** 账号信息部分 *******************
            TblAccountInfo accountInfo = new TblAccountInfo();
            accountInfo.setUsername(Status.superAdminUsername);
            accountInfo.setEncryptPassword(encrypt_password);
            accountInfo.setSalt(salt_password);
            accountInfo.setStatus("1");
            accountInfo.setCreatTime(LocalDate.now().toString());
            accountInfo.setRole("1");
            // 权限部分待定
            // tblAccountInfo.setPermission("1");
            accountInfoMapper.add(accountInfo);

            // ******************* 用户信息部分 ********************
            TblUserInfo userInfo = new TblUserInfo();
            userInfo.setNickname("布诺阁官方管理员");
            userInfo.setAvatar(JSON.parseArray("['/avatar/superAdmin_avatar.jpg']").toString());
            userInfo.setOpenId(accountInfo.getOpenId());
            userInfo.setEmail(Status.superAdminUsername);
            userInfo.setGender("3");
            userInfoMapper.add(userInfo);
        }

        //初始化二————记录用户账号
        redisTemplate.delete(Status.redis_base_account);
        List<String> accountList = adminMapper.initAccountInfo();
        for (String username : accountList){
            this.saveAccount(username);
        }

    }

    @Override
    public void saveAccount(String username) {
        redisTemplate.opsForSet().add(Status.redis_base_account,username);
    }

    @Override
    public Boolean checkUsername(String username){
        return redisTemplate.opsForSet().isMember(Status.redis_base_account,username);
    }

    @Override
    public void saveSalt(String openId, String token_salt) {
        redisTemplate.opsForHash().put(Status.redis_base_salt,openId,token_salt);
    }

    @Override
    public String getSalt(String openId) {
        return String.valueOf(redisTemplate.opsForHash().get(Status.redis_base_salt,openId));
    }

    @Override
    public void saveTokens(String openId, String token) {
        redisTemplate.opsForHash().put(Status.redis_base_token,openId,token);
    }

    @Override
    public void deleteTokenBatch(String[] openIds) {
        for (String openId : openIds){
            redisTemplate.opsForHash().delete(Status.redis_base_token,openId);
        }
    }

    @Override
    public Boolean verifyTokenRefresh(String openId,String expireToken) {
        String savedToken = String.valueOf(redisTemplate.opsForHash().get(Status.redis_base_token,openId));
        return savedToken.equals(expireToken);
    }

    @Override
    public void saveVerifyCode(String userEmail, String code) {
        redisTemplate.opsForHash().put(Status.redis_base_verify_code,userEmail,code);
    }

    @Override
    public String getVerifyCode(String username) {
        return String.valueOf(redisTemplate.opsForHash().get(Status.redis_base_verify_code,username));
    }

    @Override
    public void saveArticleInfoCount(ArticleInfoCountResult countResult) {

    }

    @Override
    public ArticleInfoCountResult getArticleInfoCount(String articleId, String type) {
        return null;
    }

    @Override
    public void clickLike(String articleId, String openId, boolean executeType) {

    }

    @Override
    public void clickCollection(String articleId, String openId, boolean executeType) {

    }

    @Override
    public void clickMark(String openId, String markedId, boolean executeType) {

    }

    @Override
    public Object clickedLikeAndCollection(String articleId, String openId) {
        return null;
    }

    @Override
    public void readSystemMessage(String openId, String messageId) {

    }

    @Override
    public Boolean checkSystemMessage(String openId, String messageId) {
        return null;
    }

    @Override
    public void addUnreadMessage(String openId, String messageId, String type) {

    }

    @Override
    public void readReplyMessage(String openId, String messageId, String type) {

    }

    @Override
    public Object getUnReadMessageCount(String openId, String type) {
        return null;
    }
}

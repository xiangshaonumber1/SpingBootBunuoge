package cn.xiangshao.bunuoge.serviceImpl;

import cn.xiangshao.bunuoge.Utils.JwtUtil;
import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblAccountInfo;
import cn.xiangshao.bunuoge.entity.request.TblLinkInfo;
import cn.xiangshao.bunuoge.entity.request.TblUserInfo;
import cn.xiangshao.bunuoge.mapper.CommonMapper;
import cn.xiangshao.bunuoge.service.AccountService;
import cn.xiangshao.bunuoge.service.CommonService;
import cn.xiangshao.bunuoge.service.RedisService;
import cn.xiangshao.bunuoge.service.UserInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RedisService redisService;


    /**
     * 用户登录验证
     * 正常情况下，params中有username(邮箱即为默认账号) 和 encryptPassword
     */
    @Transactional(rollbackFor = SQLException.class)
    @Override
    public ResponseResult login(Map<String,Object> params){

          TblAccountInfo accountInfo = accountService.getAccountInfo(params);
          if (accountInfo==null){
              // 用户名或密码错误或不存在,返回操作失败(拒绝信息)
              return ResponseResult.Refuse();
          }

          //账号信息存在，验证密码
          String password = params.get("password").toString();
          String encrypt_password = new Sha256Hash(password,accountInfo.getSalt()).toHex();

          if (accountInfo.getEncryptPassword().equals(encrypt_password)){
              log.info("账号为【"+accountInfo.getUsername()+"】，openId为【"+accountInfo.getOpenId()+"】的用户验证成功，记录新的登录信息......");
              //验证成功后，重置密码信息
              accountService.updatePassword(params);

              //重新生成token
              String token = this.generateToken(accountInfo.getOpenId());

              Map<String,Object> userInfoParams = new HashMap<>();
              userInfoParams.put("openId",accountInfo.getOpenId());
              TblUserInfo userInfo = userInfoService.getUserInfo(userInfoParams);

              //然后准备数据返回
              JSONObject data = new JSONObject();
              data.put("token",token);
              data.put("openId",userInfo.getOpenId());
              data.put("avatar",userInfo.getAvatar());
              data.put("nickname",userInfo.getNickname());

              //最后保存token到redis。
              redisService.saveTokens(accountInfo.getOpenId(),token);

              return ResponseResult.Success().setData(data);
          }else {
              //验证密码失败
              return ResponseResult.Refuse();
          }
    }

    /**
     * 新用户注册
     * params中,应该包括
     * username 注册邮箱（登录名），
     * password 明文密码
     * verifyCode 邮箱验证码
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult register(Map<String, Object> params){

        String username = params.get("username").toString();
        String password = params.get("password").toString();
        String verifyCode = params.get("verifyCode").toString();

        String redisVerifyCode = redisService.getVerifyCode(username);

        if (verifyCode.equals(redisVerifyCode)){
            //**************************** 用户信息 和 账号信息 处理部分 ****************************************
            // *注： 该salt只用于密码加密，不用于生成token
            String salt_password = JwtUtil.generateSalt();
            String nickname = "小博主_"+ System.nanoTime();
            String encrypt_password = new Sha256Hash(password,salt_password).toHex();

            //紧接着，保存用户信息到数据库，保存到缓存安排在生成token时，和token一起保存到缓存中
            TblAccountInfo accountInfo = new TblAccountInfo();
            accountInfo.setUsername(username);
            accountInfo.setEncryptPassword(encrypt_password);
            accountInfo.setSalt(salt_password);
            // *注: 账号状态默认正常
            accountInfo.setStatus("1");
            accountInfo.setCreatTime(LocalDate.now().toString());
            // *注：默认身份为普通用户
            accountInfo.setRole("3");
            accountService.add(accountInfo);

            TblUserInfo userInfo = new TblUserInfo();
            userInfo.setOpenId(accountInfo.getOpenId());
            userInfo.setNickname(nickname);
            // *注：默认为3（保密）
            userInfo.setGender("3");
            userInfo.setAvatar(JSON.parseArray("['/avatar/default_avatar.jpg']").toString());
            // *注：默认邮箱注册，所以用户名(账号),同邮箱
            userInfo.setEmail(username);
            userInfoService.add(userInfo);

            //然后将注册账号保存在Redis缓存中，方便用户注册时，检测该账号是否可用
            redisService.saveAccount(username);

            //**************************************** token 处理部分 *****************************************
            String token = this.generateToken(accountInfo.getOpenId());
            redisService.saveTokens(accountInfo.getOpenId(),token);
            //然后准备数据返回
            JSONObject data = new JSONObject();
            data.put("token",token);
            data.put("openId",userInfo.getOpenId());
            data.put("avatar",userInfo.getAvatar());
            data.put("nickname",userInfo.getNickname());
            return ResponseResult.Success().setData(data);
        }else {
            return ResponseResult.Refuse().setMsg("验证码不正确！");
        }
    }

    /**
     * token 刷新功能
     * 当前最新过期token,才能换取token刷新
     */
    @Override
    public ResponseResult refreshToken(String old_token) {
        String openId = JwtUtil.getOpenId(old_token);
        //检查当前过期token,是否是
        Boolean result = redisService.verifyTokenRefresh(openId,old_token);
        if (result){
            //重新生成token,并保存到redis中，最后返回数据到前端
            String new_token = this.generateToken(openId);
            redisService.saveTokens(openId,new_token);
            return ResponseResult.Success().setData(new_token);
        }else {
            //拒绝请求刷新，返回登录
            return ResponseResult.Refuse();
        }
    }

    /**
     * 检查账户名是否存在
     * result：存在则为true,不存在则为false
     */
    @Override
    public ResponseResult checkUsername(String username) {
        boolean result = redisService.checkUsername(username);
        return ResponseResult.Success().setStatus(result);
    }

    /**
     * 新增网站链接
     */
    @Override
    public ResponseResult addLinkInfo(TblLinkInfo linkInfo) {
        commonMapper.addLinkInfo(linkInfo);
        return ResponseResult.Success();
    }

    /**
     * 删除指定的网站链接
     */
    @Override
    public ResponseResult deleteLinkInfo(TblLinkInfo linkInfo) {
        commonMapper.deleteLinkInfo(linkInfo);
        return ResponseResult.Success();
    }

    /**
     * 修改指定的连接
     */
    @Override
    public ResponseResult updateLinkInfo(TblLinkInfo linkInfo) {
        commonMapper.updateLinkInfo(linkInfo);
        return ResponseResult.Success();
    }

    /**
     * 获取所有链接信息
     */
    @Override
    public ResponseResult getLinkInfo() {
        List<TblLinkInfo> data = commonMapper.getLinkInfo();
        return ResponseResult.Success().setData(data);
    }

    /**
     * 通过用户openId生成token并保存token_salt到redis中
     */
    private String generateToken(String openId){
        String salt = JwtUtil.generateSalt();
        redisService.saveSalt(openId,salt);
        return JwtUtil.generateToken(openId,salt);
    }

}

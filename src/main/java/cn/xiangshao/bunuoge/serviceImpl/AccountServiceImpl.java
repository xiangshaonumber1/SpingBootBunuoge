package cn.xiangshao.bunuoge.serviceImpl;

import cn.xiangshao.bunuoge.Utils.JwtUtil;
import cn.xiangshao.bunuoge.entity.request.TblAccountInfo;
import cn.xiangshao.bunuoge.mapper.AccountInfoMapper;
import cn.xiangshao.bunuoge.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountInfoMapper accountInfoMapper;

    @Override
    public void add(TblAccountInfo accountInfo) {
        accountInfoMapper.add(accountInfo);
    }

    @Override
    public TblAccountInfo getAccountInfo(Map<String, Object> params) {
        return accountInfoMapper.getAccountInfo(params);
    }

    /**
     * 获取openId和密码，生成salt，加密密码 并 保存
     */
    @Override
    public void updatePassword(Map<String,Object> params) {
        String password = params.get("password").toString();
        String openId = params.get("openId").toString();
        String salt = JwtUtil.generateSalt();
        String encrypt_password = new Sha256Hash(password,salt).toHex();
        accountInfoMapper.updatePassword(openId,encrypt_password,salt);
    }

    @Override
    public void resetPassword(String[] openIds) {
        //重置密码，默认为123456789
        for (String openId : openIds){
            String salt = JwtUtil.generateSalt();
            String encrypt_password = new Sha256Hash("123456789",salt).toHex();
            accountInfoMapper.updatePassword(openId,encrypt_password,salt);
        }
    }

}

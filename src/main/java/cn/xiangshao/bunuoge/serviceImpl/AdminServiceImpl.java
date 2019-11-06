package cn.xiangshao.bunuoge.serviceImpl;


import cn.xiangshao.bunuoge.Utils.JwtUtil;
import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblFeedBackInfo;
import cn.xiangshao.bunuoge.entity.response.TblArticleInfoResult;
import cn.xiangshao.bunuoge.entity.response.TblUserInfoResult;
import cn.xiangshao.bunuoge.mapper.AdminMapper;
import cn.xiangshao.bunuoge.service.AdminService;
import cn.xiangshao.bunuoge.service.RedisService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * admin 管理员相关接口实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RedisService redisService;

    @Override
    public void dealRole(String openId, String role) {
        adminMapper.dealRole(openId,role);
    }

    @Override
    public void dealPermission(String openId, String permission) {
        adminMapper.dealPermission(openId,permission);
    }

    @Override
    public PageInfo<TblFeedBackInfo> getFeedBackMessages(Map<String, Object> params) {
        return adminMapper.getFeedBackMessages(params);
    }

    @Override
    public void dealFeedBackMessage(String feedId, String status) {
        adminMapper.dealFeedBackMessage(feedId,status);
    }

    @Override
    public PageInfo<TblUserInfoResult> getAdminUserInfos(Map<String, Object> params) {
        return adminMapper.getAdminUserInfos(params);
    }


    @Override
    public PageInfo<TblArticleInfoResult> getAdminArticleInfos(Map<String, Object> params) {
        return adminMapper.getAdminArticleInfos(params);
    }

    @Override
    public void dealArticle(String[] articleIds, String status) {
        adminMapper.dealArticle(articleIds,status);
    }

    @Override
    public ResponseResult resetPasswordBatch(String[] openIds) {
        //获取新的随机数,并采用固定密码+随机数加密的方式生成新密码
        String salt = JwtUtil.generateSalt();
        String password = new Sha256Hash("123456789",salt).toHex();
        //数据库执行修改
        adminMapper.restPasswordBatch(openIds,salt,password);
        //这里还需要做一些删除redis中，旧的用户信息
        redisService.deleteTokenBatch(openIds);
        return ResponseResult.Success();
    }


    @Override
    public void sendMessage(Map<String, Object> params) {

    }
}

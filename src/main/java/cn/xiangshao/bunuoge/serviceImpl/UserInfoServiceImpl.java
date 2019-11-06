package cn.xiangshao.bunuoge.serviceImpl;


import cn.xiangshao.bunuoge.entity.request.TblUserInfo;
import cn.xiangshao.bunuoge.mapper.UserInfoMapper;
import cn.xiangshao.bunuoge.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public void add(TblUserInfo userInfo) {
        userInfoMapper.add(userInfo);
    }

    @Override
    public TblUserInfo getUserInfo(Map<String, Object> params) {
        return userInfoMapper.getUserInfo(params);
    }
}

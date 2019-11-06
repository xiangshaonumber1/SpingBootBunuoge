package cn.xiangshao.bunuoge.service;



import cn.xiangshao.bunuoge.entity.request.TblAccountInfo;

import java.util.Map;

public interface AccountService {

    /**
     * 新增账号信息
     */
    void add(TblAccountInfo accountInfo);

    /**
     *根据参数条件，获取对饮的账户信息
     */
    TblAccountInfo getAccountInfo(Map<String, Object> params);

    /**
     * 根据参数条件，
     * 通过不同的方式，实现修改对应的账户密码
     */
    void updatePassword(Map<String, Object> params);

    /**
     * 重置指定用户密码
     */
    void resetPassword(String[] openIds);
}

package cn.xiangshao.bunuoge.service;

import cn.xiangshao.bunuoge.Utils.ResponseResult;
import cn.xiangshao.bunuoge.entity.request.TblFeedBackInfo;
import cn.xiangshao.bunuoge.entity.response.TblArticleInfoResult;
import cn.xiangshao.bunuoge.entity.response.TblUserInfoResult;
import com.github.pagehelper.PageInfo;


import java.util.Map;

/**
 * @author xiangshao
 * @date 2019/3/18 14:03
 * @describe 管理员操作
 */

public interface AdminService {

    /**
     * 处理用户角色
     * @param openId 目标用户ID
     * @param role 修改结果
     */
    void dealRole(String openId, String role);

    /**
     * 处理用户权限
     * @param openId 目标用户ID
     * @param permission 修改结果
     */
    void dealPermission(String openId, String permission);

    /**
     * 按需查询相用户关回馈信息
     * @param params 查询条件
     * @return 返回结果集
     */
    PageInfo<TblFeedBackInfo> getFeedBackMessages(Map<String, Object> params);

    /**
     * 处理回馈消息
     * @param feedId 目标ID
     * @param status 处理结果
     */
    void dealFeedBackMessage(String feedId, String status);

    /**
     * 管理员查看用户信息
     * @param params 查询条件
     * @return 返回结果集
     */
    PageInfo<TblUserInfoResult> getAdminUserInfos(Map<String, Object> params);

    /**
     * 查询文章审核进度
     * @param params 查询条件
     * @return 返回结果集
     */
    PageInfo<TblArticleInfoResult> getAdminArticleInfos(Map<String, Object> params);

    /**
     * 处理文章审核
     * @param articleIds 目标ID集
     * @param status 处理结果
     */
    void dealArticle(String[] articleIds, String status);

    /**
     * 重置用户密码
     * @param openIds 目标ID集
     */
    ResponseResult resetPasswordBatch(String[] openIds);

    /**
     * 发送系统邮件
     * @param params 系统邮件参数
     */
    void sendMessage(Map<String, Object> params);


    //修改指定用户的角色身份 或 权限
//    public boolean update_role_or_permission(String aim_openID, String new_value, String type){
//        return adminDao.update_role_or_permission(aim_openID,new_value,type);
//    }

    //获取反馈信息
//    public Page<FeedBackMessage> getFeedBackMessage(){
//        return adminDao.getFeedBackMessage();
//    }

    //修改反馈信息处理状态
//    public boolean updateStatus(String feedID,String status){
//        return adminDao.updateStatus(feedID,status);
//    }

    //获取用户基本信息、权限和角色
//    public AdminUserRoleAndPermission getInfo(int page, int pageCount, String key_word){
//        PageHelper.startPage(page,pageCount,true,true,true);
//        Page<AdminUserRoleAndPermission.UserPart> userPartList = adminDao.getUserPart(key_word);
//        AdminUserRoleAndPermission result  = adminDao.getInfo();
//        result.setUserPartInfoList(userPartList);
//        return result;
//    }

    //管理员获取文章审核信息
//    public AdminArticleInfo getAdminArticleInfo(int page, String key_word, int pageCount){
//        PageHelper.startPage(page,pageCount,true,true,true);
//        Page<AdminArticleInfo.AdminArticle> adminArticleList = adminDao.getAdminArticleInfo(key_word);
//        String dictionary_articleStatus = adminDao.getDictionary("articleStatus");
//        AdminArticleInfo result = new AdminArticleInfo();
//        return result.setAdminArticleList(adminArticleList).setDictionary_articleStatus(dictionary_articleStatus);
//    }

    //管理员修改文章审核状态
//    public boolean updateArticleStatus(String articleID,String new_status){
//        return adminDao.updateArticleStatus(articleID,new_status);
//    }

    //重置用户密码
//    public boolean resetPassword(String aim_openID){
//        String new_salt_sql = JwtUtil.generateSalt(); //获取新的随机数
//        String new_pw = new Sha256Hash("123456789",new_salt_sql).toHex();//生成新的加密密码
//        //然后形生成新的token(并将 salt 和 token 保存到redis中,不然重置后，验证的salt 和 token 还是 以前redis中保存的，不过这里不用返回给前端
//        String resetToken =  userService.generateJwtToken(aim_openID,new_salt_sql);
//        return userDao.update_pw(aim_openID,new_pw,new_salt_sql);
//    }

    //编辑底部链接信息
//    public boolean edit_link(Link linkInfo ,String operation){
//        switch (operation){
//            case "update":
//                return adminDao.updateLink(linkInfo);
//            case "delete":
//                return adminDao.deleteLink(linkInfo.getLinkID());
//            case "insert":
//                return adminDao.insertLink(linkInfo);
//            default:
//                return false;
//        }
//    }

    //创建并向在线用户发送系统消息
//    public void sendSystemMessage(String sendType,String title,String content,String acceptor){
//        nettySocketIoService.notification_system_message(sendType,title,content,acceptor);
//    }

}

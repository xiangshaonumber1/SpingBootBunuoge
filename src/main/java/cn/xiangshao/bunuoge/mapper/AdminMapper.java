package cn.xiangshao.bunuoge.mapper;


import cn.xiangshao.bunuoge.entity.request.TblFeedBackInfo;
import cn.xiangshao.bunuoge.entity.response.TblArticleInfoResult;
import cn.xiangshao.bunuoge.entity.response.TblUserInfoResult;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author xiangshao
 * @date 2019/3/18 14:17
 * @describe 管理员相关操作
 */
public interface AdminMapper {

    @Select("select username from tbl_account_info")
    List<String> initAccountInfo();

    @Update("update tbl_account_info set role = #{role} where open_id = #{openId}")
    void dealRole(@Param("openId") String openId, @Param("role") String role);

    @Update("update tbl_account_info set permission =#{permission} where open_id = #{openId}")
    void dealPermission(@Param("openId") String openId, @Param("role") String role);

    PageInfo<TblFeedBackInfo> getFeedBackMessages(Map<String, Object> params);

    @Update("update tbl_feedback_info set status = #{status} where feed_id = #{feedId}")
    void dealFeedBackMessage(@Param("feedId") String feedId, @Param("status") String status);

    PageInfo<TblUserInfoResult> getAdminUserInfos(Map<String, Object> params);

    PageInfo<TblArticleInfoResult> getAdminArticleInfos(Map<String, Object> params);

    void dealArticle(@Param("articleIds") String[] articleIds, @Param("status") String status);

    void restPasswordBatch(@Param("openIds") String[] openIds, @Param("salt") String salt, @Param("password") String password);


//    @UpdateProvider(type = Admin_SQL.class,method = "update_role_or_permission")
//    boolean update_role_or_permission(@Param("aim_openID")String aim_openID,@Param("new_value")String new_value,@Param("type")String type);
//
//    //查询反馈信息
//    @Select("SELECT * FROM feed_back_message")
//    Page<FeedBackMessage> getFeedBackMessage();
//
//    //修改反馈信息处理状态
//    @Update("UPDATE feed_back_message SET status = #{status} WHERE feedID = #{feedID}")
//    boolean updateStatus(@Param("feedID") String feedID,@Param("status") String status);
//
//    //获取单个指定key_name的字典信息
//    @Select("SELECT content FROM dictionary WHERE key_name = #{key_name} ")
//    String getDictionary(@Param("key_name") String key_name);
//
//    //获取权限和角色字典信息和用户部分信息
//    @Select("SELECT (SELECT content FROM dictionary WHERE key_name = 'role') AS dictionary_role,(SELECT content FROM dictionary WHERE key_name = 'permission') AS dictionary_permission")
//    AdminUserRoleAndPermission getInfo();
//
//    //查询用户部分信息、权限和角色分配
////    @Select("SELECT A.openID,A.nickname,A.email,B.role,B.permission FROM user A LEFT JOIN role_permission B ON A.openID = B.openID WHERE A.openID like '%#{key_word}%' OR A.nickname like '%#{key_word}%' OR A.email like '#{key_word}' ")
//    @SelectProvider(type = Admin_SQL.class,method = "getUserPart")
//    Page<AdminUserRoleAndPermission.UserPart> getUserPart(String key_word);
//
//    //管理员查询文章审核信息
//    @SelectProvider(type = Admin_SQL.class,method = "getAdminArticleInfo")
//    Page<AdminArticleInfo.AdminArticle> getAdminArticleInfo(String key_word);
//
//    //修改指定文章的审核状态
//    @Update("UPDATE article SET status = #{new_status} WHERE articleID = #{articleID}")
//    boolean updateArticleStatus(String articleID,String new_status);
//
//    //修改指定link信息
//    @Update("UPDATE link SET type= #{type},label=#{label},website=#{website} WHERE linkID=#{linkID} ")
//    boolean updateLink(Link linkInfo);
//
//    //删除指定link信息
//    @Delete("DELETE FROM link WHERE linkID=#{linkID}")
//    boolean deleteLink(@Param("linkID") String linkID);
//
//    //新增link信息
//    @Insert("INSERT INTO link(type,label,website) VALUES(#{type},#{label},#{website})")
//    boolean insertLink(Link linkInfo);
//
//    @Insert("INSERT INTO system_message(title,content) VALUES(#{title},#{content})")
//    void insertSystemMessage(@Param("title")String title, @Param("content") String content);

}

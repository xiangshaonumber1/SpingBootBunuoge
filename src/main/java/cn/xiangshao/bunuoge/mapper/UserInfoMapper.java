package cn.xiangshao.bunuoge.mapper;

import cn.xiangshao.bunuoge.SqlProvider.User_SQL;
import cn.xiangshao.bunuoge.Utils.SqlProvider;
import cn.xiangshao.bunuoge.entity.request.SystemMessage;
import cn.xiangshao.bunuoge.entity.request.TblAccountInfo;
import cn.xiangshao.bunuoge.entity.request.TblUserInfo;
import cn.xiangshao.bunuoge.entity.response.MessageDetails;
import cn.xiangshao.bunuoge.entity.response.UserInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.Map;

public interface UserInfoMapper {

    @Insert("insert into tbl_user_info values(#{openId},#{nickname},#{gender},#{avatar},#{email},#{phone},#{myDescribe},#{wishDescribe})")
    void add(TblUserInfo userInfo);

    TblUserInfo getUserInfo(Map<String, Object> params);

    //通过openID查询UserInfo信息，其中不包括
//    @Select("SELECT * FROM user WHERE openID=#{openID}")
    @SelectProvider(type = SqlProvider.class,method = "getUserInfoByopenID")
    UserInfo getUserInfoByopenID(@Param("openID") String openID);

    //通过openID查询用户信息，包含加密密码，盐值等
    @Select("SELECT * FROM user WHERE openID=#{openID}")
    TblAccountInfo getUserByopenID(@Param("openID") String openID);

    //因为使用邮箱注册，所以用户名即邮箱
    @Select("SELECT username FROM user WHERE username=#{email}")
    String IsExist_mail(@Param("email") String email);

    //通过username查询用户所拥有的权限和角色
    @Select("SELECT * FROM role_permission WHERE openID = #{openID}")
//    RolePermission selectRolePermissionByopenID(@Param("openID") String openID);

    //保存用户新的密码
    @Update("UPDATE user SET encrypt = #{encrypt},salt = #{salt} WHERE openID = #{openID}")
    boolean update_pw(@Param("openID") String openID, @Param("encrypt") String encrypt, @Param("salt") String salt);

    //注册用户信息
    @Insert("INSERT INTO user(username,encrypt,salt,nickname,email,userIcon) VALUES(#{username},#{encrypt},#{salt},#{nickname},#{email},#{userIcon})")
    @Options(useGeneratedKeys = true,keyProperty = "openID",keyColumn = "openID")
    void register(TblAccountInfo user);

    //为新注册用户添加默认user权限
    @Insert("INSERT INTO role_permission(openID,role) VALUES(#{openID},'普通用户')")
    void add_role_permission(@Param("openID") String openID);

    @Update("UPDATE user SET email=#{newEmail} WHERE openID = #{openID}")
    int update_email(@Param("openID") String openID, @Param("newEmail") String newEmail);

    //修改用户基本信息
    @Update("UPDATE user SET nickname=#{nickname},gender=#{gender},wishCard=#{wishCard},myDescribe=#{myDescribe} WHERE openID =#{openID}")
    int updateUserInfo(UserInfo userInfo);

    //修改用户基本头像
    @Update("UPDATE user SET userIcon = #{userIcon} WHERE openID = #{openID}")
    int updateUserIcon(@Param("userIcon") String userIcon, @Param("openID") String openID);

    //获取指定用户注册后的所有系统信息
    @Select("SELECT A.* FROM system_message A left join user B on (A.time>B.time) WHERE B.openID = #{openID} ORDER BY A.time desc")
    Page<SystemMessage> getSystemMessage(@Param("openID") String openID);

    //获取指定类型的消息详情
    @SelectProvider(method = "getReplyMessageDetails",type = User_SQL.class)
    Page<MessageDetails> getReplyMessageDetails(@Param("openID") String openID);
}
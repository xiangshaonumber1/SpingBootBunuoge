package cn.xiangshao.bunuoge.mapper;

import cn.xiangshao.bunuoge.entity.request.TblAccountInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface AccountInfoMapper {

    @Options(useGeneratedKeys = true,keyColumn = "open_id",keyProperty = "openId")
    @Insert("Insert into tbl_account_info values(#{openId},#{username},#{encryptPassword},#{salt},#{status},#{creatTime},#{role},#{permission})")
    void add(TblAccountInfo accountInfo);

    TblAccountInfo getAccountInfo(Map<String, Object> params);

    @Update("update tbl_account_info set encrypt_password =#{encryptPassword}, salt=#{salt} where open_id=#{openId}")
    void updatePassword(@Param("openId") String openId, @Param("encryptPassword") String encryptPassword, @Param("salt") String salt);
}

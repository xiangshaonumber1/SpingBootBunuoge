package cn.xiangshao.bunuoge.mapper;


import cn.xiangshao.bunuoge.entity.request.TblFeedBackInfo;
import cn.xiangshao.bunuoge.entity.request.TblLinkInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xiangshao
 * @date 2019/3/5 14:15
 * @describe
 */
public interface CommonMapper {

    //获取首页海报图片信息
    @Select("SELECT posterSrc FROM poster")
    List<String> getPosterList();

    //提交反馈意见
    @Insert("INSERT INTO feed_back_message(openID,content,contactType,contactInfo,feedbackType,status)" +
            "VALUES (#{openID},#{content},#{contactType},#{contactInfo},#{feedbackType},'待处理')")
    boolean writeFeedBackMessage(TblFeedBackInfo feedInfo);

    //重置用户密码
    @Update("UPDATE user SET encrypt = #{encrypt},salt = #{salt} WHERE email = #{email}")
    boolean resetPassword(@Param("encrypt") String encrypt, @Param("salt") String salt, @Param("email") String email);

    //网站底部链接-新增
    @Insert("insert into tbl_link_info values(#{type},#{describe},#{website},#{creatTime})")
    void addLinkInfo(TblLinkInfo linkInfo);

    //网站底部链接-删除
    @Delete("delete tbl_link_info where id = #{id}")
    void deleteLinkInfo(TblLinkInfo linkInfo);

    //网站底部链接-修改
    @Update("update tbl_link_info set describe = #{describe},web")
    void updateLinkInfo(TblLinkInfo linkInfo);

    //网站底部链接-查询所有
    @Select("select * from tbl_link_info")
    List<TblLinkInfo> getLinkInfo();


}

package cn.xiangshao.bunuoge.SqlProvider;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @author xiangshao
 * @date 2019/3/18 14:21
 * @describe admin相关操作动态sql
 */
@Slf4j
public class Admin_SQL {

    /**
     * 根据type的值去判断是更改role 还是 permission
     */
    public String update_role_or_permission(String aim_openID,String new_value,String type){
        String sql = new SQL(){
            {
                UPDATE("role_permission");
                //如果是修改角色身份
                if (type.equals("role")){
                    SET(" role = #{new_value} ");
                }
                //如果是修改权限
                else if (type.equals("permission")){
                    SET(" permission = #{new_value} ");
                }
                WHERE(" openID = #{aim_openID}");
            }
        }.toString();
        log.info(getClass()+"执行 sql :"+sql);
        return sql;
    }

    /**
     * 查询用户部分信息、权限和角色分配,可以按条件查询
     */
    public String getUserPart(String key_word){
//        SELECT A.openID,A.nickname,A.email,B.role,B.permission FROM user A LEFT JOIN role_permission B ON A.openID = B.openID
        String sql = new SQL(){
            {
                SELECT("A.openID,A.nickname,A.email,B.role,B.permission");
                FROM("user A LEFT JOIN role_permission B ON A.openID = B.openID");
                if (key_word!=null){
                    WHERE("(A.openID LIKE '%"+key_word+"%' OR A.nickname LIKE '%"+key_word+"%' OR A.email LIKE '%"+key_word+"%')" );
                }
            }
        }.toString();
        log.info(getClass()+"执行 sql :\n"+sql);
        return sql;
    }

    /**
     * 管理员查询文章审核信息
     */
    public String getAdminArticleInfo(String key_word){
        String sql = new SQL(){
            {
                SELECT("A.*,B.username,B.nickname,B.email");
                FROM("article A LEFT JOIN user B ON A.openID = B.openID");
                if (key_word!=null){
                    WHERE("(A.title LIKE '%"+key_word+"%' OR B.nickname LIKE '%"+key_word+"%' OR B.email LIKE '%"+key_word+"%' OR B.username LIKE '%"+key_word+"%')");
                }
            }
        }.toString();
        log.info(getClass()+"执行 sql :\n"+sql);
        return sql;
    }




}

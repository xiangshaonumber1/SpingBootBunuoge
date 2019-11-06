package cn.xiangshao.bunuoge.Utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * @author xiangshao
 * @date 2018/12/17 13:42
 * @describe 动态构建SQL语句
 */
@Slf4j
public class SqlProvider {

    /**
     * 文章作者实现动态删除文章(日记)
     */
    public String delete_article(String openID,String deleteID,String type){
        return new SQL(){
            {
                DELETE_FROM(type);
                WHERE("openID = "+openID);
                switch (type){
                    case "article":
                        WHERE(" articleID = "+deleteID);
                        break;
                    case "diary":
                        WHERE(" diaryID = "+deleteID);
                        break;
                }
            }
        }.toString();
    }

    /**
     * root管理和admin管理，实现动态可批量删除(一次只能删除一种类型文章)
     * @param openID 请求者ID
     * @param deleteIDArray 删除的文章或者日记的ID数组（同一时间，只能删除一种文章类型）
     * @param deleteAll 是否全部删除（一种文章类型）
     * @param type 文章类型：article（普通文章），diary（日记）,并且类型名同SQL表名
     */
    public String admin_delete(String openID, String deleteIDArray, boolean deleteAll,String type){
        return new SQL(){
            {
                DELETE_FROM(type);
                if (deleteAll){ //如果选择全部删除，则只需要确定文章类型和openID
                    WHERE("openID = "+openID);
                }else if (deleteIDArray!=null){ //如果是多选或者单选删除，需确定删除的ID和用户ID
                    String value = deleteIDArray.substring(1,deleteIDArray.length()-1);//去掉jsonarray的 [ 和 ]
                    value = "'"+ value +"'";
                    WHERE("FIND_IN_SET(id,"+value+") AND openID = "+openID);
                }
            }
        }.toString();
    }


    /**
     * 搜索满足关键字的文章信息
     */
    public String get_search_articleInfo(List key_value_list){
        String mysql =  new SQL(){
            {
                log.warn("输出 key_value_list : "+key_value_list);
                SELECT("user.nickname,user.userIcon,article.*");
                FROM("article,user");
                WHERE("user.openID = article.openID");
                AND();
                int count = key_value_list.size();
                StringBuilder where = new StringBuilder();
                for (Object key : key_value_list){
                    count--;
                    where.append("(article.title LIKE '%"+key.toString()+"%' OR article.content LIKE '%"+key.toString()+"%')");
                    if (count!=0){
                        where.append(" OR ");
                    }
                }
                WHERE(where.append("AND article.status <> '不通过'").toString());
            }
        }.toString();
        log.info("sql 语句为："+'\n'+mysql);
        return mysql;
    }

    /**
     * 搜索满足关键字的用户
     */
    public String get_search_userInfo(List key_value_list){
        String mysql = new SQL(){
            {
                SELECT("user.*");
                FROM("user");
                StringBuilder where = new StringBuilder();
                int count = key_value_list.size();
                for (Object key: key_value_list){
                    count--;
                    where.append("(nickname LIKE '%"+key+"%')");
                    if (count!=0){
                        where.append("OR");
                    }
                }
                WHERE(where.toString());
            }
        }.toString();
        log.info("sql 语句为："+'\n'+mysql);
        return mysql;
    }

    /**
     * 获得统计数据和个人基本信息
     */
    public String getUserInfoByopenID(String openID){
        return new SQL(){
            {
                SELECT("user.*," +
                        "(SELECT COUNT(*) FROM article WHERE openID=#{openID} AND type='original') AS originArticleCount," +
                        "(SELECT COUNT(*) FROM article WHERE openID=#{openID} AND type='reprint') AS translateArticleCount," +
                        "(SELECT COUNT(*) FROM article WHERE openID=#{openID} AND type='translate') AS reprintArticleCount," +
                        "(SELECT COUNT(*) FROM diary WHERE openID = #{openID} AND type = 'public') AS publicDiaryCount," +
                        "(SELECT COUNT(*) FROM diary WHERE openID = #{openID} AND type = 'private') AS privateDiaryCount");
                FROM("user");
                WHERE("user.openID =#{openID} ");
            }
        }.toString();
    }

    /**
     * 修改用户密码
     * @param encrypt 加密后的密码
     * @param salt salt值
     */
    public String update_encrypt(String userMail,String openID,String encrypt,String salt){
        return new SQL(){
            {
                UPDATE("user");
                SET("encrypt='"+encrypt+"',salt='"+salt+"'");

                    WHERE("openID = '"+openID+"'");

                    WHERE("email = '"+userMail+"'");

            }
        }.toString();
    }

}

package cn.xiangshao.bunuoge.SqlProvider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 所有需要动态查询的sql
 */
@Slf4j
public class SelectProvider {

    public String getFeedBackMessages(Map<String,Object> map){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM("feed_back_message");
                for (String key : map.keySet()) {
                    if (StringUtils.isNotBlank(map.get(key).toString())){
                        WHERE(" "+key+" = '"+map.get(key)+"' ");
                    }
                }
            }
        }.toString();
        log.info(getClass()+"执行 sql :"+sql);
        return sql;
    }


}

package cn.xiangshao.bunuoge.Utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

@Slf4j
public class RequestResponseUtil {

    /**
     * 通用Json返回格式,返回信息给客户端
     */
    public static void responseWrite(Object message, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = null;
        try {
            printWriter = WebUtils.toHttp(response).getWriter();
            ResponseResult data = (ResponseResult) message;
            String result = JSONObject.toJSONString(data,SerializerFeature.WriteMapNullValue);
            printWriter.print(result);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }finally {
            if (null != printWriter) {
                printWriter.close();
            }
        }
    }

}

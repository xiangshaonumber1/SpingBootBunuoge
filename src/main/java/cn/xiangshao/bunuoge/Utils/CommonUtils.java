package cn.xiangshao.bunuoge.Utils;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 常用通用型工具类
 */
@Slf4j
public class CommonUtils{

    private static final SmartChineseAnalyzer SMART_CHINESE_ANALYZER = new SmartChineseAnalyzer();

    //将Exception 捕捉到的错误信息转换为String，便于记录日志
    public static String GetTrace(Throwable t){
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

    //将上传的文件保存到本地服务器，并返回保存的地址
    public static JSONArray upload_files(HttpServletRequest request, String savePath){
        log.info("正在上传文件到服务器");
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file;
        BufferedOutputStream stream;
        String filepath = "C:/Projects/myblog/upload_files/"+savePath+"";
        File dest = new File(filepath);
        if (!dest.exists()){
            boolean wasSuccess = dest.mkdirs();
            if (!wasSuccess){
                log.error("创建路径失败");
            }
        }
        JSONArray fileArray = new JSONArray();
        for (int i = 0;i<files.size();i++){
            file = files.get(i);
            if (!file.isEmpty()){
                try {
                    //获取文件名(包括后缀名)
                    String filename = file.getOriginalFilename();
                    //获取文件类型,即文件后缀名
                    System.out.println("输出filename:"+filename);
                    String suffix_name = filename.substring(filename.lastIndexOf("."));
                    byte[] bytes = file.getBytes();
                    String uuid = UUID.randomUUID().toString().replace("-","");
                    //这里new File 中添加的三个参数分别是 filepath: 文件保存的路径; uuid: 用作文件名; suffixName: 是后缀类型
                    stream = new BufferedOutputStream(new FileOutputStream(new File(filepath + uuid + suffix_name)));
                    stream.write(bytes);
                    stream.close();
                    //ip地址由数据库默认添加
                    fileArray.add("/"+savePath+uuid+suffix_name);
                }catch (Exception e){
                    stream = null;
                    log.error("加载文件时出了错："+"\n"+GetTrace(e));
                    return null;
                }
            }else {
                log.info("第【"+i+"】个文件有为空");
            }
        }
        return fileArray;
    }

    //下载功能
    public Object download(HttpServletResponse response){
        return ResponseResult.Success();
    }

    //搜索-中文分词
    public static List key_split(String keyValue){
        List<String> result = new LinkedList<String>();
        try {
            TokenStream tokenStream = SMART_CHINESE_ANALYZER.tokenStream("text",keyValue);
            tokenStream.reset();
            while (tokenStream.incrementToken()){
                CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
                result.add(charTermAttribute.toString());
            }
            tokenStream.close();
        } catch (IOException e) {
            log.info(GetTrace(e));
        }
        return result;
    }

    //生成4位随机验证码
    public static String generateVerifyCode(){
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0;i<4;i++){
            String value = random.nextInt(2)%2==0 ? "char" : "num";
            //输出是字母还是数字
            if("char".equalsIgnoreCase(value)){
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2)%2==0 ? 65:97;
                result.append((char) (random.nextInt(26) + temp));
            }else {
                result.append(random.nextInt(10));
            }
        }
        return String.valueOf(result);
    }

    /**
     * 判断map中指定key的值
     * 如果为key不存在，返回false
     * 如果存在，但是value为null,返回false
     * 存在，且值不为null，则返回true
     */
    public static boolean containsKeyAndNull(Map<String,Object> map,String key){
        String result = null;
        if (map.containsKey(key)){
            result = map.getOrDefault(key,null).toString();
            return result != null;
        }else {
            return false;
        }
    }


}

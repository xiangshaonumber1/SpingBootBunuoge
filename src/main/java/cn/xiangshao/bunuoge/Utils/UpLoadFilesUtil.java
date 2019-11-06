package cn.xiangshao.bunuoge.Utils;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.*;

/**
 * @author xiangshao
 * @date 2018/12/14 13:57
 * @describe
 * 参考来源：https://www.cnblogs.com/smallSevens/p/8874213.html
 */
@Slf4j
public class UpLoadFilesUtil{

    /**
     * IO密集型任务  = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）
     * CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
     * 混合型任务  = 视机器配置和复杂度自测而定
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
            corePoolSize+1,
            101,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000));

    public JSONArray start_upload(HttpServletRequest request,String save_path){
        Future<JSONArray> future = executor.submit(new UpLoadFileHelper(request,save_path));
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(CommonUtils.GetTrace(e));
            return null;
        }finally {
            executor.shutdown();
        }
    }

    /**
     * UpLoadFilesUtil 的辅助类
     * 帮助
     */
    public class UpLoadFileHelper implements Callable<JSONArray>{

        private HttpServletRequest request;
        private String save_path;

        UpLoadFileHelper(HttpServletRequest request, String save_path){
            this.request = request;
            this.save_path = save_path;
        }

        @Override
        public JSONArray call() throws Exception {
            return CommonUtils.upload_files(request,save_path);
        }
    }

}

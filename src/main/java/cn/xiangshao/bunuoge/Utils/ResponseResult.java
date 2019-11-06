package cn.xiangshao.bunuoge.Utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {
    //http 状态码
    private int code;
    //返回到提示信息
    private String msg;
    //返回的数据
    private Object data;
    //返回的操作结果状态
    private boolean status;

    //处理成功信息
    public static ResponseResult Success() {
        return new ResponseResult()
                .setCode(Status.code_service_success)
                .setStatus(true);
    }

    //拒绝处理信息
    public static ResponseResult Refuse(){
        return new ResponseResult()
                .setCode(Status.code_service_refuse)
                .setStatus(false);
    }

    //服务器异常信息
    public static ResponseResult Exception(int StatusCode){
        return new ResponseResult()
                .setCode(StatusCode)
                .setStatus(false);
    }

}

package cn.xiangshao.bunuoge.Utils.Netty;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xiangshao
 * @date 2019/4/30 11:07
 * @describe
 */
public class NettySocketIoUtil {

    private static ConcurrentMap<String, SocketIOClient> webSocketMap = new ConcurrentHashMap<>();
    private static ConcurrentMap<String, SocketIOClient> temp = new ConcurrentHashMap<>();

    //存放平台客户端链接信息
    public static void put(String key, SocketIOClient SocketIOClient) {
        webSocketMap.put(key, SocketIOClient);
    }
    //存放临时客户端信息
    public static void putTemp(String key, SocketIOClient SocketIOClient){
        temp.put(key,SocketIOClient);
    }

    //根据key,获取平台客户端链接信息
    public static SocketIOClient get(String key) {
        return webSocketMap.get(key);
    }

    //删除保存的客户端链接信息
    public static void remove(String key) {
        webSocketMap.remove(key);
    }
    //删除保存的临时客户端链接信息
    public static void removeTemp(String key){
        temp.remove(key);
    }

    //直接获取平台客户端链接信息
    public static Collection<SocketIOClient> getValues() {
        return webSocketMap.values();
    }
    //直接获取临时客户端链接信息
    public static Collection<SocketIOClient> getTempValues(){
        return temp.values();
    }


    public static ConcurrentMap<String, SocketIOClient> getWebSocketMap() {
        return webSocketMap;
    }
}

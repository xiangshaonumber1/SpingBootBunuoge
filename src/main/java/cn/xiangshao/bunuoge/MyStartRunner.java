package cn.xiangshao.bunuoge;

import cn.xiangshao.bunuoge.service.RedisService;
import com.corundumstudio.socketio.SocketIOServer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xiangshao
 * @date 2018/12/10 17:32
 * @describe 系统启动初始化配置
 */
@Component
@Order(value = 1)
@Slf4j
public class MyStartRunner implements CommandLineRunner {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SocketIOServer socketIOServer;

    @Override
    public void run(String... args) {
        // 项目启动时，执行redis中的部分数据初始化...
        LoadDataToRedis();

        // 启动netty-socket.io 服务
        runSocketIOServer();
    }

    // 项目启动时，向Redis添加初始数据
    private void LoadDataToRedis(){
        log.info("部分数据正在执行初始化...");
        redisService.initInfos();
    }

    private void runSocketIOServer(){
        log.info("socket服务已启动...");
        socketIOServer.start();
    }



}

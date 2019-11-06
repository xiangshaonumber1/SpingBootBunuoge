package cn.xiangshao.bunuoge.config.NettySocket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiangshao
 * @date 2019/4/30 9:54
 * @describe
 */
@Configuration
@Slf4j
public class NettySocketConfig {

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        String os = System.getProperty("os.name");
        log.info("当前操作系统 ： "+os);

        config.setHostname("0.0.0.0");
        config.setPort(8100);

        //设置最大的Websocket帧内容长度限制
        config.setMaxFramePayloadLength(1024 * 1024);
        //设置最大Http内容长度限制
        config.setMaxHttpContentLength(1024 * 1024 );
        return new SocketIOServer(config);
    }

    //启动服务的关键，不过不知道有没有其他启动方式
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer){
        return new SpringAnnotationScanner(socketIOServer);
    }

}

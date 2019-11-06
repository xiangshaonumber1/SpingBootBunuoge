package cn.xiangshao.bunuoge;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
//@MapperScan 注解对应了项目中 dao 所在的路径，就可以不用再写@Mapper
@MapperScan("cn.xiangshao.bunuoge.mapper")
@SpringBootApplication
public class BunuogeApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BunuogeApplication.class, args);
    }

}

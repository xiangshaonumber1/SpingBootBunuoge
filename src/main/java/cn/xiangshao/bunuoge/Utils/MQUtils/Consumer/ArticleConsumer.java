package cn.xiangshao.bunuoge.Utils.MQUtils.Consumer;//package com.personal.myblog.Utils.MQUtils.Consumer;
//
//import com.personal.myblog.config.MQ.MQConfig;
//import com.personal.myblog.entity.MessageQueue;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author xiangshao
// * @date 2019/4/29 14:20
// * @describe
// */
//@Component
//@Slf4j
//@RabbitListener(queues = MQConfig.queue_article)
//public class ArticleConsumer {
//
//    @RabbitHandler
//    public void process(MessageQueue messageQueue){
//        System.out.println("-----------接收到消息--------");
//        System.out.println("获取到的 文章队列 信息 :"+ messageQueue);
//    }
//
//
//}

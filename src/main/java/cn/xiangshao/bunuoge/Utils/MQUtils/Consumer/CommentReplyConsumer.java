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
// * @date 2019/4/29 14:21
// * @describe
// */
//@Component
//@Slf4j
//@RabbitListener(queues = MQConfig.queue_comment_reply)
//public class CommentReplyConsumer {
//
//    @RabbitHandler
//    public void process(MessageQueue messageQueue){
//        System.out.println("获取到的 评论和回复 信息 :"+ messageQueue);
//    }
//
//}

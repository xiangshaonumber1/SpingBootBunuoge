package cn.xiangshao.bunuoge.Utils.MQUtils;//package com.personal.myblog.Utils.MQUtils;
//
//import com.personal.myblog.config.MQ.MQConfig;
//import com.personal.myblog.entity.MessageQueue;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
///**
// * @author xiangshao
// * @date 2019/4/26 15:08
// * @describe 消息队列生产者
// */
//@Slf4j
//@Component
//public class MQProducer implements RabbitTemplate.ConfirmCallback {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    /**
//     * 发送即时消息
//     * convertAndSend 参数
//     * exchange 分发消息的交换机名称
//     * routingKey 用来匹配消息队列的Key
//     * Object 消息体
//     * CorrelationData 消息ID
//     */
//    public void sendMessage(MessageQueue messageQueue,String type){
//        String exchange = null;
//        String routingKey = null;
//        switch (type){
//            case "article":
//                exchange = MQConfig.exchange_article;
//                routingKey = MQConfig.routing_key_article;
//                break;
//            case "answer":
//                exchange = MQConfig.exchange_comment_reply;
//                routingKey = MQConfig.routing_key_comment_reply;
//                break;
//            case "all":
//                // ............
//                break;
//            default:
//                log.error("无效的 type 信息,无法生产消息");
//                return;
//        }
//        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//        rabbitTemplate.setConfirmCallback(this);
//        //convertAndSend方法用于将object类型的消息转换后发送到RabbitMQ服务端,发送的消息类型要与消费者方法参数保持一致
//        rabbitTemplate.convertAndSend(exchange, routingKey, messageQueue, correlationId);
//    }
//
//    public void getMessage(String type){
//        switch (type){
//            case "article":
////                Object result1 = rabbitTemplate.receive();
////                Object result2 = rabbitTemplate.receiveAndConvert();
//                Object result3 = rabbitTemplate.receive(MQConfig.queue_article);
//                Object result4 = rabbitTemplate.receiveAndConvert(MQConfig.queue_article);
////                System.out.println("输出 rabbitTemplate result1 : " + result1);
////                System.out.println("输出 rabbitTemplate result2 : " + result2);
//                System.out.println("输出 rabbitTemplate result3 : " + result3);
//                System.out.println("输出 rabbitTemplate result4 : " + result4);
//                break;
//            case "answer":
//                //..........
//                break;
//            case "all":
//                //......
//                break;
//        }
//    }
//
//
//    /**
//     * 消息回调确认方法
//     * @param correlationData 请求数据对象
//     * @param ack 是否发送成功
//     * @param cause
//     */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        System.out.println(" 回调ID ："+correlationData);
//        if (ack){
//            System.out.println("消息消费成功");
//        }else {
//            System.out.println("消息消费失败："+cause);
//        }
//    }
//
//}

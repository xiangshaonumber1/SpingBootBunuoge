package cn.xiangshao.bunuoge.config.MQ;//package com.personal.myblog.config.MQ;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author xiangshao
// * @date 2019/4/26 15:15
// * @describe 消息队列配置
// * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
// * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
// * Queue:消息的载体,每个消息都会被投到一个或多个队列。
// * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
// * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
// * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
// * Producer:消息生产者,就是投递消息的程序.
// * Consumer:消息消费者,就是接受消息的程序.
// * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
// */
//@Configuration
//public class MQConfig {
//
//    //定义交换机名称
//    public static final String exchange_article = "exchange-article"; //文章发布，等待查阅
//    public static final String exchange_comment_reply = "exchange-comment_reply";    //有评论或回复，等待应答
//    //定义消息队列名称
//    public static final String queue_article = "queue-article";
//    public static final String queue_comment_reply = "queue-comment_reply";
//    //定义获取消息需要的key
//    public static final String routing_key_article = "key-article";
//    public static final String routing_key_comment_reply = "key-comment_reply";
//
//
//    /**
//     * 针对消费者配置
//     * 1. 设置交换机类型
//     * 2. 将队列绑定到交换机
//     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
//     HeadersExchange ：通过添加属性key-value匹配
//     DirectExchange:按照routingkey分发到指定队列
//     TopicExchange:多关键字匹配
//     */
//    @Bean
//    public DirectExchange ArticleExchange(){
//        //交换机持久化，第一个参数为交换机名，第二个参数为durable是否持久化，第三个参数为autoDelete,当交换机没有绑定队列时会自动删除交换机
//        return new DirectExchange(exchange_article,true,false);
//    }
//
//    @Bean
//    public DirectExchange CommentReplyExchange(){
//        //同上
//        return new DirectExchange(exchange_comment_reply,true,false);
//    }
//
//    @Bean
//    public Queue ArticleQueue(){
//        //队列持久化，第一个参数为队列名，第二个为durable 是否持久化
//        return new Queue(queue_article,true);
//    }
//
//    @Bean
//    public Queue CommentReply(){
//        //同上
//        return new Queue(queue_comment_reply,true);
//    }
//
//    /**
//     * 实现绑定
//     */
//    @Bean
//    public Binding ArticleBinding(){
//        return BindingBuilder.bind(ArticleQueue()).to(ArticleExchange()).with(routing_key_article);
//    }
//
//    @Bean
//    public Binding CommentReplyBinding(){
//        return BindingBuilder.bind(CommentReply()).to(CommentReplyExchange()).with(routing_key_comment_reply);
//    }
//
//}

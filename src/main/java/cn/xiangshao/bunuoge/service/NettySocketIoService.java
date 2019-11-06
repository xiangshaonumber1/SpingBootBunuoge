package cn.xiangshao.bunuoge.service;

/**
 * @author xiangshao
 * @date 2019/4/30 9:27
 * @describe 可参照设置
 * https://blog.csdn.net/caicai1171523597/article/details/88052120?tdsourcetag=s_pcqq_aiomsg
 */
public interface NettySocketIoService{

//    @Autowired
//    RedisTemplate<Object,Object> redisTemplate;
//    @Autowired
//    RedisService redisService;
//    @Autowired
//    AdminMapper adminMapper;
//    @Autowired
//    CommonService commonService;
//
//    @OnConnect
//    private void onConnect(SocketIOClient client){
//        log.info("建立连接:"+client);
//    }
//
//    @OnDisconnect
//    private void onDisConnect(SocketIOClient client){
//        log.info("断开连接:"+client);
//    }
//
//    // ※ 用户提交注销申请时，删除保存的用户客户端地址
//    @OnEvent("notification_logout")
//    public void notification_logout(String openID){
//        log.info("用户 【"+openID+"】 已注销下线上线-----------------------------------");
//        //移除链接成功的ip地址
//        NettySocketIoUtil.remove(openID);
//        log.info("剩余在线用户:"+NettySocketIoUtil.getWebSocketMap());
//    }
//
//    // ※ 用户上线，添加客户端
//    @OnEvent("notification_connect")//前端主动连接时的关键字
//    void notification_connect(SocketIOClient client, String openID){
//        log.info("用户openID ：【"+openID+"】 已连接");
//        //保存连接成功的ip的地址
//        NettySocketIoUtil.put(openID,client);
//        client.sendEvent("receive_connect","用户openID ：【"+openID+"】 已连接");
//    }
//
//    // ※ 文章发布，通知订阅者,前端提交notification_article
//    @OnEvent("notification_article")
//    public void notification_article(String openID){
//        Set fansInfo = (Set) redisService.getFansInfoAndMarkedInfo(openID).get("fansInfo");
//        for (Object aim_openID : fansInfo){
//            //首先循环获取粉丝的客户端链接信息
//            SocketIOClient aim_client = NettySocketIoUtil.get(String.valueOf(aim_openID));
//            if (aim_client==null){
//                log.info("粉丝用户未【"+aim_openID+"】上线，暂不推送！继续......");
//                continue;
//            }
//            aim_client.sendEvent("receive_article","通知粉丝已发布新文章");
//        }
//    }
//
//    // 执行评论或者回复的时候
//    public void notification_commentAndReply(String openID, String aim_openID, String content, String type){
//        // 第一步，保存消息发送日志到redis中，注，这里不是保存回复数据本体的意思，因为数据保存时保存在数据库
//        // 这里只是保存消息的发送，如 xxxx 评论(回复)了你的内容 xxxxxxxxxxxx
//        redisService.addUnreadMessage(aim_openID,openID,type);
//        //第二步，获取目标用户IP地址
//        SocketIOClient aim_client = NettySocketIoUtil.get(aim_openID);
//        //第三步，判断是回复还是评论类型
//        aim_client.sendEvent("notification_reply");
//    }
//
//    //管理员向全体在线用户发送即时站内消息,前端提交notification_system_message方法
//    void notification_system_message(String sendType,String title,String content,String acceptor){
//        //保存系统消息到MySQL中，以便没上线的用户依然能够在下次上线时获取到消息
////        adminMapper.insertSystemMessage(title,content);
//        //然后立即向全体在线用户发送消息通知 注：如果用户数量过多，这里应该实现异步发送
//        if ("all".equals(sendType)){
//            //发送全体
//            for (SocketIOClient client : NettySocketIoUtil.getValues()){
//                client.sendEvent("notification_system_message",content);
//            }
//        }else {
//            //发送给指定用户，需要redis等辅助
//            log.error("发送给指定用户，尚未实现");
//        }
//    }



}

package cn.xiangshao.bunuoge.service;

import cn.xiangshao.bunuoge.entity.response.ArticleInfoCountResult;

public interface RedisService {

    /**
     * 系统启动时，初始化部分信息
     */
    void initInfos();

    /**
     * 保存用户账号信息
     */
    void saveAccount(String username);

    /**
     * 判断指定账号是否已存在
     */
    Boolean checkUsername(String username);

    /**
     * 保存salt,与数据库保存一致
     * @param openId 用户openId
     * @param token_salt 生成token的盐值
     */
    void saveSalt(String openId, String token_salt);

    /**
     * 获取用户的salt,与数据库一致
     * @param openId 用户openId
     * @return 盐值
     */
    String getSalt(String openId);

    /**
     * 保存accessToken 和 refreshToken 的链信息
     */
    void saveTokens(String openId, String token);

    /**
     * 批量删除token,一般用于重置账号密码后，删除原来的token
     */
    void deleteTokenBatch(String[] openIds);

    /**
     * 检验当前过期token是否能进行刷新
     */
    Boolean verifyTokenRefresh(String openId, String token);

    /**
     * 保存系统验证码
     * @param userEmail 用户填写邮箱
     * @param code 系统生产的验证码
     */
    void saveVerifyCode(String userEmail, String code);

    /**
     * 获取系统验证码
     * @param username 用户账号(邮箱)
     * @return 返回验证码
     */
    String getVerifyCode(String username);

    /**
     * 保存文章的流量信息
     * @param countResult 文章流量统计信息
     */
    void saveArticleInfoCount(ArticleInfoCountResult countResult);

    /**
     * 获取文章的流量信息
     * @param articleId 文章id
     * @param type 文章类型
     * @return 文章的流量统计信息
     */
    ArticleInfoCountResult getArticleInfoCount(String articleId, String type);

    /**
     * 执行点赞或取消点赞
     * @param articleId 文章id
     * @param openID 用户id
     * @param executeType 操作类型
     */
    void clickLike(String articleId, String openID, boolean executeType);

    /**
     * 执行收藏或取消收藏
     * @param articleId 被收藏文章id
     * @param openId 收藏者id
     * @param executeType 执行动作类型
     */
    void clickCollection(String articleId, String openId, boolean executeType);

    /**
     * 执行新增关注或取消关注
     * @param openId 主动关注者id
     * @param markedId 被关注者id
     * @param executeType 执行动作类型
     */
    void clickMark(String openId, String markedId, boolean executeType);

    /**
     * 获取用户对指定文章的操作信息
     * @param articleId 目标文章id
     * @param openId 当前用户id
     * @return 返回操作信息结果
     */
    Object clickedLikeAndCollection(String articleId, String openId);

    /**
     * 记录当前系统信息已读用户(持续新增已读消息记录，不删除)
     * @param openId 读取系统消息用户id
     * @param messageId 系统消息id
     */
    void readSystemMessage(String openId, String messageId);

    /**
     * 检查指定用户（openId）,是否已读指定系统消息
     * @param openId 检查用户id
     * @param messageId 目标系统消息
     * @return 检查结果
     */
    Boolean checkSystemMessage(String openId, String messageId);

    /**
     * 为指定用户(openId)新增一条未读消息(messageId)（持续新增未读消息记录，读取后删除记录）
     * @param openId 目标用户id
     * @param messageId 新增未读消息id
     * @param type 消息类型
     */
    void addUnreadMessage(String openId, String messageId, String type);

    /**
     * 将 reply(消息类型) 消息设置为已读（删除未读消息记录）
     * @param openId 读取用户id
     * @param messageId 读取消息id
     * @param type 读取消息类型
     */
    void readReplyMessage(String openId, String messageId, String type);

    /**
     * 获取指定用户（openId）未读的指定消息类型（type）
     * @param openId 统计未读总量用户id
     * @param type 消息类型
     * @return 未读消息统计
     */
    Object getUnReadMessageCount(String openId, String type);

//
//    /*********************************************** 回复/评论 消息处理(存在即未读，不存在即为已读) **********************************************************/
//    //新增指定类型消息——保存未读消息到redis中，和系统消息相反，因为系统消息属于一对多，该消息属于一对一，无时间上问题
//    void addUnreadMessage(String aim_openID, String messageID, String type){
//        redisTemplate.opsForSet().add(Status.redis_message_Reply+":"+type+":"+aim_openID,messageID);
//    }
//
//    //删除——阅读 reply类型 消息时，把所有消息设置为已读(即删除)
//    void readReplyMessage(String aim_openID){
//        redisTemplate.delete(Status.redis_message_Reply+":"+"comment"+":"+aim_openID);
//        redisTemplate.delete(Status.redis_message_Reply+":"+"reply"+":"+aim_openID);
//    }
//
//    //查询——查询comment类型 和reply类型 未读消息总数
//    public int getUnReadReplyMessage(String openID){
//        Long comment = redisTemplate.opsForSet().size(Status.redis_message_Reply+":"+"comment"+":"+openID);
//        Long reply = redisTemplate.opsForSet().size(Status.redis_message_Reply+":"+"reply"+":"+openID);
//        return (comment.intValue()+reply.intValue());
//    }

//
//    /**
//     * 下面这一句，
//     * 想作 死死死死死死死死死死 就该改吧
//     */
//    @Autowired
//    private RedisTemplate<Object, Object> redisTemplate;
//
//    @Autowired
//    UserDao userDao;
//
//    /*
//     * 骚操作说明：
//     * 这里key-value都存放token的是因为登录的时候，是不需要token来进行的验证的，
//     * 而本程序的token刷新是基于 把 刚过期的token作为key，如果符合，则生成新的 token,作为键值对的新的key，value是刚过期的token
//     * 保证有效期token只有一个，允许刷新的过期token也只有一个，而且还在不断变化中
//     * 从而实现，只有刚过期的那一个token才是允许进行刷新token的合法依据，避免了拿到以前过期的合法token换取新token
//     */
//    void save_token(String new_token, String old_token) {
//        String openID_from_new_token = JwtUtil.getopenID(new_token);
//        String openID_from_old_token = JwtUtil.getopenID(old_token);
//        if (openID_from_new_token.equals(openID_from_old_token)){
//            //两个token解析出来的username必须一致
//            Map<Object,Object> map_token = new LinkedHashMap<>();
//            map_token.put(new_token,old_token);
//            Map<Object,Object> map_username = new LinkedHashMap<>();
//            map_username.put(openID_from_old_token, map_token);
//            //然后，保存新的token信息，因为key都是username，所以不用考虑删除原来的token的问题
//            redisTemplate.opsForHash().putAll(Status.redis_base_Token,map_username);
//        }else {
//            log.error("两个token解析出的用户名不一致，视为非法操作！！！ 已停止保存！");
//        }
//    }
//
//    /***************************************** 保存邮箱验证码,并设置只有5分钟的有效时间 **********************************/
//    public void save_MailVerificationCode(String userMail, String code){
//        redisTemplate.opsForValue().set(Status.redis_user_MailVerificationCode+":"+userMail,code);
//        redisTemplate.expire(Status.redis_user_MailVerificationCode+":"+userMail,5, TimeUnit.MINUTES);
//    }
//
//    /**************************** 获取邮箱验证码,如果存在，则获取并返回，如不存在或已过期，则返回null *******************/
//    public String get_MailVerificationCode(String userMail){
//        //这里取值有问题，明天要更改 WRONGTYPE Operation against a key holding the wrong kind of value
//        Object isExist = redisTemplate.opsForValue().get(Status.redis_user_MailVerificationCode+":"+userMail);
//        if (isExist!=null)
//            return String.valueOf(isExist);
//        else
//            return null;
//    }
//
//    /************** 邮箱验证码用完后，立即删除缓存在redis中的验证码,具体实现地方有 用户修改完密码，并成功保存到数据库后 **************/
//    public void delete_MailVerificationCode(String userMail){
//        redisTemplate.delete(Status.redis_user_MailVerificationCode+":"+userMail);
//    }
//
//    //保存文章的喜欢数，不喜欢数，和浏览量
//    public void saveArticleStatusCount(ArticleStatusCount count){
//        redisTemplate.opsForHash().put(Status.redis_user_ArticleStatusCount,count.getArticleID(),count);
//    }
//
//    //删除文章时，应把文章的状态信息也一起删除
//    public void deleteRedisArticleInfo(String deleteID){
//        redisTemplate.opsForHash().delete(Status.redis_user_ArticleStatusCount,deleteID);
//        redisTemplate.delete(Status.redis_user_LikeInfo+":"+deleteID);
//    }
//
//    //获取指定ID的文章的喜欢数量，不喜欢数量，和浏览量
//    public ArticleStatusCount getArticleStatusCount(String articleID){
//       return (ArticleStatusCount) redisTemplate.opsForHash().get(Status.redis_user_ArticleStatusCount,articleID);
//    }
//
//    //点击了点赞按钮，根据executeType的值，来判断是点赞还是取消赞
//    void clickLike(String articleID,String openID,boolean executeType){
//        if (executeType){
//            //删除点赞记录
//            redisTemplate.opsForSet().remove(Status.redis_user_LikeInfo+":"+articleID,openID);
//        }else {
//            //记录用户点赞信息，
//            redisTemplate.opsForSet().add(Status.redis_user_LikeInfo+":"+articleID,openID);
//        }
//    }
//
//    //点击了收藏按钮，根据executeType的值，来判断是收藏还是取消收藏
//    void clickCollect(String articleID,String openID,boolean executeType){
//        if (executeType){
//            //删除点赞记录
//            redisTemplate.opsForSet().remove(Status.redis_user_CollectInfo+":"+articleID,openID);
//        }else {
//            //收藏记录
//            redisTemplate.opsForSet().add(Status.redis_user_CollectInfo+":"+articleID,openID);
//        }
//    }
//
//    //点击关注，根据executeType的值，来判断是关注还是取消关注
//    void clickMark(String openID,String aim_openID,boolean executeType){
//        if (executeType){
//            //取消关注该用户
//            log.info("openID : "+openID+"---"+"aim_openID : "+aim_openID+"---"+"执行取消关注操作！！！");
//            //  1. 把aim_openID做为key, openID作为values,，生成关注aim_openID的有哪些人的统计信息， 可以作为之后的aim_openID用户的粉丝数量统计
//            redisTemplate.opsForSet().remove(Status.redis_user_FansInfo+":"+aim_openID,openID);
//            //  2. 然后把openID作为key, aim_openID作为values,生成openID关注了那些人的统计信息， 可作为之后openID用户的关注人数统计
//            redisTemplate.opsForSet().remove(Status.redis_user_MarkedInfo+":"+openID,aim_openID);
//        }else {
//            //关注该用户
//            log.info("openID : "+openID+"---"+"aim_openID : "+aim_openID+"---"+"执行新增关注操作！！！");
//            //  1. 同上,关注aim_openID的人数+1（即aim_openID粉丝量+1）
//            redisTemplate.opsForSet().add(Status.redis_user_FansInfo+":"+aim_openID,openID);
//            //  2. 同上，openID的关注人数+1
//            redisTemplate.opsForSet().add(Status.redis_user_MarkedInfo+":"+openID,aim_openID);
//        }
//    }
//
//    //判请求用户是否对指定文章有过点赞记录和收藏记录
//    Object getLikeAndCollectStatus(String articleID,String openID){
//        JSONObject result = new JSONObject();
//        result.put("likeStatus",this.isExistInSet(Status.redis_user_LikeInfo+":"+articleID,openID));
//        result.put("collectStatus",this.isExistInSet(Status.redis_user_CollectInfo+":"+articleID,openID));
//        return result;
//    }
//
//    //判断发起用户是否有关注该用户,即在 openID的关注表里，查看是否存aim_openID
//    boolean getMarkStatus(String openID,String aim_openID){
//        return this.isExistInSet(Status.redis_user_MarkedInfo+":"+openID,aim_openID);
//    }
//
//    //查询请求用户的关注的人的数量和自己的粉丝数量和详细信息
//    public JSONObject getFansInfoAndMarkedInfo(String openID){
//        Set markedInfo = this.get_set(Status.redis_user_MarkedInfo+":"+openID);
//        Set fansInfo = this.get_set(Status.redis_user_FansInfo+":"+openID);
//        int markedCount = this.get_set(Status.redis_user_MarkedInfo+":"+openID).size();
//        int fansCount = this.get_set(Status.redis_user_FansInfo+":"+openID).size();
//        JSONObject result = new JSONObject();
//        //我的粉丝
//        result.put("fansInfo",fansInfo);
//        //我关注的用户
//        result.put("markedInfo",markedInfo);
//        //我的粉丝数量
//        result.put("markedCount",markedCount);
//        //我关注的用户数量
//        result.put("fansCount",fansCount);
//        return result;
//    }
//
//    /********************************************* 系统消息处理 ***********************************************************/
//    //确认并保存用户ID到指定messageID下，和其他类型消息处理相反，因为这里是一对多，有用户注册时间控制
//    void readSystemMessage(String openID,String messageID){
//        redisTemplate.opsForSet().add(Status.redis_message_System+":"+messageID,openID);
//    }
//    //检查指定用户(openID) 是否已读取指定系统消息(messageID)
//    Boolean checkSystemMessage(String openID,String messageID){
//        return redisTemplate.opsForSet().isMember(Status.redis_message_System+":"+messageID,openID);
//    }
//
//    /*********************************************** 回复/评论 消息处理(存在即未读，不存在即为已读) **********************************************************/
//    //新增指定类型消息——保存未读消息到redis中，和系统消息相反，因为系统消息属于一对多，该消息属于一对一，无时间上问题
//    void addUnreadMessage(String aim_openID, String messageID, String type){
//        redisTemplate.opsForSet().add(Status.redis_message_Reply+":"+type+":"+aim_openID,messageID);
//    }
//
//    //删除——阅读 reply类型 消息时，把所有消息设置为已读(即删除)
//    void readReplyMessage(String aim_openID){
//        redisTemplate.delete(Status.redis_message_Reply+":"+"comment"+":"+aim_openID);
//        redisTemplate.delete(Status.redis_message_Reply+":"+"reply"+":"+aim_openID);
//    }
//
//    //查询——查询comment类型 和reply类型 未读消息总数
//    public int getUnReadReplyMessage(String openID){
//        Long comment = redisTemplate.opsForSet().size(Status.redis_message_Reply+":"+"comment"+":"+openID);
//        Long reply = redisTemplate.opsForSet().size(Status.redis_message_Reply+":"+"reply"+":"+openID);
//        return (comment.intValue()+reply.intValue());
//    }




}

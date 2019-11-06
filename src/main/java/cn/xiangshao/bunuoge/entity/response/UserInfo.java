package cn.xiangshao.bunuoge.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xiangshao
 * @date 2019/2/27 16:18
 * @describe
 */
@Data
@Accessors(chain = true)
public class UserInfo implements Serializable {
    private String openID; //主键ID,作为用户的openID
    private String nickname;    //用户昵称
    private String gender;  //性别
    private String userIcon; //这是默认用户头像
    private String email; //绑定邮箱
    private String phone; //绑定手机号
    private String label; //个人标签
    private String wishCard; //心愿墙内容
    private String myDescribe; //个人描述
    private int fansCount = 0;  //粉丝总量
    private int markedCount = 0; //关注量
    private int originArticleCount = 0;     //原创文章数
    private int reprintArticleCount = 0;    //转载文章数
    private int translateArticleCount = 0;  //翻译文章数
    private int publicDiaryCount = 0;   //公开日记数
    private int privateDiaryCount = 0;  //私密日记数
    private int collectArticleCount = 0; //收藏文章数
}

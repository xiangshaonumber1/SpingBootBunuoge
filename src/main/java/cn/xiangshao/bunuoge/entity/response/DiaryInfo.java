package cn.xiangshao.bunuoge.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class DiaryInfo implements Serializable {
    String openID;      //作者的openID
    String diaryID;     //日记自增长主键
    String nickname;    //用户昵称
    String userIcon;    //这是默认用户头像
    String title;       //日记标题
    String content;     //日记具体内容
    String type;        //日记type类型
    String label;       //日记附加标签
    String time;        //日记发布时间
}

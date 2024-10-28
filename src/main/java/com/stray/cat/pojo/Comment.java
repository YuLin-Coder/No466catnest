package com.stray.cat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {//评论表

    private int commentId;
    private String commentPhone;
    private String commentContent;
    private Date commentCreatetime;
    private int commentPsId;  //来源表对应id
    private int commentSource; //评论来源

}

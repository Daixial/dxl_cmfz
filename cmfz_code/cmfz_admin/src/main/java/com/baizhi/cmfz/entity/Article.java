package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private String id;
    private String title; // 标题
    private Date   createDate; //创建时间
    private String pictureUrl;// 图片路径
    private String author;    // 作者
    private String content; // 内容
    private String category;// 文章类型
    private String status;  // 文章状态
}

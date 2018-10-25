package com.baizhi.cmfz.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Managers implements Serializable {
    private  String id;// 管理员id
    private  String name;// 管理员 用户名
    private  String password ; // 密码
    private  String phone; // 手机号
    private  Date   createDate;// 创建时间
    private  String solt; // 加密
}

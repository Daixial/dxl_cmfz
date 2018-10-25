package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String id; // 主键
    private String phone;// 手机号
    private String name;//姓名
    private String nickName; // 法名
    private String sex;// 性别
    private Date createDate;// 创建时间
    private String img; // 头像
    private String signature;// 签名
    private String password;// 密码
    private String salt;// 私盐
    private String status="1";// 状态
    private String location;// 北京
}

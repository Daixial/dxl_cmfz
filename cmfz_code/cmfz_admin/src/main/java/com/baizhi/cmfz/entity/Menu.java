package com.baizhi.cmfz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
   private Integer id;// 主键
   private  String title;// 标签
   private String iconCls;// 头像
   private String href;// 超链接
   private Integer lev;// 等级
   private List<Menu> list;// 表关系
}

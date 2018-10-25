package com.baizhi.cmfz.dao;

import java.util.List;

public interface BaseDao<T> {
    void  insert(T t);// 添加
    T findOne(String t);// 查询一个
    void delete(String id);// 删除
    void update(T t);// 更新
    List<T> fingAll();// 查所有
}

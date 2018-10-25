package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Statistics;
import com.baizhi.cmfz.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();
    void  addUser(User user);
    User  queryOne(String id);
    void  renewalUser(User user);
    List<User>  queryColums(String column);
    List<Statistics> findactive(String sex);//中国地图
    List<Integer> findActive();//查看用户活跃度
}

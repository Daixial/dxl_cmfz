package com.baizhi.cmfz.dao;


import com.baizhi.cmfz.entity.Managers;

public interface ManagerDao extends  BaseDao<Managers>{
    Managers  select1(String username);
    void  update1(Managers managers);
}

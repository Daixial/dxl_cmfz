package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Managers;

public interface ManagersService {
    Managers queryManager(String name,String password);
    void modyfiManager(Managers managers);
}

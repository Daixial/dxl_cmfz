package com.baizhi.cmfz.serviceImpl;

import com.baizhi.cmfz.dao.ManagerDao;
import com.baizhi.cmfz.entity.Managers;
import com.baizhi.cmfz.service.ManagersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ManagersServiceImpl implements ManagersService {
    @Resource
    private ManagerDao managerDao;

    @Override
    public void modyfiManager(Managers managers) {
        try {
            managerDao.update1(managers);
        } catch (Exception e) {
            throw  new  RuntimeException("修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Managers queryManager(String name, String password) {
        Managers select = managerDao.select1(name);
        if (select!=null){
            String pwd = select.getPassword();
            if(password.equals(pwd)){
                return select;
            }
            throw new RuntimeException("用户名或密码错误");
        }
        throw new RuntimeException("用户名或密码错误");
    }
}

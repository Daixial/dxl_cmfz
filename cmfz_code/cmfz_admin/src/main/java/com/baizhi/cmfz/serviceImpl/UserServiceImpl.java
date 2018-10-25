package com.baizhi.cmfz.serviceImpl;

import com.baizhi.cmfz.dao.UserDao;
import com.baizhi.cmfz.entity.Statistics;
import com.baizhi.cmfz.entity.User;
import com.baizhi.cmfz.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource //注入
    private UserDao userDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> queryAll() {
        return userDao.fingAll();
    }

    @Override
    public void addUser(User user) {
        userDao.insert(user);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryOne(String id) {
        return userDao.findOne(id);
    }

    @Override
    public void renewalUser(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> queryColums(String column) {
        return userDao.queryCustomeAll(column);
    }

    @Override
    public List<Statistics> findactive(String sex) {
        return userDao.findactive(sex);
    }

    @Override
    public List<Integer> findActive() {
        List<Integer> count = new ArrayList<Integer>();
        List<Integer> day = new ArrayList<Integer>();
        day.add(7);
        day.add(15);
        day.add(30);
        day.add(90);
        day.add(180);
        day.add(365);
        for (Integer integer : day) {
            int active = userDao.findActive(integer);
            count.add(active);
        }
        return count;
    }
}

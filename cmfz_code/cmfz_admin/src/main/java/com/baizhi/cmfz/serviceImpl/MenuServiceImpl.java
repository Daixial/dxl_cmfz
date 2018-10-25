package com.baizhi.cmfz.serviceImpl;

import com.baizhi.cmfz.dao.MenuDao;
import com.baizhi.cmfz.entity.Menu;
import com.baizhi.cmfz.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("menuService")
@Transactional
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Menu> queryAll() {
        List<Menu> list = menuDao.fingAll();
        return list;
    }
}

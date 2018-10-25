package com.baizhi.cmfz.serviceImpl;

import com.baizhi.cmfz.dao.PersonDao;
import com.baizhi.cmfz.entity.Person;
import com.baizhi.cmfz.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Person> queryAll() {
        return personDao.queryAll();
    }
}

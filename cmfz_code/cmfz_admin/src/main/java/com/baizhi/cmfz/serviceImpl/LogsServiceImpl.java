package com.baizhi.cmfz.serviceImpl;

import com.baizhi.cmfz.dao.LogsDao;
import com.baizhi.cmfz.entity.Logs;
import com.baizhi.cmfz.service.LogsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class LogsServiceImpl implements LogsService {

    @Resource
    private LogsDao logsDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Logs> queryPage(Integer page, Integer rows) {
        Integer start=(page-1)*page;
        return logsDao.fingPageAndView(start,rows);
    }

    @Override
    public Long queryCount() {
        return logsDao.findPage();
    }

    @Override
    public void insertLogs(Logs logs) {
        logsDao.insert(logs);
    }
}

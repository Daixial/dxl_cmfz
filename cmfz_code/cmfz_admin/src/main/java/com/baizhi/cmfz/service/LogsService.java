package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Logs;
import java.util.List;

public interface LogsService {
    List<Logs> queryPage(Integer page, Integer rows);
    Long queryCount();
    void  insertLogs(Logs logs);
}

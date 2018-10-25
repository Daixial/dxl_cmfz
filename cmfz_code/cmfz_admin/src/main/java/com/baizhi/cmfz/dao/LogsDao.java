package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Logs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogsDao extends BaseDao<Logs> {
    List<Logs> fingPageAndView(@Param("page")Integer page, @Param("rows")Integer rows);
    Long findPage();
}

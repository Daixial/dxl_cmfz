package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Person;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PersonDao {

    @Select("select id,name from t_person")
     List<Person> queryAll();
}

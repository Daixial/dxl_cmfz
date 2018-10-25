package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Statistics;
import com.baizhi.cmfz.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao extends BaseDao<User> {

    @Select("select ${column} from t_user")
    List<User> queryCustomeAll(@Param("column") String column);

    /*中国地图显示用户活跃度*/
    List<Statistics> findactive(String sex);
    /*统计用户的活跃度*/
    Integer findActive(@Param("day") Integer day);
}

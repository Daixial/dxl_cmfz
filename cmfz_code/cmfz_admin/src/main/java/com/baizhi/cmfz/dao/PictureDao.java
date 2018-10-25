package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureDao extends BaseDao<Picture> {

    List<Picture> fingPageAndView(@Param("page")Integer page,@Param("rows")Integer rows);
    Long findPage();
}

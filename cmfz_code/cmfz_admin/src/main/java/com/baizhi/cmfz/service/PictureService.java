package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Picture;

import java.util.List;

public interface PictureService {
    List<Picture> queryAll();
    void modyfiPicture(Picture picture);
    void addPicture(Picture picture);
    Picture queryOne(String id);
    List<Picture> queryPage(Integer page,Integer rows);
    Long queryPage();
}

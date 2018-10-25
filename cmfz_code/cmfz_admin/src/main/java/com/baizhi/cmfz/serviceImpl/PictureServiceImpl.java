package com.baizhi.cmfz.serviceImpl;

import com.baizhi.cmfz.dao.PictureDao;
import com.baizhi.cmfz.entity.Picture;
import com.baizhi.cmfz.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureDao pictureDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Picture> queryAll() {
        return pictureDao.fingAll();
    }

    @Override
    public void modyfiPicture(Picture picture) {
        pictureDao.update(picture);
    }

    @Override
    public void addPicture(Picture picture) {
        try {
            picture.setId(UUID.randomUUID().toString().replaceAll("-",""));
            picture.setCreateDate(new Date());
            pictureDao.insert(picture);
        } catch (Exception e) {
            throw new RuntimeException("添加失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Picture queryOne(String id) {
        return pictureDao.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Picture> queryPage(Integer page, Integer rows) {
        int start=(page-1)*page;
        return pictureDao.fingPageAndView(start,rows);
    }

    @Override
    public Long queryPage() {
        return pictureDao.findPage();
    }
}

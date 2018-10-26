package com.baizhi.cmfz.serviceImpl;

import com.baizhi.cmfz.dao.ArticleDao;
import com.baizhi.cmfz.entity.Article;
import com.baizhi.cmfz.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {  //  这是  ！！！！！
    @Resource
    private ArticleDao articleDao;
    @Override
    public void insertArticle(Article article) {
        articleDao.insert(article);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Article> findAll() {

        return articleDao.fingAll();
    }

    @Override
    public Article findOneArticl(String id) {
        return articleDao.findOne(id);
    }

    @Override
    public Long Selectcount() {
        return null;
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.update(article);
    }
}

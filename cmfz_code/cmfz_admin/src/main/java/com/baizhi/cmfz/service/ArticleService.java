package com.baizhi.cmfz.service;

import com.baizhi.cmfz.entity.Article;

import java.util.List;

public interface ArticleService {
    void insertArticle(Article article); // 添加文章
    List<Article> findAll();
    Article findOneArticl(String id);
    Long Selectcount();
    void  updateArticle(Article article);// 修改
}

package com.baizhi.cmfz.dao;

import com.baizhi.cmfz.entity.Article;

public interface ArticleDao extends BaseDao<Article> {
    Long findPage();

}

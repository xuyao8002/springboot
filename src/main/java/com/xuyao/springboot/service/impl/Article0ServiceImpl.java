package com.xuyao.springboot.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuyao.springboot.bean.po.Article;
import com.xuyao.springboot.dao.ArticleMapper;
import com.xuyao.springboot.service.IArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuyao
 * @since 2022-07-17
 */
@Service("Article0ServiceImpl")
@DS("dynamic0")
public class Article0ServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


    @Override
    public List<Article> getList() {
        return getArticles();
    }

    private List<Article> getArticles() {
        return this.baseMapper.selectList(Wrappers.lambdaQuery(Article.class));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Article> getListNew() {
        return getArticles();
    }
}

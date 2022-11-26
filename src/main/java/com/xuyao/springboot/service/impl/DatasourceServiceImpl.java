package com.xuyao.springboot.service.impl;

import com.xuyao.springboot.bean.po.Article;
import com.xuyao.springboot.service.IArticleService;
import com.xuyao.springboot.service.IDatasourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DatasourceServiceImpl implements IDatasourceService {

    @Resource(name = "articleServiceImpl")
    private IArticleService articleService;

    @Resource(name = "Article0ServiceImpl")
    private IArticleService articleService0;

    @Resource(name = "Article1ServiceImpl")
    private IArticleService articleService1;

    @Override
    public void normal() {
        List<Article> list = articleService.getList();
        log.info(list.toString());
        List<Article> list1 = articleService0.getList();
        log.info(list1.toString());
        List<Article> list2 = articleService1.getList();
        log.info(list2.toString());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void requiresNew() {
        List<Article> list = articleService.getListNew();
        log.info(list.toString());
        List<Article> list1 = articleService0.getListNew();
        log.info(list1.toString());
        List<Article> list2 = articleService1.getListNew();
        log.info(list2.toString());
    }
}

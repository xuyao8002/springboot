package com.xuyao.springboot.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuyao.springboot.bean.po.Article;
import com.xuyao.springboot.dao.ArticleMapper;
import com.xuyao.springboot.service.IArticleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuyao
 * @since 2022-07-17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}

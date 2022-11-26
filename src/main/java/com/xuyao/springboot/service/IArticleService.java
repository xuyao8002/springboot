package com.xuyao.springboot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xuyao.springboot.bean.po.Article;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuyao
 * @since 2022-07-17
 */
public interface IArticleService extends IService<Article> {

    List<Article> getList();

    List<Article> getListNew();

}

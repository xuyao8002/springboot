package com.xuyao.springboot;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xuyao.springboot.bean.po.Article;
import com.xuyao.springboot.service.IArticleService;
import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@TestPropertySource("classpath:other.properties")
public class MybatisPlusTests {

	@Autowired
	private IArticleService articleService;

	@Test
	public void save() {
		Article article = new Article();
		article.setTitle("第一篇文章");
		article.setContent("这是我的第一篇文章啊");
		article.setAuthor("xuyao");
		article.setAvatar("http://www.xuyao.com/img");
		article.setArticleId(2L);
		article.setOriginUrl("http://www.xuyao.com/article/1");
		boolean save = articleService.save(article);
		System.out.println("save: " + save);
	}

	@Test
	public void list() {
		List<Article> list = articleService.list(Wrappers.lambdaQuery(Article.class).in(Article::getArticleId, 1L, 2L, 3L));
		System.out.println(list);
	}

	@Test
	public void update() {
		boolean update = articleService.update(Wrappers.lambdaUpdate(Article.class).in(Article::getArticleId, 1L, 2L, 3L)
				.eq(Article::getAuthor, "xuyao").set(Article::getAuthor, "xuyao1"));
		System.out.println(update);
	}

	@Test
	public void remove() {
		boolean delete = articleService.remove(Wrappers.lambdaUpdate(Article.class).in(Article::getArticleId, 1L, 2L, 3L)
				.eq(Article::getAuthor, "xuyao1"));
		System.out.println(delete);
	}

}

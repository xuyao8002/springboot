package com.xuyao.springboot;

import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.dao.UserMapper;
import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MybatisTests {

	private static String username = "guest";

	@Autowired
	private UserMapper userMapper;

	@Test
	public void selectByPrimaryKey() {
		User user = userMapper.selectByPrimaryKey(5L);
		System.out.println(user);
	}

	@Test
	public void insertSelective() {
		User insert = new User();
		insert.setName("xy");
		insert.setGender(0);
		insert.setUsername("xuyao");
		insert.setPassword("hahahhaha");
		insert.setPhone("18321703333");
        int i = userMapper.insertSelective(insert);

        System.out.println(insert);
	}

}

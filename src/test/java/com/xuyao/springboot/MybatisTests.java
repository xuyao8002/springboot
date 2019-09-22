package com.xuyao.springboot;

import com.xuyao.springboot.bean.po.User;
import com.xuyao.springboot.dao.IUserDao;
import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MybatisTests {

	private static String username = "guest";

	@Autowired
	private IUserDao userDao;

	@Test
	public void selectByPrimaryKey() {
		User user = userDao.selectByPrimaryKey(5L);
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
		insert.setEmail("125@123.com");
		insert.setAddress("i'am hear");
        int i = userDao.insertSelective(insert);
        System.out.println(insert);
	}

	@Test
	public void batchInsert(){
		List<User> batchList = new ArrayList<>();
		User insert = new User();
		insert.setName("xy");
		insert.setGender(0);
		insert.setUsername("xuyao");
		insert.setPassword("hahahhaha");
		insert.setPhone("18321703333");
		insert.setEmail("125@123.com");
		insert.setAddress("i'am hear");
		batchList.add(insert);

		insert = new User();
		insert.setName("xy");
		insert.setGender(0);
		insert.setUsername("xuyao");
		insert.setPassword("hahahhaha");
		insert.setPhone("18321703333");
		insert.setEmail("125@123.com");
		insert.setAddress("i'am hear");
		batchList.add(insert);

        userDao.batchInsert(batchList);
        System.out.println(batchList);
    }

    @Test
    public void updateByPrimaryKeySelective(){
	    User user = new User();
        user.setId(1L);
        user.setCreateDate(new Date());
        int i = userDao.updateByPrimaryKeySelective(user);
        System.out.println(i + ", " + user);
    }

    @Test
    public void batchUpdate(){
        List<User> update = new ArrayList<>();
        User user = new User();
        user.setId(2L);
        user.setCreateDate(new Date());
        update.add(user);
        user = new User();
        user.setId(3L);
        user.setCreateDate(new Date());
        update.add(user);
        user = new User();
        user.setId(4L);
        user.setCreateDate(new Date());
        update.add(user);
        int i = userDao.batchUpdate(update);
        System.out.println(i);
    }

}

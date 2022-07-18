package com.xuyao.springboot;

import com.mongodb.client.result.UpdateResult;
import com.xuyao.springboot.bean.po.UserXy;
import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@TestPropertySource("classpath:other.properties")
public class MongoTests {


	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void contextLoads() {
	    int count = 1000;
        List<UserXy> xys = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            UserXy userXy = new UserXy();
            userXy.setAge(30);
            userXy.setName("xuyao-"+i);
            userXy.setDescription("coding");
            Date date = new Date();
            userXy.setCreateDate(date);
            userXy.setUpdateDate(date);
            xys.add(userXy);
        }

        Collection<UserXy> userXIES = mongoTemplate.insertAll(xys);
        System.out.println(userXIES);
    }

    @Test
    public void update() {


        Query query = new Query(Criteria.where("description").is("coding"));
        Update set = new Update().set("description", "code more");
        long start = System.currentTimeMillis();
        UpdateResult updateResult = mongoTemplate.updateMulti(query,
                set, UserXy.class);
        long end = System.currentTimeMillis();
        System.out.println("cost: " + (end - start));
        System.out.println(updateResult);
    }


}

package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedisTests {


	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void atomicSetAndExpire() {
	    int count = 10;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            executorService.execute(() -> {
                Boolean aBoolean = valueOperations.setIfAbsent("name", "xuyao", 10, TimeUnit.SECONDS);
                System.out.println(aBoolean);
            });
        }


    }


}

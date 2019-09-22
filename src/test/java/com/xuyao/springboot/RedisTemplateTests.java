package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedisTemplateTests {


	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void contextLoads() throws InterruptedException {
	    int c = 10;
        CountDownLatch latch = new CountDownLatch(c);
        ExecutorService service = Executors.newFixedThreadPool(c);
        for (int i = 0; i < c; i++) {
            service.execute(() -> {
                Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent("name", "xuyao", 10, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + ", result: " + aBoolean);
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("end");

	}

}

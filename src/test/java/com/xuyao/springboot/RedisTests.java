package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@TestPropertySource("classpath:other.properties")
public class RedisTests {


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

	@Test
    public void hyperLogLog(){
	    int i = 1000_0000;
        String hello = "hello";
        int x = 0;
        for (int i1 = 0; i1 < 10000; i1++) {
            String[] vals = new String[1000];
            for(int j = 0; j < 1000; j++){
                vals[j] = String.valueOf(x++);
            }
            redisTemplate.opsForHyperLogLog().add(hello, vals);
        }
        System.out.println("add " + i);
        System.out.println(redisTemplate.opsForHyperLogLog().size(hello));
    }


}

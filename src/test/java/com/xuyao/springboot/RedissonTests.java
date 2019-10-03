package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedissonTests {


	@Autowired
	private RedissonClient redissonClient;

	private String name = "redisson";

	private int nums = 10;

	@Test
	public void mutextLock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(nums);
        CountDownLatch latch = new CountDownLatch(nums);
        for (int i = 0; i < nums; i++) {
            executorService.execute(() ->{
                RLock lock = redissonClient.getLock(name);
                try {
                    //waitTime：最长等待时间，leaseTime：最长持有时间
                    boolean b = lock.tryLock(1, 2, TimeUnit.SECONDS);
                    if(b){
                        try{
                            System.out.println(getName() + ": get lock");
                        }finally {
                            lock.unlock();
                        }

                    }else{
                        System.out.println(getName() + ": lost lock");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("-----the end");
    }

    private String getName() {
        return Thread.currentThread().getName();
    }


}

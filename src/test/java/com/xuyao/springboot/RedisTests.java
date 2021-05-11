package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
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

	//基数统计，如访问量
    //元素只能添加，不能删除
	@Test
    public void hyperLogLog(){
        HyperLogLogOperations<String, String> ops = redisTemplate.opsForHyperLogLog();
        String key1 = "log::1";
        ops.add(key1, "1", "2", "3", "4");
        System.out.println(key1 + ": " + ops.size(key1));
        String key2 = "log::2";
        ops.add(key2, "4", "5", "6", "7");
        System.out.println(key2 + ": " + ops.size(key2));
        String key3 = "log::3";
        ops.union(key3, key1, key2);
        System.out.println(key3 + ": " + ops.size(key3));
    }

    //位图操作，统计数
    @Test
    public void bitmap(){
	    String key = "bit::xy";
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.setBit(key, 0, true);
        ops.setBit(key, 2, true);
        ops.setBit(key, 4, true);
        ops.setBit(key, 8, true);
        ops.setBit(key, 16, true);
        ops.setBit(key, 32, true);
        ops.setBit(key, 64, true);
        ops.setBit(key, 128, true);

        System.out.println("bit 50: " + ops.getBit(key, 50));
        System.out.println("bit 64: " + ops.getBit(key, 64));

        System.out.println("bit count: " + redisTemplate.execute(new RedisCallback<Long>(){
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.bitCount(key.getBytes());
            }
        }));

    }

    //set，交集、差集、并集
    @Test
    public void set(){
        String key1 = "set::xy1";
        String key2 = "set::xy2";
        SetOperations<String, String> set = redisTemplate.opsForSet();
        set.add(key1, "100001", "100002", "100003", "100004", "100005", "100006");
        set.add(key2, "100001", "100003", "100005", "100007", "100009", "100011");
        Set<String> intersect = set.intersect(key1, key2);
//        Set<String> intersect = set.intersectAndStore(key1, key2, "intersectKey");
        System.out.println(intersect);

        redisTemplate.delete(key1);
        redisTemplate.delete(key2);

        set.add(key1, "100001", "100002", "100003", "100004", "100005", "100006");
        set.add(key2, "100001", "100002", "100003", "100004", "100006", "100007");

        Set<String> difference = set.difference(key2, key1);
        System.out.println(difference);

        redisTemplate.delete(key1);
        redisTemplate.delete(key2);

        set.add(key1, "100001", "100002", "100003", "100004", "100005", "100006");
        set.add(key2, "100007", "100008");

        Set<String> union = set.union(key2, key1);
        System.out.println(union);

        redisTemplate.delete(key1);
        redisTemplate.delete(key2);

    }


}

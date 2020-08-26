package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@TestPropertySource("classpath:other.properties")
public class DelayTask {


	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void redis() throws InterruptedException {
	    String key = "delayTask";
        long l = System.currentTimeMillis();
        redisTemplate.opsForZSet().add(key, "d", l + 4000L);
        redisTemplate.opsForZSet().add(key, "b", l + 2000L);
        redisTemplate.opsForZSet().add(key, "c", l + 3000L);
        redisTemplate.opsForZSet().add(key, "a", l + 1000L);
        while(true){
            Set<ZSetOperations.TypedTuple<String>> typedTuples = redisTemplate.opsForZSet().rangeByScoreWithScores(key, 0, System.currentTimeMillis(), 0, 1);
            if(typedTuples != null && !typedTuples.isEmpty()){
                for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
                    String value = typedTuple.getValue();
                    Double score = typedTuple.getScore();
                    if (System.currentTimeMillis() >= score) {
                        System.out.println(value + ", " + System.currentTimeMillis());
                        redisTemplate.opsForZSet().remove(key, value);
                        break;
                    }
                }
            }else{
                Thread.sleep(100L);
            }
        }


    }

}

package com.xuyao.springboot.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Task{
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private int i = 0;

    private String key = "taskLock";

//    @Scheduled(cron = "${schedule.cron.task}")
    public void cron() {
        class Lock implements LockCallback{
            @Override
            public void process() {
                log.info("" + i++);
            }
        }
        lock(new Lock());
    }

    private void lock(LockCallback callback){
        Boolean success = redisTemplate.opsForValue().setIfAbsent(key, "1", 5, TimeUnit.SECONDS);
        if(success){
            try{
                callback.process();
            }finally {
                redisTemplate.delete(key);
            }
        }
    }

    interface LockCallback{
        void process();
    }

}


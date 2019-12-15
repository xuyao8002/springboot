package com.xuyao.springboot;


import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class ScheduleTest {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    @Test
    public void schedule() throws InterruptedException {
//        cronTrigger();
//        info();
        periodicTrigger();
        info();
    }

    private void periodicTrigger() throws InterruptedException {
        //每五秒执行一次
        PeriodicTrigger trigger = new PeriodicTrigger(5, TimeUnit.SECONDS);
        trigger.setFixedRate(true);
        //启动后一秒后开始执行首次
        trigger.setInitialDelay(1);
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), trigger);
    }

    private void cronTrigger() throws InterruptedException {
        //每五秒执行一次
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/5 * * * * *"));

    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("当前时间：" + LocalDateTime.now());
        }
    }

    private void info() throws InterruptedException {
        System.out.println("开始执行");
        int activeCount = threadPoolTaskScheduler.getActiveCount();
        System.out.println("任务数量: " + activeCount);
        Thread.sleep(10000L);
        System.out.println("10秒");
        future.cancel(true);
        Thread.sleep(10000L);
        System.out.println("20秒");
    }

}

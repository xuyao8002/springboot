package com.xuyao.springboot.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);
    private int i = 0;
//    @Scheduled(cron = "${schedule.cron.task}")
    public void sendSignInMessage() {
        log.info("" + i++);
    }

}

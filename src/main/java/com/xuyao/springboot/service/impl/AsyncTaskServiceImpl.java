package com.xuyao.springboot.service.impl;

import com.xuyao.springboot.service.IAsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncTaskServiceImpl implements IAsyncTaskService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskServiceImpl.class);

    @Override
    @Async
    public void sendSms() throws InterruptedException {
        logger.info("sendSms start");
        TimeUnit.SECONDS.sleep(3);
        logger.info("sendSms end");
    }

    @Override
    @Async
    public void sendEmail() throws InterruptedException {
        logger.info("sendEmail start");
        TimeUnit.SECONDS.sleep(2);
        logger.info("sendEmail end");
    }
}

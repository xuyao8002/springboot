package com.xuyao.springboot.service.impl;

import com.xuyao.springboot.exception.CustomException;
import com.xuyao.springboot.service.ICustomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

@Service
public class CustomServiceImpl implements ICustomService {
    @Override
    public String hello(String name) throws CustomException {
        if (StringUtils.isEmpty(name)) {
            throw new CustomException("name can't be null");
        }
        return "hello, " + name;
    }

    @Override
    public void stopWatch() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("统计耗时");

        stopWatch.start("100ms");
        Thread.sleep(100L);
        stopWatch.stop();

        stopWatch.start("200ms");
        Thread.sleep(200L);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }
}

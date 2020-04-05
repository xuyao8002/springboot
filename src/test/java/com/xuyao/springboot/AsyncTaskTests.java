package com.xuyao.springboot;

import com.xuyao.springboot.service.IAsyncTaskService;
import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@TestPropertySource("classpath:other.properties")
public class AsyncTaskTests {

    @Autowired
    private IAsyncTaskService asyncTaskService;

	@Test
	public void asyncTask() throws InterruptedException {
        asyncTaskService.sendSms();
        asyncTaskService.sendEmail();
        TimeUnit.SECONDS.sleep(10);
    }


}

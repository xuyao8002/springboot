package com.xuyao.springboot;

import com.xuyao.springboot.service.ITransactionService;
import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@TestPropertySource(locations = {"classpath:application.properties","classpath:other.properties"})
public class TransactionTests {

    @Autowired
    private ITransactionService transactionService;

    @Test
    public void transactionAnnotation() {
        transactionService.transactionAnnotation();
    }

    @Test
    public void transactionTemplate() {
        transactionService.transactionTemplate();
    }

    @Test
    public void transactionManager() {
        transactionService.transactionManager();
    }

}

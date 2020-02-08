package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class OtherTest {

    @Autowired
    private MessageSource messageSource;

    @Test
	public void messageSource() {
        String zh = messageSource.getMessage("greeting", null, Locale.SIMPLIFIED_CHINESE);
        String en = messageSource.getMessage("greeting", null, Locale.US);
        System.out.println(zh);
        System.out.println(en);
    }


}

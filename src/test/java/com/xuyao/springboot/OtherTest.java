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

    private Locale locale = Locale.SIMPLIFIED_CHINESE;

    @Test
	public void messageSource() {
        String code = "greeting";
        System.out.println(getMessage(code));
        setLocale(Locale.US);
        System.out.println(getMessage(code));
    }

    private String getMessage(String code){
        return messageSource.getMessage(code, null, locale);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}

package com.xuyao.springboot;//package com.xuyao.springboot;

import com.xuyao.springboot.startup.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class MailTests {

    @Autowired
    private JavaMailSender mailSender;

	@Test
	public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xxx@163.com");
        message.setTo("xxx@qq.com");
        message.setSubject("注册成功提醒");
        String content="你好,你已注册成功,请登录系统!";
        message.setText(content);

        mailSender.send(message);
    }


}

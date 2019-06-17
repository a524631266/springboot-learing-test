package com.springboot.learing.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTestMail {
    @Autowired
    JavaMailSender javaMailSender;
    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知");  // 标题
        message.setText("今晚7:30开会"); // 内容
        message.setTo("62390zll@163.com"); // 接收方
        message.setFrom("524631266@qq.com"); // 来自于哪里，必须要与发送方一致
        javaMailSender.send(message);
    }

}

package com.coderead.boot;
/**
 * @Copyright 源码阅读网 http://coderead.cn
 */

import com.coderead.boot.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @author 鲁班大叔
 * @date 2020/11/19 10:02 AM
 */
@RestController
//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class MainApplication   {
    final User user;
    @Value("#{userInfo.name}")
    String name;
    @Value("${spring.profiles.active}")
    String active;
    public MainApplication(User user) {
        System.out.println(user);
        this.user = user;
    }

    @PostConstruct
    public void init(){
        System.out.println("------初始化-------");
        System.out.println(name);
        System.out.println(active);
    }

    @RequestMapping("/")
    public String hello() {
        return "hello spring boot";
    }

    public static void main(String[] args) {
//        System.getenv().get("BOOT_ACTIVE");
        System.out.println(System.getProperty("user.dir"));
        SpringApplication.run(MainApplication.class, args);
    }

}

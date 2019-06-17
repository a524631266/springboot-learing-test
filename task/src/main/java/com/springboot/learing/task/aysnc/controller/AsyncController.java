package com.springboot.learing.task.aysnc.controller;

import com.springboot.learing.task.aysnc.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {
    @Autowired
    AsyncService asyncService;
    @RequestMapping("/hello2")
    public String hello() {
        asyncService.hello();
        return "hello";
    }
}

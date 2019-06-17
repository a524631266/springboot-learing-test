package com.springboot.learing.task.scheduled.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduledService {
    /**
     * 秒 分 时 日 月 星期
     * 0 *  *  *  *  *
     */
    @Async
    @Scheduled(cron = "0/4 * * * * MON-FRI")
    public void hello() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "hello。。。。");
        }
    }
}

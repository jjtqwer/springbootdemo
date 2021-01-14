package com.example.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@EnableAsync
@Slf4j
public class AsyncScheduledTasks {

    private static final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedDelay：固定延迟执行。距离上一次调用成功后2秒才执。
     */
    @Async
    @Scheduled(fixedDelay = 2000)
    public void reportCurrentTimeWithFixedDelay(){
        try {
            TimeUnit.SECONDS.sleep(3);
            log.info("Current Thread {}",Thread.currentThread().getName());
            log.info("Fixed Delay Task : The time is now {}",sdf.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

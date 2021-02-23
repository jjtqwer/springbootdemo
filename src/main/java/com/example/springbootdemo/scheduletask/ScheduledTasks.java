package com.example.springbootdemo.scheduletask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//@Component
@Slf4j
public class ScheduledTasks {

    private static final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
    private List<Integer> index= Arrays.asList(6,6,2,3);
    int i=0;

    /**
     * fixedRate：固定速率执行。每5秒执行一次  容器启动完成就执行
     */
    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTimeWithFixedRate(){
        log.info("Current Thread : {}",Thread.currentThread().getName());
        log.info("Fixed Rate Task: The time is now {}",sdf.format(new Date()));
        //关于 fixedRate 这里其实有个坑，假如我们有这样一种情况：我们某个方法的定时器设定的固定速率是每5秒执行一次。这个方法现在要执行下面四个任务，
        // 四个任务的耗时是：6 s、6s、 2s、 3s，请问这些任务默认情况下（单线程）将如何被执行？  结果是只会执行一次
//        if(i==0){
//            log.info("Start time is {}",sdf.format(new Date()));
//        }
//        if(i<4){
//            try {
//                TimeUnit.SECONDS.sleep(index.get(i));
//                log.info("Fixed Rate Task : The time is now {}",sdf.format(new Date()));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            i++;
//        }
    }

    /**
     * fixedDelay：固定延迟执行。距离上一次调用成功后2秒才执。  容器启动完成等2s后执行
     */
    //@Scheduled(fixedDelay = 2000)
    public void reportCurrentTimeWithFixedDelay(){
        try {
            TimeUnit.SECONDS.sleep(3);
            log.info("Fixed Delay Task : The time is now{}",sdf.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * initialDelay:初始延迟。任务的第一次执行将延迟5秒，然后将以5秒的固定间隔执行。  容器启动完成后等5s执行
     */
    //@Scheduled(initialDelay = 5000,fixedRate = 5000)
    public void reportCurrentTimeWithInitialDelay(){
        log.info("Fixed Rate Task with Ininial Delay : The time is now {}",sdf.format(new Date()));
    }

    /**
     * cron：使用Cron表达式。　每分钟的1，2秒运行
     */
    //@Scheduled(cron = "1-2 * * * * ?")
    public void reportCurrentTimeWithCronExpression(){
        log.info("Cron Expression : The time is now {}",sdf.format(new Date()));
    }
}

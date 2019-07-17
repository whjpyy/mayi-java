package com.mayi.mayispringboot.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 1000)
    public void run(){
        System.out.println("每秒打印一次");
    }
}

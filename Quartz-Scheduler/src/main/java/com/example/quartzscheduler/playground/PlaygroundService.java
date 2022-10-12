package com.example.quartzscheduler.playground;

import com.example.quartzscheduler.info.TimerInfo;
import com.example.quartzscheduler.jobs.HelloWorldJob;
import com.example.quartzscheduler.timeService.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaygroundService {
    private final SchedulerService scheduler;
    @Autowired
    public PlaygroundService(SchedulerService scheduler) {
        this.scheduler = scheduler;
    }
    public void runHelloWorldJob(){
       final TimerInfo infor=new TimerInfo();
       infor.setTotalFireCount(5);
         infor.setRepeatIntervalMs(2000);
         infor.setInitialOffsetMs(1000);
         infor.setCallbackData("HelloWorldJob");
         scheduler.schedule(HelloWorldJob.class,infor);

    }
    public List<TimerInfo> getAllRunningTimers(){
        return scheduler.getAllRunningTimers();
    }
}

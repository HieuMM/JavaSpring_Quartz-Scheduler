package com.example.quartzscheduler.until;

import com.example.quartzscheduler.info.TimerInfo;
import org.quartz.*;

public final class TimerUtils {
    private TimerUtils() {
    }

    public static JobDetail buildJobDetail(final Class jobClass, final TimerInfo infor) {
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), infor);
        return JobBuilder.newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .usingJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final TimerInfo infor) {
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMilliseconds(infor.getRepeatIntervalMs());
        if (infor.isRunningForever()) {
            builder = builder.repeatForever();
        } else {
            builder = builder.withRepeatCount(infor.getTotalFireCount() - 1);
        }
        return TriggerBuilder.newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .startAt(DateBuilder.futureDate((int) infor.getInitialOffsetMs(), DateBuilder.IntervalUnit.MILLISECOND))
                .withSchedule(builder)
                .build();
    }
}

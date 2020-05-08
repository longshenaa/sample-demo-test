package com.ls.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class JobMain {
    public static void main(String[] args) throws SchedulerException {
        //创建schedulerfactory
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        //创建jobdetail
        JobDetail build = JobBuilder.newJob(HelloJob.class)
                .withDescription("this is a job")
                .withIdentity("helloJob", "jobGroup")
                .build();
        //设置任务运行时间
        long time = System.currentTimeMillis() + 3 * 1000L;
        Date startTime = new Date(time);
        Trigger t = TriggerBuilder.newTrigger()
                .withDescription("")
                .withIdentity("helloTrigger", "helloTriggerGroup")
                .startAt(startTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
        scheduler.scheduleJob(build, t);
        scheduler.start();
    }
}
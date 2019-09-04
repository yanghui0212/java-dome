package com.yangqh.common.config;

import com.yangqh.job.MyJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * @author yangq
 * Create time in 2018-07-20 09:27
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private MyJob myJob;

    @Bean
    public MethodInvokingJobDetailFactoryBean task1JobDetail() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setTargetObject(myJob);
        factoryBean.setTargetMethod("task1");
        factoryBean.setConcurrent(false);
        return factoryBean;
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean task2JobDetail() {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setTargetObject(myJob);
        factoryBean.setTargetMethod("task2");
        factoryBean.setConcurrent(false);
        return factoryBean;
    }

    @Bean
    public CronTriggerFactoryBean task1JobDetailTrigger() {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setCronExpression("0/10 * * * * ? 2099-2099");
        triggerFactoryBean.setJobDetail(task1JobDetail().getObject());
        return triggerFactoryBean;
    }

    @Bean
    public Trigger task2JobDetailTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("5/10 * * * * ? 2099-2099");
        return TriggerBuilder.newTrigger().forJob(task2JobDetail().getObject())
                .withIdentity("task2Trigger").withSchedule(scheduleBuilder).build();
    }
}

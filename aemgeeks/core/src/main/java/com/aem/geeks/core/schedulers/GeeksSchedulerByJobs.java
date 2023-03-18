package com.aem.geeks.core.schedulers;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component(service = Job.class, immediate = true)
@Designate(ocd = SchedulerConfigurationByJobs.class)
public class GeeksSchedulerByJobs implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(GeeksSchedulerByJobs.class);
    private int schedulerJobId;

    @Reference
    private Scheduler scheduler;

    @Activate
    protected void activate(SchedulerConfigurationByJobs config){
        addSchedulerJob(config);
        schedulerJobId = config.schedulerName().hashCode();
    }

    @Deactivate
    protected void deactivate(SchedulerConfigurationByJobs config){
        removeSchedulerJob();
    }

    private void removeSchedulerJob(){
        scheduler.unschedule(String.valueOf(schedulerJobId));
    }

    private void addSchedulerJob(SchedulerConfigurationByJobs config){


        //TO EXECUTE DIFFERENT JOBS AT SPECIFIC TIMES

        // For India -->
        ScheduleOptions in = scheduler.EXPR("0 14 16 1/1 * ? *");
        Map<String, Serializable> inMap = new HashMap<>();
        inMap.put("country", "IN");
        inMap.put("url", "www.in.com");
        in.config(inMap);
        scheduler.schedule(this, in);

        // For Germany -->
        ScheduleOptions de = scheduler.EXPR("0 15 16 1/1 * ? *");
        Map<String, Serializable> deMap = new HashMap<>();
        deMap.put("country", "DE");
        deMap.put("url", "www.de.com");
        de.config(deMap);
        scheduler.schedule(this, de);

        // For United States -->
        ScheduleOptions us = scheduler.EXPR("0 16 16 1/1 * ? *");
        Map<String, Serializable> usMap = new HashMap<>();
        usMap.put("country", "US");
        usMap.put("url", "www.us.com");
        us.config(usMap);
        scheduler.schedule(this, us);
    }

    @Override
    public void execute(JobContext jobContext) {
//        LOG.info("\n =====> JOB EXECUTING");
        //when job will execute one by one, the below LOG statement will have different values
        //of country and their respective url.
        LOG.info("\n =====> COUNTRY : URL {} : {} ", jobContext.getConfiguration().get("country"),
                jobContext.getConfiguration().get("url"));
        //check the logs to verify at the mentioned cron time above.
        //every time job will run for different country
    }
}

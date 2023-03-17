package com.aem.geeks.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SchedulerConfiguration.class)
public class GeeksScheduler implements Runnable{
    private static final Logger LOG = LoggerFactory.getLogger(GeeksScheduler.class);

    private int schedulerId;

    @Reference
    private Scheduler scheduler;

    @Activate
    protected void activate(SchedulerConfiguration config){
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfiguration config){
        removeScheduler();
    }

    private void removeScheduler(){
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    private void addScheduler(SchedulerConfiguration config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this, scheduleOptions);
        LOG.info("\n ------ SCHEDULER ADDED -----");

        // if you want to execute scheduler now once, you can write as below.
        //ONCE DEPLOYED, IT'LL EXECUTE FOR ONCE!
        //if not specified,it'll only run as specified in the configuration.
        ScheduleOptions scheduleOptionsNow = scheduler.NOW();
        scheduler.schedule(this, scheduleOptionsNow);

        //also if you want to execute for 3 times after you deploy, each time,
        //it should execute after 5 seconds, then you can give this as below.
        // UNCOMMENT TO CHECK FUNCTIONALITY -- AND SEE IN ERROR.LOG

//        ScheduleOptions scheduleOptionsThreeTimes = scheduler.NOW(3,5);
//        scheduler.schedule(this, scheduleOptionsThreeTimes);

    }



    @Override
    public void run() {
        LOG.info("\n ===== RUN METHOD EXECUTING ====");
    }
}

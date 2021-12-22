package in.handyman.server.schedular;

import in.handyman.raven.lambda.process.LContext;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.server.StartProcess;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

@Slf4j
public class InstanceJob implements Job {

    public static final String VM = "VM";
    public static final String NAME = "NAME";
    public static final String LOAD_TYPE = "loadType";
    public static final String CRON = "CRON";
    public static final String LOAD_TYPE_VARIABLE = "LOAD_TYPE";

    public enum HandymanVM {
        RAVEN_VM, LEGACY
    }

    @Override
    public void execute(final JobExecutionContext jobExecutionContext) {

        final JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        final String name = jobDataMap
                .getString(NAME);
        final String vmString = jobDataMap
                .getString(VM);
        final HandymanVM handymanVM = HandymanVM.valueOf(vmString);

        if (handymanVM == HandymanVM.RAVEN_VM) {
            LambdaEngine.start(LContext.builder()
                    .pipelineName(name)
                    .processLoadType(jobDataMap
                            .getString(LOAD_TYPE))
                    .build());
        } else if (handymanVM == HandymanVM.LEGACY) {
            new StartProcess().start(name);
        }


    }
}

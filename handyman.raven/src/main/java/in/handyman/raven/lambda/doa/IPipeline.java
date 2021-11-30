package in.handyman.raven.lambda.doa;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IPipeline {

    default String getCurrentTime() {
        return LocalDateTime.now().toString();
    }

    default String getCurrentDate() {
        return LocalDate.now().toString();
    }

    Long getPipelineId();

    Long getParentPipelineId();

    Long getParentActionId();

    String getPipelineName();

    String getLambdaName();
}

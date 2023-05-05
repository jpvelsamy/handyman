package in.handyman.chidori.controller;

import in.handyman.raven.lambda.doa.audit.ExecutionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PipelineResponse {


    private Long rootPipelineId;

    private ExecutionStatus executionStatus;

}

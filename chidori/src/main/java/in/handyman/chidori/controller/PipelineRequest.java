package in.handyman.chidori.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PipelineRequest {

    @Builder.Default
    private Map<String, String> context = new HashMap<>();

    @NotNull
    private String processLoadType;

    @NotNull
    private String pipelineName;


}

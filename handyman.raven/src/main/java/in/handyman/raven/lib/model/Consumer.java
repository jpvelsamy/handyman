package in.handyman.raven.lib.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.lambda.action.ActionContext;
import in.handyman.raven.lambda.action.IActionContext;
import in.handyman.raven.lambda.doa.config.SpwResourceConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto Generated By Raven
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ActionContext(
        actionName = "Consumer"
)
public class Consumer implements IActionContext {

    private Long pcmId;

    private SpwResourceConfig source;
    private String event;

    private String name;
    private String limit;
    private String pop;

    private Boolean completed = false;
    private Boolean standalone = false;

    @JsonIgnore
    private List<RavenParser.ActionContext> actions = new ArrayList<>();
    private Boolean condition = true;

    private String threadCount;
}

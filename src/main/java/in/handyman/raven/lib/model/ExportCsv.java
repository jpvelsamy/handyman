package in.handyman.raven.lib.model;

import in.handyman.raven.lambda.action.ActionContext;
import in.handyman.raven.lambda.action.IActionContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Auto Generated By Raven
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ActionContext(
        actionName = "ExportCsv"
)
public class ExportCsv implements IActionContext {

    private String name;

    private String source;

    private String executionSource;

    private String stmt;

    private String targetLocation;

    @Builder.Default
    private Boolean condition = true;

    private String writeThreadCount;

    private String payloadSize;
}

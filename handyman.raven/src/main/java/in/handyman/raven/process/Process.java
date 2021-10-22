package in.handyman.raven.process;

import in.handyman.raven.compiler.RavenParser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class Process {

    private final Long processId;
    private final String lambdaName;

    private final Map<String, String> context;
    @Builder.Default
    private final Long start = System.nanoTime();
    private RavenParser.ProcessContext ravenParser;
    @Builder.Default
    private Integer status = ProcessStatus.STARTED;
    private Long parentPipelineId;
    @Builder.Default
    private Set<Context> tryActions = new HashSet<>();
    @Builder.Default
    private Integer tryStatus = ProcessStatus.STARTED;
    @Builder.Default
    private Set<Context> catchActions = new HashSet<>();
    @Builder.Default
    private Integer catchStatus = ProcessStatus.STARTED;
    @Builder.Default
    private Set<Context> finallyActions = new HashSet<>();
    @Builder.Default
    private Integer finallyStatus = ProcessStatus.STARTED;

    public String getProcessName() {
        if (lambdaName.contains("#")) {
            return lambdaName.substring(0, lambdaName.lastIndexOf("#"));
        }
        return lambdaName;
    }

    public void setStatus(final Integer status) {
        if (ProcessStatus.STARTED.equals(status)) {
            this.status = status;
        }
    }
}

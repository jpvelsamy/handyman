package in.handyman.raven.context;

import in.handyman.raven.process.ProcessStatus;
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
public class ProcessContext {

    private final Long processId;
    private final String instanceName;
    private final Map<String, String> context;
    @Builder.Default
    private final Long start = System.nanoTime();

    @Builder.Default
    private Integer status = ProcessStatus.STARTED;
    private Long parentProcessId;
    @Builder.Default
    private Set<ActionContext> tryActions = new HashSet<>();
    @Builder.Default
    private Integer tryStatus = ProcessStatus.STARTED;
    @Builder.Default
    private Set<ActionContext> catchActions = new HashSet<>();
    @Builder.Default
    private Integer catchStatus = ProcessStatus.STARTED;
    @Builder.Default
    private Set<ActionContext> finallyActions = new HashSet<>();
    @Builder.Default
    private Integer finallyStatus = ProcessStatus.STARTED;

    public String getProcessName() {
        if (instanceName.contains("#")) {
            return instanceName.substring(0, instanceName.lastIndexOf("#"));
        }
        return instanceName;
    }

    public void setStatus(final Integer status) {
        if (ProcessStatus.STARTED.equals(status)) {
            this.status = status;
        }
    }
}

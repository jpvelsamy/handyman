package in.handyman.raven.lib.model;

import in.handyman.raven.lambda.action.ActionContext;
import in.handyman.raven.lambda.action.IActionContext;
import java.lang.Boolean;
import java.lang.String;
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
    actionName = "PhraseMatchPaperFilter"
)
public class PhraseMatchPaperFilter implements IActionContext {
  private String name;

  private String resourceConn;

  private String processID;

  private String querySet;

  private String threadCount;

  private String readBatchSize;
  private String writeBatchSize;

  private Boolean condition = true;
}

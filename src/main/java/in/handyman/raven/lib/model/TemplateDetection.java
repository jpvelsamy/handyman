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
    actionName = "TemplateDetection"
)
public class TemplateDetection implements IActionContext {
  private String name;

  private String coproUrl;

  private String resourceConn;

  private String inputTable;

  private String processId;

  private String ouputTable;

  private String querySet;

  private Boolean condition = true;
}
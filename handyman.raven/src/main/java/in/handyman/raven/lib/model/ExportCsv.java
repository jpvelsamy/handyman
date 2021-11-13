package in.handyman.raven.lib.model;

import in.handyman.raven.lambda.action.ActionContext;
import in.handyman.raven.lambda.action.IActionContext;
import java.lang.Boolean;
import java.lang.String;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Auto Generated By Raven
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@ActionContext(
    actionName = "ExportCsv"
)
public class ExportCsv implements IActionContext {
  private String name;

  private String source;

  private String execution_source;

  private String on;

  private String location;

  private String tablename;

  private Boolean condition = true;

  private String writeThreadCount;
}
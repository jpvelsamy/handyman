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
    actionName = "EvalPatientName"
)
public class EvalPatientName implements IActionContext {
  private String name;

  private String patientName;

  private int wordCountLimit;

  private int charCountLimit;

  private String nerCoproApi;

  private int wordCountThreshold;

  private int charCountThreshold;

  private int nerApiThreshold;

  private Boolean condition = true;
}
package in.handyman.raven.lib.model;

import in.handyman.raven.lambda.Lambda;
import in.handyman.raven.lambda.LambdaContext;
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
@LambdaContext(
    lambdaName = "DropFile"
)
public class DropFile implements Lambda {
  private String name;

  private String target;

  private Boolean condition = true;
}

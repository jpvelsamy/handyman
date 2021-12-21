package in.handyman.raven.lambda.doa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpwCommonConfig extends Auditable {

    public static final String SCHEMA_NAME = "handyman_config";
    public static final String TABLE_NAME = "spw_common_config";

    private String variable;
    private String value;
    private boolean active;
    private Integer version;

}

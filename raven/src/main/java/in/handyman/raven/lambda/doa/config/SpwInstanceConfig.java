package in.handyman.raven.lambda.doa.config;

import in.handyman.raven.lambda.doa.Auditable;
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
public class SpwInstanceConfig extends Auditable {

    public static final String SCHEMA_NAME = "handyman_config";
    public static final String TABLE_NAME = "spw_instance_config";

    private Long id;

    private String instance;
    private String process;

    private String variable;
    private String value;
    private boolean active;
    private Integer version;

}

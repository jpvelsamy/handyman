package in.handyman.raven.lambda.doa.config;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import in.handyman.raven.lambda.doa.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jdbi.v3.core.Jdbi;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpwResourceConfig extends Auditable {

    public static final String SCHEMA_NAME = "handyman_config";
    public static final String TABLE_NAME = "spw_resource_config";

    private Long id;

    private String configName;
    private String driverClassName;
    private String userName;
    private String password;
    private String url;
    private String host;
    private String port;
    private String databaseName;

    private boolean active;
    private Integer version;

    public Jdbi get() {
        return Jdbi.create(url, userName, password);
    }

}

package in.handyman.raven.lambda.doa;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ResourceConnection extends Auditable {

    private String name;
    private String configType;
    private String driverClassName;
    private String userName;
    private String password;
    private String url;
    private boolean active;
    private Integer version;

    public Jdbi get(){
        return Jdbi.create(url,userName,password);
    }

}

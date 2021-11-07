package in.handyman.raven.lambda.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "config_store")
public class ConfigStore extends Auditable {

    @Id
    private Long id;

    private String name;
    private String variable;
    @Column(name = "config_type_id")
    private Integer configTypeId;

    private String value;
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(final String variable) {
        this.variable = variable;
    }

    public Integer getConfigTypeId() {
        return configTypeId;
    }

    public void setConfigTypeId(final Integer configTypeId) {
        this.configTypeId = configTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}

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
@Table(name="resource_connection")
public class ResourceConnection extends Auditable {

    @Id
    private String name;

    @Column(name = "config_type")
    private String configType;
    @Column(name = "driver_class_name")
    private String driverClassName;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private String url;
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(final String configType) {
        this.configType = configType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(final String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }
}

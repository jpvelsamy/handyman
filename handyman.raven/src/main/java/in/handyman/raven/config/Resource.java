package in.handyman.raven.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resource {

    private String configType;
    private String driverClassName;
    private String userName;
    private String password;
    private String url;
    private String name;

}

package in.handyman.raven.metric.elastic;

import io.micrometer.core.instrument.config.validate.Validated;
import io.micrometer.core.instrument.step.StepRegistryConfig;
import io.micrometer.core.lang.Nullable;

import static io.micrometer.core.instrument.config.validate.PropertyValidator.getBoolean;
import static io.micrometer.core.instrument.config.validate.PropertyValidator.getSecret;
import static io.micrometer.core.instrument.config.validate.PropertyValidator.getString;
import static io.micrometer.core.instrument.config.validate.PropertyValidator.getUrlString;

public interface MariaDBConfig extends StepRegistryConfig {

    MariaDBConfig DEFAULT = k -> null;

    default String prefix() {
        return "mariaDB";
    }

    @Nullable
    String get(String key);


    /**
     * The host to send metrics to.
     *
     * @return host
     */
    default String host() {
        return getUrlString(this, "host").orElse("http://localhost:3306");
    }

    default String tableName() {
        return getString(this, "tableName").orElse("micrometer-metrics");
    }

    default String driver() {
        return getString(this, "driver").orElse("org.mariadb.jdbc.Driver");
    }

    /**
     * The Basic Authentication username.
     *
     * @return username for Basic Authentication
     */
    @Nullable
    default String userName() {
        return getSecret(this, "userName").orElse(null);
    }

    /**
     * The Basic Authentication password.
     *
     * @return password for Basic Authentication
     */
    @Nullable
    default String password() {
        return getSecret(this, "password").orElse(null);
    }

    @Nullable
    default boolean autoCreateTable() {
        return getBoolean(this, "autoCreateTable").orElse(true);
    }

    @Override
    default Validated<?> validate() {
        return StepRegistryConfig.super.validate();
    }

}

package in.handyman.raven.lambda.access.repo;

import com.typesafe.config.ConfigFactory;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ConfigStore;
import in.handyman.raven.lambda.doa.ConfigType;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.doa.Statement;
import lombok.extern.log4j.Log4j2;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class HandymanRepoR2Impl extends AbstractAccess implements HandymanRepo {

    protected static final String CONFIG_URL = "config.r2.url";
    private static final String CONFIG_PASSWORD = "config.r2.password";
    private static final String CONFIG_USER = "config.r2.user";
    private static final String CONFIG_DRIVER = "config.r2.driver";
    private static final String CONFIG_TYPE_ID = "config_type_id";
    private static final String NAME = "name";
    private static final String VARIABLE = "variable";

    private final EntityManager entityManager;

    public HandymanRepoR2Impl() {
        var CONFIG = ConfigFactory.parseResources("handyman-raven-configstore.props");
        log.info("Initializing the config store from config file {}", CONFIG.origin().url());

        final String driver = CONFIG.getString(CONFIG_DRIVER);
        final String username = CONFIG.getString(CONFIG_USER);
        final String password = CONFIG.getString(CONFIG_PASSWORD);
        final String url = CONFIG.getString(CONFIG_URL);

        try {
            var configuration = new Configuration();
            var settings = new Properties();
            settings.put(Environment.DRIVER, driver);
            settings.put(Environment.URL, url);
            settings.put(Environment.USER, username);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(settings);

            var serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            entityManager = configuration.buildSessionFactory(serviceRegistry).createEntityManager();
        } catch (Exception e) {
            throw new HandymanException("DB Init failed", e);
        }
    }


    @Override
    public Map<String, String> getAllConfig(final String pipelineName) {
        final String lambdaName = getLambdaName(pipelineName);
        final Map<String, String> pipelineConfig = toMap(findConfigEntities(ConfigType.PIPELINE, pipelineName));
        final Map<String, String> lambdaConfig = toMap(findConfigEntities(ConfigType.LAMBDA, lambdaName));
        final Map<String, String> commonConfig = getCommonConfig();
        final Map<String, String> finalMap = new HashMap<>(pipelineConfig);
        finalMap.putAll(lambdaConfig);
        finalMap.putAll(commonConfig);
        return Map.copyOf(finalMap);
    }

    @Override
    public List<ConfigStore> findConfigEntities(final ConfigType configType, final String configName) {
        final Class<ConfigStore> clazz = ConfigStore.class;
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ConfigStore> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<ConfigStore> root = criteriaQuery.from(clazz);
        criteriaBuilder.equal(root.get(NAME), configName);
        criteriaBuilder.and(criteriaBuilder.equal(root.get(CONFIG_TYPE_ID), configType.getId()));
        criteriaQuery.select(root);
        var typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    @Override
    public Map<String, String> getCommonConfig() {
        final Class<ConfigStore> clazz = ConfigStore.class;
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ConfigStore> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<ConfigStore> root = criteriaQuery.from(clazz);
        criteriaBuilder.and(criteriaBuilder.equal(root.get(CONFIG_TYPE_ID), ConfigType.COMMON.getId()));
        criteriaQuery.select(root);
        var typedQuery = entityManager.createQuery(criteriaQuery);
        return toMap(typedQuery.getResultList());
    }

    @Override
    public ResourceConnection getResourceConfig(final String name) {
        final Class<ResourceConnection> clazz = ResourceConnection.class;
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ResourceConnection> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<ResourceConnection> root = criteriaQuery.from(clazz);
        criteriaBuilder.equal(root.get(NAME), name);
        criteriaQuery.select(root);
        var typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    @Override
    public String findValueCommonConfig(final String configName, final String variable) {
        final Class<ConfigStore> clazz = ConfigStore.class;
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ConfigStore> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<ConfigStore> root = criteriaQuery.from(clazz);
        criteriaBuilder.equal(root.get(NAME), configName);
        criteriaBuilder.equal(root.get(VARIABLE), variable);
        criteriaQuery.select(root);
        var typedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.ofNullable(typedQuery.getSingleResult()).map(ConfigStore::getValue).orElse(null);
    }

    @Override
    public Set<String> getPackageAction() {
        final Class<ConfigStore> clazz = ConfigStore.class;
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<ConfigStore> criteriaQuery = criteriaBuilder.createQuery(clazz);
        final Root<ConfigStore> root = criteriaQuery.from(clazz);
        criteriaBuilder.equal(root.get(NAME), SYS_PACKAGE);
        criteriaBuilder.and(criteriaBuilder.equal(root.get(CONFIG_TYPE_ID), ConfigType.COMMON.getId()));
        criteriaQuery.select(root);
        var typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList().stream().map(ConfigStore::getValue).collect(Collectors.toSet());
    }

    @Override
    public void insertPipeline(final Pipeline audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        entityManager.persist(audit);
    }

    @Override
    public void insertAction(final Action audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        entityManager.persist(audit);


    }

    @Override
    public void insertStatement(final Statement audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        entityManager.persist(audit);

    }

    @Override
    public void insert(final LambdaExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        entityManager.persist(audit);

    }

    @Override
    public void insert(final ActionExecutionAudit audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        entityManager.persist(audit);

    }

    @Override
    public void update(final Pipeline audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        entityManager.persist(audit);

    }

    @Override
    public void update(final Action audit) {
        audit.setLastModifiedDate(LocalDateTime.now());
        entityManager.persist(audit);

    }


}

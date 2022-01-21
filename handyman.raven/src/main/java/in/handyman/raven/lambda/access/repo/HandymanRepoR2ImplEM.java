package in.handyman.raven.lambda.access.repo;

//public class HandymanRepoR2ImplEM extends AbstractAccess implements HandymanRepo {
//
//    protected static final String CONFIG_URL = "config.r2.url";
//    private static final String CONFIG_PASSWORD = "config.r2.password";
//    private static final String CONFIG_USER = "config.r2.user";
//    private static final String CONFIG_DRIVER = "config.r2.driver";
//    private static final String CONFIG_TYPE_ID = "configTypeId";
//    private static final String NAME = "name";
//    private static final String VARIABLE = "variable";
//
//    private final EntityManager ENTITY_MANAGER;
//
//    public HandymanRepoR2ImplEM() {
//        var config = ConfigFactory.parseResources("config.props");
//        log.info("Initializing the config store from config file {}", config.origin().url());
//
//        final String driver = config.getString(CONFIG_DRIVER);
//        final String username = config.getString(CONFIG_USER);
//        final String password = config.getString(CONFIG_PASSWORD);
//        final String url = config.getString(CONFIG_URL);
//
//        try {
//            var configuration = new Configuration();
//            var settings = new Properties();
//            settings.put(AvailableSettings.DRIVER, driver);
//            settings.put(AvailableSettings.URL, url);
//            settings.put(AvailableSettings.USER, username);
//            settings.put(AvailableSettings.PASS, password);
//            settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MariaDBDialect");
//            settings.put(AvailableSettings.HBM2DDL_AUTO, "none");
//            settings.put(AvailableSettings.SHOW_SQL, "true");
//            settings.put(AvailableSettings.STORAGE_ENGINE, "innodb");
//            settings.put(AvailableSettings.FORMAT_SQL, "true");
//            settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//            settings.put(AvailableSettings.AUTOCOMMIT, "true");
//
//            configuration.setProperties(settings);
//
//            var serviceRegistry = new StandardServiceRegistryBuilder()
//                    .applySettings(configuration.getProperties()).build();
//
//            var reflections = new Reflections("in.handyman");
//
//            var classes = reflections.getTypesAnnotatedWith(javax.persistence.Entity.class);
//
//            for (Class<?> clazz : classes) {
//                configuration.addAnnotatedClass(clazz);
//            }
//
//            var sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            ENTITY_MANAGER = sessionFactory.createEntityManager();
//        } catch (Exception e) {
//            throw new HandymanException("DB Init failed", e);
//        }
//    }
//
//
//    @Override
//    public Map<String, String> getAllConfig(final String pipelineName) {
//        final String lambdaName = getLambdaName(pipelineName);
//        final Map<String, String> pipelineConfig = toMap(findConfigEntities(ConfigType.PIPELINE, pipelineName));
//        final Map<String, String> lambdaConfig = toMap(findConfigEntities(ConfigType.LAMBDA, lambdaName));
//        final Map<String, String> commonConfig = getCommonConfig();
//        final Map<String, String> finalMap = new HashMap<>(pipelineConfig);
//        finalMap.putAll(lambdaConfig);
//        finalMap.putAll(commonConfig);
//        return Map.copyOf(finalMap);
//    }
//
//    @Override
//    public List<ConfigStore> findConfigEntities(final ConfigType configType, final String configName) {
//        return configStores(configName, configType.getId());
//    }
//
//    @Override
//    public Map<String, String> getCommonConfig() {
//        final Class<ConfigStore> clazz = ConfigStore.class;
//        final CriteriaBuilder criteriaBuilder = ENTITY_MANAGER.getCriteriaBuilder();
//        final CriteriaQuery<ConfigStore> criteriaQuery = criteriaBuilder.createQuery(clazz);
//        final Root<ConfigStore> root = criteriaQuery.from(clazz);
//        criteriaBuilder.and(criteriaBuilder.equal(root.get(CONFIG_TYPE_ID), ConfigType.COMMON.getId()));
//        criteriaQuery.select(root);
//        var typedQuery = ENTITY_MANAGER.createQuery(criteriaQuery);
//        return toMap(typedQuery.getResultList());
//    }
//
//    @Override
//    public ResourceConnection getResourceConfig(final String name) {
//        final Class<ResourceConnection> clazz = ResourceConnection.class;
//        final CriteriaBuilder criteriaBuilder = ENTITY_MANAGER.getCriteriaBuilder();
//        final CriteriaQuery<ResourceConnection> criteriaQuery = criteriaBuilder.createQuery(clazz);
//        final Root<ResourceConnection> root = criteriaQuery.from(clazz);
//        criteriaBuilder.equal(root.get(NAME), name);
//        criteriaQuery.select(root);
//        var typedQuery = ENTITY_MANAGER.createQuery(criteriaQuery);
//        return typedQuery.getResultStream().findFirst().orElse(null);
//    }
//
//    @Override
//    public String findValueCommonConfig(final String configName, final String variable) {
//        final Class<ConfigStore> clazz = ConfigStore.class;
//        final CriteriaBuilder criteriaBuilder = ENTITY_MANAGER.getCriteriaBuilder();
//        final CriteriaQuery<ConfigStore> criteriaQuery = criteriaBuilder.createQuery(clazz);
//        final Root<ConfigStore> root = criteriaQuery.from(clazz);
//        criteriaBuilder.equal(root.get(NAME), configName);
//        criteriaBuilder.equal(root.get(VARIABLE), variable);
//        criteriaQuery.select(root);
//        var typedQuery = ENTITY_MANAGER.createQuery(criteriaQuery);
//        return typedQuery.getResultStream().findFirst().map(ConfigStore::getValue).orElse(null);
//    }
//
//    @Override
//    public Set<String> getPackageAction() {
//        return configStores(SYS_PACKAGE, ConfigType.COMMON.getId()).stream().map(ConfigStore::getValue).collect(Collectors.toSet());
//    }
//
//    private List<ConfigStore> configStores(final String configName, final Integer configTypeId) {
//        return ENTITY_MANAGER.createNativeQuery("SELECT id, active, config_type_id, name, value, variable, created_by, created_date, last_modified_by, last_modified_date, configTypeId FROM handyman_vm.config_store where name = ? and config_type_id = ?", ConfigStore.class)
//                .setParameter(1, configName)
//                .setParameter(2, configTypeId)
//                .getResultList();
//
//    }
//
//    @Override
//    public void insertPipeline(final Pipeline audit) {
//        audit.setLastModifiedDate(LocalDateTime.now());
//        ENTITY_MANAGER.createNativeQuery("INSERT INTO handyman_vm.pipeline (pipeline_id, created_by, created_date, last_modified_by, last_modified_date, context_node, execution_status_id, lambda_name, parent_action_id, parent_action_name, parent_pipeline_id, parent_pipeline_name, pipeline_name, file_content, host_name, mode_of_execution, pipeline_load_type, relative_path, request_body, thread_name) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
//                .setParameter(1, audit.getPipelineId())
//                .setParameter(2, audit.getCreatedBy())
//                .setParameter(3, audit.getCreatedDate())
//                .setParameter(4, audit.getLastModifiedBy())
//                .setParameter(5, audit.getLastModifiedDate())
//                .setParameter(6, audit.getContextNode())
//                .setParameter(7, audit.getExecutionStatusId())
//                .setParameter(8, audit.getLambdaName())
//                .setParameter(9, audit.getParentActionId())
//                .setParameter(10, audit.getParentActionName())
//                .setParameter(11, audit.getParentPipelineId())
//                .setParameter(12, audit.getParentPipelineName())
//                .setParameter(13, audit.getPipelineName())
//                .setParameter(14, audit.getFileContent())
//                .setParameter(15, audit.getHostName())
//                .setParameter(16, audit.getModeOfExecution())
//                .setParameter(17, audit.getPipelineLoadType())
//                .setParameter(18, audit.getRelativePath())
//                .setParameter(19, audit.getRequestBody())
//                .setParameter(20, audit.getThreadName());
//
//    }
//
//    private void save(final Object audit) {
//        //ENTITY_MANAGER.persist(audit);
//    }
//
//    @Override
//    public void insertAction(final Action audit) {
//        audit.setLastModifiedDate(LocalDateTime.now());
//        save(audit);
//
//
//    }
//
//    @Override
//    public void insertStatement(final Statement audit) {
//        audit.setLastModifiedDate(LocalDateTime.now());
//        save(audit);
//
//
//    }
//
//    @Override
//    public void insert(final LambdaExecutionAudit audit) {
//        audit.setLastModifiedDate(LocalDateTime.now());
//        save(audit);
//
//
//    }
//
//    @Override
//    public void insert(final ActionExecutionAudit audit) {
//        audit.setLastModifiedDate(LocalDateTime.now());
//        save(audit);
//    }
//
//    @Override
//    public void update(final Pipeline audit) {
//        audit.setLastModifiedDate(LocalDateTime.now());
//        save(audit);
//    }
//
//    @Override
//    public void update(final Action audit) {
//        audit.setLastModifiedDate(LocalDateTime.now());
//        save(audit);
//
//
//    }
//
//
//}

package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.config.SpwInstanceConfig;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface SpwInstanceConfigRepo {

    String COLUMNS = " id, `instance`, process, variable, value, active, created_by, created_date, last_modified_by, last_modified_date, version ";

    @SqlUpdate("insert into " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " (instance,process, variable, value, active, created_by, created_date, last_modified_by, last_modified_date, version) " +
            "VALUES (:instance, :process, :variable, :value,:active,:createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :version)")
    void insert(@BindBean final SpwInstanceConfig spwInstanceConfig);

    @SqlUpdate("UPDATE " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " SET active=:active, created_by= :createdBy , created_date= :createdDate," +
            " last_modified_by= :lastModifiedBy, last_modified_date= :lastModifiedDate , instance= :instance, process= :process , variable= :variable , value= :value WHERE id = :id ")
    void update(@BindBean final SpwInstanceConfig spwInstanceConfig);

    @SqlQuery("SELECT " + COLUMNS + " FROM  " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " where active=1 ; ")
    @RegisterBeanMapper(value = SpwInstanceConfig.class)
    List<SpwInstanceConfig> findAll();

    @SqlQuery("SELECT " + COLUMNS + " FROM  " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " where instance= :instance  and active=1 ; ")
    @RegisterBeanMapper(value = SpwInstanceConfig.class)
    List<SpwInstanceConfig> findAllByInstance(@Bind("instance") final String instance);

    @SqlQuery("SELECT count(1)+1 FROM  " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " where instance= :instance  and variable= :variable ; ")
    @RegisterBeanMapper(value = SpwInstanceConfig.class)
    Long getNextVersion(@BindBean final SpwInstanceConfig spwInstanceConfig);

    @SqlQuery("SELECT " + COLUMNS + " FROM  " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " where instance= :instance  and variable= :variable  and active=1 order by version desc limit 1; ")
    @RegisterBeanMapper(value = SpwInstanceConfig.class)
    Optional<SpwInstanceConfig> findOne(@Bind("instance") final String instance, @Bind("variable") final String variable);


    @SqlQuery("SELECT " + COLUMNS + " FROM  " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " where variable= :variable and active=1 ; ")
    @RegisterBeanMapper(value = SpwInstanceConfig.class)
    List<SpwInstanceConfig> findAllByInstanceVariable(@Bind("variable") final String variable);

    @SqlQuery("select count(1) from " + SpwInstanceConfig.SCHEMA_NAME + " where name = :name and variable= :variable and config_type_id= :configTypeId")
    Integer count(@Bind("name") final String name, @Bind("variable") final String variable, @Bind("configTypeId") final Integer configTypeId);

    @SqlQuery("SELECT " + COLUMNS + " FROM  " + SpwInstanceConfig.SCHEMA_NAME + "." + SpwInstanceConfig.TABLE_NAME + " where name = :name and variable= :variable and config_type_id= :configTypeId and active=1 order by version desc limit 1; ")
    @RegisterBeanMapper(value = SpwInstanceConfig.class)
    Optional<SpwInstanceConfig> get(@Bind("name") final String name, @Bind("variable") final String variable, @Bind("configTypeId") final Integer configTypeId);
}

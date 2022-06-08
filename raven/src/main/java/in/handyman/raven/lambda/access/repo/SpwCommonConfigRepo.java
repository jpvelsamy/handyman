package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.DoaConstant;
import in.handyman.raven.lambda.doa.config.SpwCommonConfig;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface SpwCommonConfigRepo {

    String COLUMNS = " id,  variable, value, active, created_by, created_date, last_modified_by, last_modified_date, version ";

    @SqlUpdate("insert into " + DoaConstant.CONFIG_SCHEMA_NAME + "." + DoaConstant.SCC_TABLE_NAME + " ( variable, value, active, created_by, created_date, last_modified_by, last_modified_date, version) " +
            "VALUES (  :variable, :value,:active,:createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :version)")
    void insert(@BindBean final SpwCommonConfig spwCommonConfig);

    @SqlUpdate("UPDATE " + DoaConstant.CONFIG_SCHEMA_NAME + "." + DoaConstant.SCC_TABLE_NAME + " SET active=:active, created_by= :createdBy , created_date= :createdDate," +
            " last_modified_by= :lastModifiedBy, last_modified_date= :lastModifiedDate ,  variable= :variable , value= :value WHERE id = :id ")
    void update(@BindBean final SpwCommonConfig spwCommonConfig);

    @SqlQuery("SELECT " + COLUMNS + " FROM  " + DoaConstant.CONFIG_SCHEMA_NAME + "." + DoaConstant.SCC_TABLE_NAME + " where active=1 ; ")
    @RegisterBeanMapper(value = SpwCommonConfig.class)
    List<SpwCommonConfig> findAll();

    @SqlQuery("SELECT count(1)+1 FROM  " + DoaConstant.CONFIG_SCHEMA_NAME + "." + DoaConstant.SCC_TABLE_NAME + " where variable= :variable ; ")
    @RegisterBeanMapper(value = SpwCommonConfig.class)
    Long getNextVersion(@BindBean final SpwCommonConfig spwCommonConfig);

    @SqlQuery("SELECT " + COLUMNS + " FROM  " + DoaConstant.CONFIG_SCHEMA_NAME + "." + DoaConstant.SCC_TABLE_NAME + " where variable= :variable  and active=1 order by version desc limit 1; ")
    @RegisterBeanMapper(value = SpwCommonConfig.class)
    Optional<SpwCommonConfig> findOne(@Bind("variable") final String variable);

}

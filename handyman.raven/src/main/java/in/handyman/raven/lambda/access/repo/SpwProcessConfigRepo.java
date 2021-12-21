package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.SpwProcessConfig;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SpwProcessConfigRepo {

    @SqlUpdate("insert into " + SpwProcessConfig.SCHEMA_NAME + "." + SpwProcessConfig.TABLE_NAME + " (process, variable, value, active, created_by, created_date, last_modified_by, last_modified_date, version) " +
            "VALUES ( :process, :variable, :value,:active,:createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :version)")
    void insert(@BindBean final SpwProcessConfig spwProcessConfig);

    @SqlUpdate("UPDATE " + SpwProcessConfig.SCHEMA_NAME + "." + SpwProcessConfig.TABLE_NAME + " SET active=:active, created_by= :createdBy , created_date= :createdDate," +
            " last_modified_by= :lastModifiedBy, last_modified_date= :lastModifiedDate , version= :version WHERE process= :process AND variable= :variable AND value= :value ")
    void update(@BindBean final SpwProcessConfig spwProcessConfig);



}

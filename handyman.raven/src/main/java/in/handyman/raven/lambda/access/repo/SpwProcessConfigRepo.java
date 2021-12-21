package in.handyman.raven.lambda.access.repo;

import in.handyman.raven.lambda.doa.SpwProcessConfig;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SpwProcessConfigRepo {

    @SqlUpdate("insert into " + SpwProcessConfig.SCHEMA_NAME + "." + SpwProcessConfig.TABLE_NAME + " (process, variable, value, active, created_by, created_date, last_modified_by, last_modified_date, version) VALUES ( :process, :variable, :value,:active,:createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :version)")
    void insert(@BindBean final SpwProcessConfig spwProcessConfig);
//
//    @SqlUpdate("insert into " + SpwProcessConfig.SCHEMA_NAME + "." + SpwProcessConfig.TABLE_NAME + " (process, variable, value, active, created_by, created_date, last_modified_by, last_modified_date, version) VALUES ( :process, :variable, :value,:active,:createdBy, :createdDate, :lastModifiedBy, :lastModifiedDate, :version)")
//    void insert(@BindBean final SpwProcessConfig spwProcessConfig);

}

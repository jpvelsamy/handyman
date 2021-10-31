package in.handyman.raven.lambda.access;

import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.Set;

@Log4j2
public class AuditAccess {


    static {
        ElasticsearchAccessApi.createIndices(Set.of(Pipeline.class, Action.class, Statement.class));
    }

    @SneakyThrows
    public static void insertPipeline(final Pipeline audit) {
        ElasticsearchAccessApi.saveIndex(String.valueOf(audit.getPipelineId()), audit);
    }

    public static void insertAction(final Action audit) {
        ElasticsearchAccessApi.saveIndex(String.valueOf(audit.getActionId()), audit);
    }

    public static void insertStatement(final Statement audit) {
        ElasticsearchAccessApi.saveIndex(String.valueOf(audit.getStatementId()), audit);
    }


}

package in.handyman.raven.lambda.access;

import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuditAccess {

    private static final HandymanRepo REPO = new HandymanRepoR2Impl();

    public static void insertPipeline(final Pipeline audit) {
        REPO.insertPipeline(audit);
    }

    public static void insertAction(final Action audit) {
        REPO.insertAction(audit);
    }

    public static void insertStatement(final Statement audit) {
        REPO.insertStatement(audit);
    }

    public static void insert(final LambdaExecutionAudit audit) {
        REPO.insert(audit);
    }

    public static void insert(final ActionExecutionAudit audit) {
        REPO.insert(audit);
    }

    public static void update(final Pipeline pipeline) {
        REPO.update(pipeline);
    }

    public static void update(final Action action) {
        REPO.update(action);
    }
}

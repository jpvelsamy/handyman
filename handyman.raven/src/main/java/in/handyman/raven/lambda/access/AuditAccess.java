package in.handyman.raven.lambda.access;

import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditAccess {

    private static final HandymanRepo REPO = new HandymanRepoR2Impl();

    public static void update(final Pipeline pipeline) {
        REPO.update(pipeline);
    }

    public static void update(final Action action) {
        REPO.update(action);
    }
}

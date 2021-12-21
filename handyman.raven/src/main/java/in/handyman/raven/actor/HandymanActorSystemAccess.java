package in.handyman.raven.actor;

import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ActionExecutionStatusAudit;
import in.handyman.raven.lambda.doa.PipelineExecutionStatusAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.StatementExecutionAudit;

public class HandymanActorSystemAccess {

    private static final HandymanRepo HANDYMAN_REPO = new HandymanRepoR2Impl();

    public static void insert(final Pipeline pipeline) {

        HANDYMAN_REPO.insertPipeline(pipeline);
    }

    public static void insert(final ActionExecutionAudit actionExecutionAudit) {

        HANDYMAN_REPO.insertAction(actionExecutionAudit);
    }

    public static void insert(final StatementExecutionAudit statementExecutionAudit) {

        HANDYMAN_REPO.insertStatement(statementExecutionAudit);
    }

    public static void insert(final PipelineExecutionStatusAudit pipelineExecutionStatusAudit) {

        HANDYMAN_REPO.save(pipelineExecutionStatusAudit);
    }

    public static void insert(final ActionExecutionStatusAudit actionExecutionStatusAudit) {

        HANDYMAN_REPO.save(actionExecutionStatusAudit);


    }


    public static void update(final Pipeline pipeline) {

        HANDYMAN_REPO.update(pipeline);
    }

    public static void update(final ActionExecutionAudit actionExecutionAudit) {

        HANDYMAN_REPO.update(actionExecutionAudit);

    }
}

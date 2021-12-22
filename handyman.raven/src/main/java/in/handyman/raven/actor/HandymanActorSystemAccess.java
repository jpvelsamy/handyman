package in.handyman.raven.actor;

import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;

public class HandymanActorSystemAccess {

    private static final HandymanRepo HANDYMAN_REPO = new HandymanRepoR2Impl();

    public static void insert(final Pipeline pipeline) {

        HANDYMAN_REPO.insertPipeline(pipeline);
    }

    public static void insert(final Action action) {

        HANDYMAN_REPO.insertAction(action);
    }

    public static void insert(final Statement statement) {

        HANDYMAN_REPO.insertStatement(statement);
    }

    public static void insert(final LambdaExecutionAudit lambdaExecutionAudit) {

        HANDYMAN_REPO.save(lambdaExecutionAudit);
    }

    public static void insert(final ActionExecutionAudit actionExecutionAudit) {

        HANDYMAN_REPO.save(actionExecutionAudit);


    }


    public static void update(final Pipeline pipeline) {

        HANDYMAN_REPO.update(pipeline);
    }

    public static void update(final Action action) {

        HANDYMAN_REPO.update(action);

    }
}

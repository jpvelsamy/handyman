package in.handyman.raven.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import in.handyman.raven.lambda.access.AuditAccess;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;

public class HandymanActorSystemAccess {

    private static final ActorSystem SYSTEM = ActorSystem.create("Handyman-DAO-system");

    public static void insert(final Pipeline pipeline) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                pipeline.getPipelineName() + ":" + pipeline.getPipelineId());
        actorRef.tell(pipeline, ActorRef.noSender());
//        AuditAccess.insertPipeline(pipeline);
    }

    public static void insert(final Action action) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                action.getActionName() + "#" + action.getActionId());
        actorRef.tell(action, ActorRef.noSender());
//        AuditAccess.insertAction(action);
    }

    public static void insert(final Statement statement) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                statement.getStatementId() + "#" + statement.getActionId());
        actorRef.tell(statement, ActorRef.noSender());
//        AuditAccess.insertStatement(statement);
    }

    public static void insert(final LambdaExecutionAudit lambdaExecutionAudit) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                lambdaExecutionAudit.getId() + "#" + lambdaExecutionAudit.getPipelineId());
        actorRef.tell(lambdaExecutionAudit, ActorRef.noSender());
    }

    public static void insert(final ActionExecutionAudit actionExecutionAudit) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                actionExecutionAudit.getId() + "#" + actionExecutionAudit.getActionId());
        actorRef.tell(actionExecutionAudit, ActorRef.noSender());
    }



    public static void update(final Pipeline pipeline) {
        AuditAccess.update(pipeline);
    }

    public static void update(final Action action) {
        AuditAccess.update(action);
    }
}

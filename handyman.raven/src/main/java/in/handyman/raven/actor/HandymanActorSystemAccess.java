package in.handyman.raven.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.LambdaExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;

import java.util.concurrent.atomic.AtomicInteger;

public class HandymanActorSystemAccess {

    private static final ActorSystem SYSTEM = ActorSystem.create("Handyman-DAO-system");
    private static final String INSERT = "INSERT:";
    private static final String UPDATE = "UPDATE:";
    private static final AtomicInteger COUNTER = new AtomicInteger();

    public static void insert(final Pipeline pipeline) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                INSERT + pipeline.getPipelineId());
        actorRef.tell(pipeline, ActorRef.noSender());
//        AuditAccess.insertPipeline(pipeline);
    }

    public static void insert(final Action action) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                INSERT + action.getActionId());
        actorRef.tell(action, ActorRef.noSender());
//        AuditAccess.insertAction(action);
    }

    public static void insert(final Statement statement) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                INSERT + statement.getStatementId());
        actorRef.tell(statement, ActorRef.noSender());
//        AuditAccess.insertStatement(statement);
    }

    public static void insert(final LambdaExecutionAudit lambdaExecutionAudit) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                INSERT + lambdaExecutionAudit.getId());
        actorRef.tell(lambdaExecutionAudit, ActorRef.noSender());
    }

    public static void insert(final ActionExecutionAudit actionExecutionAudit) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditInsertActor.class),
                INSERT + actionExecutionAudit.getId());
        actorRef.tell(actionExecutionAudit, ActorRef.noSender());
    }


    public static void update(final Pipeline pipeline) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditUpdateActor.class),
                UPDATE + pipeline.getPipelineId() + ":" + COUNTER.incrementAndGet());
        actorRef.tell(pipeline, ActorRef.noSender());
    }

    public static void update(final Action action) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditUpdateActor.class),
                UPDATE + action.getActionId() + ":" + COUNTER.incrementAndGet());
        actorRef.tell(action, ActorRef.noSender());
    }
}

package in.handyman.raven.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;

public class HandymanActorSystemAccess {

    private static final ActorSystem SYSTEM = ActorSystem.create("Handyman-DAO-system");

    public static void insert(final Pipeline pipeline) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                pipeline.getPipelineName() + ":" + pipeline.getPipelineId());
        actorRef.tell(pipeline, ActorRef.noSender());
    }

    public static void insert(final Action action) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                action.getActionName() + "#" + action.getActionId());
        actorRef.tell(action, ActorRef.noSender());
    }

    public static void insert(final Statement statement) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                statement.getStatementId() + "#" + statement.getActionId());
        actorRef.tell(statement, ActorRef.noSender());
    }

}

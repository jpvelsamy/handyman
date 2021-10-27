package in.handyman.raven.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import in.handyman.raven.audit.AuditPayload;
import in.handyman.raven.process.doa.ActionAudit;
import in.handyman.raven.process.doa.PipelineAudit;
import in.handyman.raven.process.doa.StatementAudit;
import in.handyman.raven.util.UniqueID;

public class HandymanActorSystemAccess {

    private static final ActorSystem SYSTEM = ActorSystem.create("Handyman-DAO-system");

    public static void doAudit(final AuditPayload context) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                context.getAuditType().name() + "-" + UniqueID.getId());
        actorRef.tell(context, ActorRef.noSender());
    }

    public static void insert(final PipelineAudit pipelineAudit) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                pipelineAudit.getPipelineName() + "#" + pipelineAudit.getPipelineId());
        actorRef.tell(pipelineAudit, ActorRef.noSender());
    }

    public static void insert(final ActionAudit actionAudit) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                actionAudit.getActionName() + "#" + actionAudit.getActionId());
        actorRef.tell(actionAudit, ActorRef.noSender());
    }

    public static void insert(final StatementAudit statementAudit) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                statementAudit.getStatementId() + "#" + statementAudit.getActionId());
        actorRef.tell(statementAudit, ActorRef.noSender());
    }

}

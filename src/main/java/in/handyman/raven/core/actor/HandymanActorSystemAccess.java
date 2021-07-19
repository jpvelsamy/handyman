package in.handyman.raven.core.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import in.handyman.raven.core.audit.AuditPayload;
import in.handyman.raven.core.util.UniqueID;

public class HandymanActorSystemAccess {

    private static final ActorSystem SYSTEM = ActorSystem.create("Handyman-system");

    public static void doAudit(final AuditPayload context) {
        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
                context.getAuditType().name() + "-" + UniqueID.getId());
        actorRef.tell(context, ActorRef.noSender());
    }

}

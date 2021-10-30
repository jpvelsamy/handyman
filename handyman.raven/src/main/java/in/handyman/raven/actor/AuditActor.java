package in.handyman.raven.actor;

import akka.actor.AbstractActor;
import in.handyman.raven.lambda.access.AuditAccess;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;

public class AuditActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Pipeline.class, AuditAccess::insertPipeline)
                .match(Action.class, AuditAccess::insertAction)
                .match(Statement.class, AuditAccess::insertStatement)
                .build();
    }
}

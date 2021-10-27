package in.handyman.raven.actor;

import akka.actor.AbstractActor;
import in.handyman.raven.access.AuditAccess;
import in.handyman.raven.process.doa.ActionAudit;
import in.handyman.raven.process.doa.PipelineAudit;
import in.handyman.raven.process.doa.StatementAudit;

public class AuditActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PipelineAudit.class, AuditAccess::insertPipeline)
                .match(ActionAudit.class, AuditAccess::insertAction)
                .match(StatementAudit.class, AuditAccess::insertStatement)
                .build();
    }
}

package in.handyman.raven.actor;

import akka.actor.AbstractActor;
import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.audit.ActionExecutionStatusAudit;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionAudit;
import in.handyman.raven.lambda.doa.audit.PipelineExecutionStatusAudit;
import in.handyman.raven.lambda.doa.audit.StatementExecutionAudit;

public class AuditInsertActor extends AbstractActor {

    private final HandymanRepo REPO = new HandymanRepoR2Impl();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PipelineExecutionAudit.class, REPO::insertPipeline)
                .match(ActionExecutionAudit.class, REPO::insertAction)
                .match(StatementExecutionAudit.class, REPO::insertStatement)
                .match(PipelineExecutionStatusAudit.class, REPO::save)
                .match(ActionExecutionStatusAudit.class, REPO::save)
                .build();
    }
}

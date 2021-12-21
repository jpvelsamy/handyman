package in.handyman.raven.actor;

import akka.actor.AbstractActor;
import in.handyman.raven.lambda.access.repo.HandymanRepo;
import in.handyman.raven.lambda.access.repo.HandymanRepoR2Impl;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.Pipeline;

public class AuditUpdateActor extends AbstractActor {

    private final HandymanRepo REPO = new HandymanRepoR2Impl();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Pipeline.class, REPO::update)
                .match(ActionExecutionAudit.class, REPO::update)
                .build();
    }
}

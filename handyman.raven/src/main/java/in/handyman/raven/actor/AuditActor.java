package in.handyman.raven.actor;

import akka.actor.AbstractActor;
import in.handyman.raven.audit.AuditPayload;
import in.handyman.raven.audit.AuditService;

public class AuditActor extends AbstractActor {


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(AuditPayload.class, context -> {
                    switch (context.getAuditType()) {
                        case CREATE_INSTANCE_AUDIT:
                            AuditService.insertInstanceAudit(context.getProcessId(),
                                    context.getInstanceName(), context.getRunMode(),
                                    context.getMachine(), context.getFile());
                            break;
                        case UPDATE_INSTANCE_AUDIT:
                            AuditService.updateInstanceAudit(context.getProcessId(),
                                    context.getStatus(), context.getContextLog(), context.getInstanceName());
                            break;
                        case CREATE_COMMAND_AUDIT:
                            AuditService.insertCommandAudit(context.getActionId(),
                                    context.getProcessId(), context.getActionName(),
                                    context.getProcessName());
                            break;
                        case UPDATE_COMMAND_AUDIT:
                            AuditService.updateCommandAudit(context.getActionId(), context.getStatus(), context.getCommandDetail());
                            break;
                        case CREATE_STATEMENT_AUDIT:
                            AuditService.insertStatementAudit(context.getStatementId(), context.getActionId(), context.getActionName(), context.getProcessName());
                            break;
                        case UPDATE_STATEMENT_AUDIT:
                            AuditService.updateStatementAudit(context.getStatementId(), context.getRowsW(), context.getRowsR(), context.getStatement(), context.getStatus());
                            break;
                        case CREATE_BATCH_AUDIT:
                            //TODO
                            break;
                    }
                }).build();
    }
}

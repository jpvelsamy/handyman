package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DirPath;
import in.handyman.raven.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "DirPath"
)
public class DirPathAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final DirPath dirPath;

    private final Marker aMarker;

    public DirPathAction(final ActionExecutionAudit action, final Logger log, final Object dirPath) {
        this.dirPath = (DirPath) dirPath;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" DirPath:" + this.dirPath.getName());
    }

    @Override
    public void execute() throws Exception {
        log.info(aMarker, "FileDetails Action for {} has been started" , dirPath.getName());
        try {
            final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(dirPath.getResourceConn());
            DirectoryDetails input = DirectoryDetails.builder()
                    .given_file_path(dirPath.getFilePath())
                    .given_file_checksum(null)
                    .build();

            jdbi.useTransaction(handle -> {

                handle.createUpdate("INSERT INTO macro.file_path_details" +
                                "(given_file_path, given_file_checksum)" +
                                " VALUES( :given_file_path, :given_file_checksum);")
                        .bindBean(input).execute();
                log.debug(aMarker, "inserted {} into true positive result", input);
                log.info(aMarker, "File Details Action for {} has been Completed" , dirPath.getName());
            });
        } catch (Exception e) {
            log.error(aMarker, "Error in inserting file path details {}", ExceptionUtil.toString(e));
            throw new HandymanException("Error in inserting file path details", e, action);
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return dirPath.getCondition();
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DirectoryDetails {

        private String given_file_path;
        private String given_file_checksum;
    }
}

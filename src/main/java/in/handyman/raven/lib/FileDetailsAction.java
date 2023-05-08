package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.FileDetails;
import in.handyman.raven.util.ExceptionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "FileDetails"
)
public class FileDetailsAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final FileDetails fileDetails;

    private final Marker aMarker;

    public FileDetailsAction(final ActionExecutionAudit action, final Logger log,
                             final Object fileDetails) {
        this.fileDetails = (FileDetails) fileDetails;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" FileDetails:" + this.fileDetails.getName());
    }

    @Override
    public void execute() throws Exception {
        try {
            final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(fileDetails.getResourceConn());

            List<Path> pathList = new ArrayList<>();
            try (var files = Files.walk(Path.of(fileDetails.getDirpath())).filter(Files::isRegularFile)) {
                files.forEach(pathList::add);
            }
            pathList.forEach(path -> {
                File file = new File(path.toUri());
                String sha1Hex;
                try (InputStream is = Files.newInputStream(Path.of(file.getPath()))) {
                    sha1Hex = org.apache.commons.codec.digest.DigestUtils.sha1Hex(is);
                } catch (IOException e) {
                    throw new HandymanException("Error in reading the input stream", e, action);
                }

                var fileSize = file.length() / 1024;
                FileInfo fileInfo = FileInfo.builder()
                        .file_ref_id(file.getName().toUpperCase() + " - " + (int) (900000 * random() + 100000))
                        .file_path(file.getPath())
                        .file_name(FilenameUtils.removeExtension(file.getName()))
                        .file_checksum(sha1Hex)
                        .file_size(fileSize)
                        .file_extension(FilenameUtils.getExtension(file.getName()))
                        .group_id(fileDetails.getGroup_id())
                        .created_user_id(-1)
                        .tenant_id(-1)
                        .inbound_id(Integer.valueOf(fileDetails.getInbound_id()))
                        .build();

                jdbi.useTransaction(handle -> {
                    handle.createUpdate("INSERT INTO ingestion.source_of_origin (file_ref_id, file_path, file_name, file_checksum, file_size, file_extension, group_id, created_user_id, tenant_id, inbound_id) " +
                                    "VALUES( :file_ref_id, :file_path, :file_name, :file_checksum, :file_size, :file_extension, :group_id, :created_user_id, :tenant_id, :inbound_id );")
                            .bindBean(fileInfo).execute();
                    log.debug(aMarker, "inserted {} into source of origin", fileInfo);
                });
                action.getContext().put("group_id", fileDetails.getGroup_id());

            });
        } catch (Exception e) {
            log.error(aMarker, "Error in file details action with exception {}", ExceptionUtil.toString(e));
            throw new HandymanException("Error in file details action", e, action);
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return fileDetails.getCondition();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FileInfo {

        private Integer origin_id;
        private String file_ref_id;
        private String file_path;
        private String file_name;
        private String file_checksum;
        private Long file_size;
        private String file_extension;
        private String group_id;
        private Integer created_user_id;
        private Integer tenant_id;
        private LocalDateTime created_on;
        private Integer inbound_id;
        private String file_process_status;
    }
}

package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.Checksum;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "Checksum"
)
public class ChecksumAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final Checksum checksum;

    private final Marker aMarker;

    public ChecksumAction(final ActionExecutionAudit action, final Logger log,
                          final Object checksum) {
        this.checksum = (Checksum) checksum;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" Checksum:" + this.checksum.getName());
    }

    @Override
    public void execute() throws Exception {

        log.info(aMarker, "<-------Checksum Action for {} has been started------->" + checksum.getName());
        String filePath = checksum.getFilePath();
        Thread.sleep(5000);
        log.info(aMarker, "FilePath and Checksum details -> {} {}", filePath, checksum);

        try (InputStream is = Files.newInputStream(Paths.get(filePath))) {
            String sha1Hex = org.apache.commons.codec.digest.DigestUtils.sha1Hex(is);
            log.debug("MD5 CHECKSUM details {} for file {} ", sha1Hex, filePath);
            action.getContext().put(checksum.getName(), sha1Hex);
        }
        log.info(aMarker, "<-------Checksum Action for {} has been Completed------->" + checksum.getName());
    }

    @Override
    public boolean executeIf() throws Exception {
        return checksum.getCondition();
    }
}
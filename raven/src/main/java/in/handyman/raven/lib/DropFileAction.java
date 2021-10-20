package in.handyman.raven.lib;

import in.handyman.raven.action.ActionExecution;
import in.handyman.raven.action.IActionExecution;
import in.handyman.raven.lib.model.DropFile;
import in.handyman.raven.process.Context;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.nio.file.Files;

/**
 * Auto Generated By Raven
 */
@ActionExecution(actionName = "DropFile")
@Log4j2
public class DropFileAction implements IActionExecution {

    private final Context context;
    private final DropFile dropFile;
    private final MarkerManager.Log4jMarker aMarker;

    public DropFileAction(final Context context, final Object dropFile) {
        this.dropFile = (DropFile) dropFile;
        this.context = context;
        this.aMarker = new MarkerManager.Log4jMarker("DropFile");
        this.context.getDetailMap().putPOJO("context", dropFile);
    }

    @Override
    public void execute() throws Exception {
        var filePath = dropFile.getTarget();
        var name = dropFile.getName();
        var id = context.getProcessId();

        log.info(aMarker, " id#{}, name#{}, file#{}", id, name, filePath);

        var file = new File(filePath);
        Files.deleteIfExists(file.toPath());
        log.info(aMarker, " Completed id#{}, name#{}, file#{}", id, name, file);
    }

    @Override
    public boolean executeIf() throws Exception {
        return dropFile.getCondition();
    }
}

package in.handyman.raven.process;

import in.handyman.raven.exception.HandymanException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Log4j2
public class HRequestResolver {

    private static final String BASE_PATH = "basepath";

    protected static String doResolve(final String processLoadType, final Process process) {
        final LoadType loadType = LoadType.valueOf(processLoadType);
        if (LoadType.DB == loadType) {
            return dbResolve(process, loadType);
        } else if (LoadType.FILE == loadType) {
            return fileResolve(process, loadType);
        } else {
            throw new HandymanException("Process definition for " + process.getProcessName() + " is unknown");
        }
    }

    private static String dbResolve(final Process process, final LoadType loadType) {
        return process.getContext().get(loadType.getVariable());
    }

    private static String fileResolve(final Process process, final LoadType loadType) {
        final String filePath = process.getContext().getOrDefault(loadType.getVariable(), "");
        final String basePath = process.getContext().getOrDefault(BASE_PATH, "");
        final String instanceName = process.getLambdaName();
        if (filePath.isEmpty()) {
            throw new HandymanException("File path configuration for process " + instanceName + " is missing, check spw_process_config or spw_instance_config");
        }
        return readFile(filePath, basePath);
    }

    protected static String readFile(final String filePath, final String basePath) {
        final String resolvePath = doPathResolve(filePath, basePath);
        try {
            return Files.readString(Path.of(resolvePath));
        } catch (IOException e) {
            throw new HandymanException("Read process definition for " + resolvePath + " is failed", e);
        }
    }

    private static String doPathResolve(final String fileRelativePath, final String basePath) {
        final String fullPath = (Objects.nonNull(basePath) && !basePath.isEmpty() ? (basePath + "/") : "") + fileRelativePath;
        log.info("Trying to find the process file {}", fullPath);
        final URL finalPathUrl = HRequestResolver.class.getClassLoader().getResource(fullPath);
        if (finalPathUrl != null) {
            return finalPathUrl.getPath();
        } else {
            throw new HandymanException("Process definition for " + fileRelativePath + " is absent");
        }
    }

    @Getter
    @AllArgsConstructor
    public enum LoadType {
        DB("processContent"), FILE("filepath");
        private final String variable;
    }

}
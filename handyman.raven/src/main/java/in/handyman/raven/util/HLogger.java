package in.handyman.raven.util;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Objects;

@Slf4j
public class HLogger {

    private HLogger() {
    }

    public static void info(final ActionExecutionAudit actionExecutionAudit, final Logger logger, final String logMessage, final Object... objects) {
        var aMarker = MarkerFactory.getMarker(actionExecutionAudit.getMacroName() + ":" + actionExecutionAudit.getActionName());
        logger.info(aMarker, logMessage, objects);
        log.info(aMarker, logMessage, objects);
    }

    public static void error(final ActionExecutionAudit actionExecutionAudit, final Logger logger, final Exception e, final String logMessage, final Object... objects) {
        var aMarker = MarkerFactory.getMarker(actionExecutionAudit.getMacroName() + ":" + actionExecutionAudit.getActionName());
        logger.error(aMarker, logMessage, objects, e);
        error(aMarker, e, logMessage, objects);
    }

    private static void error(final Marker aMarker, final Exception e, final String logMessage, final Object[] objects) {
        log.error(aMarker, "{}: {}", e.getClass().getSimpleName(), e.getMessage());
        printANdThrow(logMessage, objects, aMarker, e);
    }

    private static void printANdThrow(final String logMessage, final Object[] objects, final Marker aMarker, final Exception e) {
        if (Objects.nonNull(logMessage) && Objects.nonNull(objects)) {
            log.error(aMarker, logMessage, objects);
        } else if (Objects.nonNull(logMessage)) {
            log.error(aMarker, logMessage);
        }
        if (e instanceof HandymanException) {
            throw (HandymanException) e;
        } else {
            throw new HandymanException(aMarker.getName(), e);
        }
    }

    public static void trace(final ActionExecutionAudit actionExecutionAudit, final Logger logger, final Exception e, final String logMessage, final Object... objects) {
        var aMarker = MarkerFactory.getMarker(actionExecutionAudit.getMacroName() + ":" + actionExecutionAudit.getActionName());
        logger.trace(aMarker, logMessage, objects, e);
        log.trace(aMarker, "{}: {}", e.getClass().getSimpleName(), e.getMessage(), e);
        printANdThrow(logMessage, objects, aMarker, e);
    }

    public static void error(final Exception e, final String logMessage, final Object... objects) {
        var aMarker = MarkerFactory.getMarker(HLogger.class.getSimpleName());
        error(aMarker, e, logMessage, objects);
    }

    public static void trace(final Exception e, final String logMessage, final Object... objects) {
        var aMarker = MarkerFactory.getMarker(HLogger.class.getSimpleName());
        error(aMarker, e, logMessage, objects);
        log.trace(logMessage, e);
    }

}

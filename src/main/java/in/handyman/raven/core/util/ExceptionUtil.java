package in.handyman.raven.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLWarning;

public class ExceptionUtil {

    public static String toString(final Throwable ex) {
        final StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static String completeSQLWarning(final SQLWarning sqlWarnings) {
        var errors = new StringWriter();
        var print = new PrintWriter(errors);
        if (sqlWarnings != null) {
            sqlWarnings.printStackTrace(print);
        } else {
            print.print("NONE");
        }
        return errors.toString();
    }

}

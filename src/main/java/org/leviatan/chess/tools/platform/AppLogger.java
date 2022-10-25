package org.leviatan.chess.tools.platform;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * AppLogger.
 *
 * @author alciucam
 *
 */
public final class AppLogger {

    private static final Logger LOGGER = Logger.getLogger(AppLogger.class.getName());

    private AppLogger() {
    }

    /**
     * Log debug.
     *
     * @param message
     *            message
     */
    public static synchronized void logDebug(final String message) {
        LOGGER.info(message);
    }

    /**
     * Log error.
     *
     * @param message
     *            message
     */
    public static synchronized void logError(final String message) {
        LOGGER.severe(message);
    }

    /**
     * Log error.
     *
     * @param message
     *            message
     * @param t
     *            Throwable
     */
    public static synchronized void logError(final String message, final Throwable t) {
        LOGGER.log(Level.SEVERE, message, t);
    }

    /**
     * Log error not very verbose.
     *
     * @param message
     *            message
     * @param t
     *            Throwable
     */
    public static synchronized void logErrorNotVerbose(final String message, final Throwable t) {
        LOGGER.log(Level.SEVERE, message + " >> " + t);
    }

    /**
     * Logs the data.
     *
     * @param label
     *            label
     * @param data
     *            data
     */
    public static synchronized void logData(final String label, final double[] data) {

        final StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (final double d : data) {
            sb.append("   " + d);
        }

        sb.append("]");

        logDebug(label + ": " + sb.toString());
    }
}

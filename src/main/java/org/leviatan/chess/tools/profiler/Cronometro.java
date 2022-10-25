package org.leviatan.chess.tools.profiler;

import java.util.Date;

import org.leviatan.chess.tools.log.Logger;

/**
 * Cronometro.
 *
 * @author Alejandro
 */
public class Cronometro {

    private Date timestamp;
    private final boolean trace;

    /**
     * Constructor for Cronometro.
     *
     * @param trace
     *            trace
     */
    public Cronometro(final boolean trace) {
        this.trace = trace;
    }

    /**
     * Calculo de tiempos.
     *
     * @param text
     *            text
     */
    public void tic(final String text) {
        print(text);
        timestamp = new Date();
    }

    /**
     * Calculo de tiempos.
     *
     * @param text
     *            text
     */
    public void toc(final String text) {
        final Date ahora = new Date();

        final long diffMillis = ahora.getTime() - timestamp.getTime();
        final long diffSecs = diffMillis / 1000;
        final long diffMillisSinSegs = diffMillis % 1000;

        final long diffMin = diffSecs / 60;
        final long diffSecsSinMin = diffSecs % 60;

        final String textoLog = text + " tardó " + diffMin + " min " + diffSecsSinMin + " segs " + diffMillisSinSegs + " millis";
        print(textoLog);
    }

    private void print(final String textoLog) {

        if (trace) {
            Logger.trace(textoLog);
        } else {
            Logger.log(textoLog);
        }
    }
}

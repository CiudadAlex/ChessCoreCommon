package org.leviatan.chess.tools.profiler;

import org.leviatan.chess.tools.log.Logger;

/**
 * Progreso.
 *
 * @author Alejandro
 */
public class Progreso {

    /** Cuenta de las que lleva. */
    private double cuenta;

    /** Total a contar. */
    private double total = 1;

    /**
     * Constructor para Progreso.
     *
     * @param texto
     *            texto
     * @param total
     *            total
     */
    public Progreso(final String texto, final long total) {
        this.total = total;
        Logger.log(texto + " " + total);
    }

    /** Aumenta la cuenta. */
    public void aumentaCuenta() {
        cuenta++;

        if (cuenta % 100 == 0) {
            Logger.log("" + 100 * cuenta / total);
        }
    }
}

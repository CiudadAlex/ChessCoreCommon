package org.leviatan.chess.tools.log;

import java.io.PrintStream;
import java.util.List;

import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;

/**
 * Logger.
 *
 * @author Alejandro
 */
public final class Logger {

    private Logger() {
    }

    /**
     * Muestra el error.
     *
     * @param txt
     *            text
     * @param t
     *            throwable
     */
    public static void logError(final String txt, final Throwable t) {
        log(txt, System.err);

        if (t != null) {
            t.printStackTrace(System.err);
        }
    }

    /**
     * Traza el texto dado.
     *
     * @param txt
     *            text
     */
    public static void trace(final String txt) {

        if (Configuracion.IS_TRACE_ENABLED) {
            log("[TRACE] " + txt);
        }
    }

    /**
     * Log de una lista de Movimiento.
     *
     * @param listaMovimiento
     *            listaMovimiento
     */
    public static void logListaMovimientos(final List<Movimiento> listaMovimiento) {

        log(" ---------------------------------- ");

        for (final Movimiento movimiento : listaMovimiento) {
            log(movimiento.toString());
        }

        log(" ---------------------------------- ");
    }

    /**
     * Log de una lista de MovimientoYConsecuencias.
     *
     * @param listaMovimientoYConsecuencias
     *            listaMovimientoYConsecuencias
     */
    public static void log(final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias) {

        log(" ---------------------------------- ");

        for (final MovimientoYConsecuencias movimientoYConsecuencias : listaMovimientoYConsecuencias) {
            log(movimientoYConsecuencias);
        }

        log(" ---------------------------------- ");
    }

    /**
     * Log de un MovimientoYConsecuencias.
     *
     * @param movimientoYConsecuencias
     *            movimientoYConsecuencias
     */
    public static void log(final MovimientoYConsecuencias movimientoYConsecuencias) {
        final float gananciaAcumulada = movimientoYConsecuencias.getGananciaAcumulada();
        final Movimiento movimiento = movimientoYConsecuencias.getMovimiento();

        trace(" ganancia = " + gananciaAcumulada + " " + movimiento.toString());
    }

    /**
     * Muestra el mensaje.
     *
     * @param txt
     *            txt
     */
    public static void log(final String txt) {
        log(txt, System.out);
    }

    private static void log(final String txt, final PrintStream ps) {
        ps.println(txt);
    }
}

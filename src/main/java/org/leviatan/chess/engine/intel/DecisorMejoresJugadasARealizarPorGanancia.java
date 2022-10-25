package org.leviatan.chess.engine.intel;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;
import org.leviatan.chess.tools.log.Logger;

/**
 * DecisorMejoresJugadasARealizarPorGanancia.
 *
 * @author Alejandro
 */
public final class DecisorMejoresJugadasARealizarPorGanancia {

    private DecisorMejoresJugadasARealizarPorGanancia() {
    }

    /**
     * Decide las mejores jugadas. Cualquiera de ellas será buena por lo que se
     * puede elegis aleatoriamente.
     *
     * @param listaMovimientoYConsecuenciasPosibles
     *            listaMovimientoYConsecuenciasPosibles
     * @return List<MovimientoYConsecuencias>
     */
    public static List<MovimientoYConsecuencias> decideMejoresJugadas(
            final List<MovimientoYConsecuencias> listaMovimientoYConsecuenciasPosibles) {

        return devuelveLasJugadasDeMaximaGanancia(listaMovimientoYConsecuenciasPosibles);
    }

    /** Devuelve la lista de las jugadas con maxima ganancia. */
    private static List<MovimientoYConsecuencias> devuelveLasJugadasDeMaximaGanancia(
            final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias) {

        final float gananciaAcumuladaMaxima = CalculadorGananciaAcumulada.obtenerGananciaAcumuladaMaxima(listaMovimientoYConsecuencias);
        Logger.trace("gananciaAcumuladaMaxima = " + gananciaAcumuladaMaxima);

        return devuelveLasJugadasDeGananciaDada(listaMovimientoYConsecuencias, gananciaAcumuladaMaxima);
    }

    /** Devuelve la lista de las jugadas con maxima ganancia. */
    private static List<MovimientoYConsecuencias> devuelveLasJugadasDeGananciaDada(
            final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias, final float gananciaAcumuladaMaxima) {

        final List<MovimientoYConsecuencias> listaNodosGananciaDada = new ArrayList<MovimientoYConsecuencias>();

        for (final MovimientoYConsecuencias movimientoYConsecuenciasTreeNode : listaMovimientoYConsecuencias) {

            if (gananciaAcumuladaMaxima == movimientoYConsecuenciasTreeNode.getGananciaAcumulada()) {
                listaNodosGananciaDada.add(movimientoYConsecuenciasTreeNode);
            }
        }

        return listaNodosGananciaDada;
    }

}

package org.leviatan.chess.engine.intel.generadorarbol;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Casilla;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.movimientosposibles.AbstractGeneradorMovimientosPosibles;

/**
 * HelperMovimientosPosibles.
 *
 * @author Alejandro
 */
public final class HelperMovimientosPosibles {

    /* Lista de casillas */
    private static List<Casilla> listaCasillaSTATIC = new ArrayList<Casilla>(Configuracion.TALLA_INICIAL_ARRAYLIST_MOVIMIENTOS);

    private HelperMovimientosPosibles() {
    }

    /**
     * Obtiene los movimientos posibles del bando.
     *
     * @param tablero
     *            tablero
     * @param bando
     *            bando
     * @return los movimientos posibles del bando
     */
    public static List<Movimiento> getMovimientosPosiblesDeBando(final Tablero tablero, final Bando bando) {

        final List<Movimiento> listaMovimientosPosibles = new ArrayList<Movimiento>(Configuracion.TALLA_INICIAL_ARRAYLIST_MOVIMIENTOS);
        final List<Casilla> listaCasilla = getCasillasOcupadasPorBando(tablero, bando);

        for (final Casilla casilla : listaCasilla) {
            final AbstractGeneradorMovimientosPosibles generadorMovimientosPosibles = casilla.getFicha().getTipoFicha()
                    .getGeneradorMovimientosPosibles();
            generadorMovimientosPosibles.generaMovimientosPosiblesYAddToLista(casilla.getPosicionTablero(), tablero,
                    listaMovimientosPosibles);
        }

        return listaMovimientosPosibles;
    }

    private static List<Casilla> getCasillasOcupadasPorBando(final Tablero tablero, final Bando bando) {

        listaCasillaSTATIC.clear();

        for (int vertical = 0; vertical < Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < Tablero.TALLA_TABLERO; horizontal++) {
                final Casilla casilla = tablero.getCasilla(horizontal, vertical);

                final Ficha ficha = casilla.getFicha();

                if (ficha != null && bando == ficha.getBando()) {
                    listaCasillaSTATIC.add(casilla);
                }
            }
        }

        return listaCasillaSTATIC;
    }
}

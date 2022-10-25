package org.leviatan.chess.engine.movimientosposibles;

import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.movimientosposibles.dto.MovimientosPosiblesSinRestricciones;
import org.leviatan.chess.engine.repositorios.RepositorioDirecciones;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTableroDireccion;

/**
 * GeneradorMovimientosPosiblesTorre.
 *
 * @author Alejandro
 */
public class GeneradorMovimientosPosiblesTorre extends AbstractGeneradorMovimientosPosibles {

    /** Genera los movimientos posibles sin restricciones. */
    @Override
    public void generaMovimientosPosiblesSinRestriccionesYAddToMovimientosPosiblesSinRestricciones(final PosicionTablero posicionTablero,
            final MovimientosPosiblesSinRestricciones movimientosPosiblesSinRestricciones, final Bando bando) {

        final List<Direccion> listaDirecciones = RepositorioDirecciones.getNoDiagonales();

        for (final Direccion direccion : listaDirecciones) {
            final List<PosicionTablero> listaPosicionTableroFinalesSecuenciales = RepositorioPosicionesTableroDireccion
                    .getListaPosicionTableroPosicionesDireccion(posicionTablero.getHorizontal(), posicionTablero.getVertical(), direccion);
            movimientosPosiblesSinRestricciones.addListMovimientoDireccional(posicionTablero, listaPosicionTableroFinalesSecuenciales);
        }
    }

    @Override
    public void generaMovimientosPosiblesYAddToLista(final PosicionTablero posicionTablero, final Tablero tablero,
            final List<Movimiento> listaMovimientos) {
        generaMovimientosPosiblesEnDireccionesDadasYAddToLista(posicionTablero, tablero, RepositorioDirecciones.getNoDiagonales(),
                listaMovimientos);
    }

}

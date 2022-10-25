package org.leviatan.chess.engine.movimientosposibles;

import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.movimientosposibles.dto.MovimientosPosiblesSinRestricciones;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTableroEnL;

/**
 * GeneradorMovimientosPosiblesCaballo.
 *
 * @author Alejandro
 */
public class GeneradorMovimientosPosiblesCaballo extends AbstractGeneradorMovimientosPosibles {

    /** Genera los movimientos posibles sin restricciones. */
    @Override
    public void generaMovimientosPosiblesSinRestriccionesYAddToMovimientosPosiblesSinRestricciones(final PosicionTablero posicionTablero,
            final MovimientosPosiblesSinRestricciones movimientosPosiblesSinRestricciones, final Bando bando) {

        final List<PosicionTablero> listaPosicionTableroFinales = RepositorioPosicionesTableroEnL
                .getListaPosicionTableroPosicionesEnL(posicionTablero.getHorizontal(), posicionTablero.getVertical());
        movimientosPosiblesSinRestricciones.addAllMovimientosFinales(posicionTablero, listaPosicionTableroFinales);
    }

    @Override
    public void generaMovimientosPosiblesYAddToLista(final PosicionTablero posicionTablero, final Tablero tablero,
            final List<Movimiento> listaMovimientos) {

        final List<PosicionTablero> listaPosicionTableroFinales = RepositorioPosicionesTableroEnL
                .getListaPosicionTableroPosicionesEnL(posicionTablero.getHorizontal(), posicionTablero.getVertical());
        generaMovimientosPosiblesConsiderandoQueSePuedeLlegarALosFinalesYQueNoSonFueraYAddToLista(posicionTablero, tablero,
                listaPosicionTableroFinales, listaMovimientos);
    }
}

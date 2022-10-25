package org.leviatan.chess.engine.movimientosposibles;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.engine.movimientosposibles.dto.MovimientosPosiblesSinRestricciones;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTablero;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTableroAlrededor;

/**
 * GeneradorMovimientosPosiblesRey.
 *
 * @author Alejandro
 */
public class GeneradorMovimientosPosiblesRey extends AbstractGeneradorMovimientosPosibles {

    /** Genera los movimientos posibles sin restricciones. */
    @Override
    public void generaMovimientosPosiblesSinRestriccionesYAddToMovimientosPosiblesSinRestricciones(final PosicionTablero posicionTablero,
            final MovimientosPosiblesSinRestricciones movimientosPosiblesSinRestricciones, final Bando bando) {

        final List<PosicionTablero> listaPosicionTableroFinales = RepositorioPosicionesTableroAlrededor
                .getListaPosicionTableroPosicionesAlrededor(posicionTablero.getHorizontal(), posicionTablero.getVertical());
        movimientosPosiblesSinRestricciones.addAllMovimientosFinales(posicionTablero, listaPosicionTableroFinales);
    }

    /** Genera los movimientos posibles del Rey. */
    @Override
    public void generaMovimientosPosiblesYAddToLista(final PosicionTablero posicionTablero, final Tablero tablero,
            final List<Movimiento> listaMovimientos) {

        final List<PosicionTablero> listaPosicionTableroFinales = RepositorioPosicionesTableroAlrededor
                .getListaPosicionTableroPosicionesAlrededor(posicionTablero.getHorizontal(), posicionTablero.getVertical());
        generaMovimientosPosiblesConsiderandoQueSePuedeLlegarALosFinalesYQueNoSonFueraYAddToLista(posicionTablero, tablero,
                listaPosicionTableroFinales, listaMovimientos);

        listaMovimientos.addAll(getMovimientosEnroque(posicionTablero, tablero));
    }

    private List<Movimiento> getMovimientosEnroque(final PosicionTablero posicionTablero, final Tablero tablero) {

        final List<Movimiento> listMovimiento = new ArrayList<Movimiento>();

        final Bando bando = tablero.getFicha(posicionTablero).getBando();

        if (Bando.BLANCO.equals(bando)) {

            if (posicionTablero.getHorizontal() == 4 && posicionTablero.getVertical() == 0) {

                addMovimientosEnroqueSiEsPosible(posicionTablero, tablero, bando, listMovimiento);
            }

        } else {

            if (posicionTablero.getHorizontal() == 4 && posicionTablero.getVertical() == 7) {

                addMovimientosEnroqueSiEsPosible(posicionTablero, tablero, bando, listMovimiento);
            }
        }

        return listMovimiento;
    }

    private void addMovimientosEnroqueSiEsPosible(final PosicionTablero posicionTablero, final Tablero tablero, final Bando bando,
            final List<Movimiento> listMovimiento) {

        if (seDanCondicionesParaEnroqueIzquierda(posicionTablero, tablero, bando)) {

            final PosicionTablero posicionDestino = RepositorioPosicionesTablero.generaNuevaPosicionMovida(posicionTablero.getHorizontal(),
                    posicionTablero.getVertical(), -2, 0);
            listMovimiento.add(new Movimiento(posicionTablero, posicionDestino));
        }

        if (seDanCondicionesParaEnroqueDerecha(posicionTablero, tablero, bando)) {

            final PosicionTablero posicionDestino = RepositorioPosicionesTablero.generaNuevaPosicionMovida(posicionTablero.getHorizontal(),
                    posicionTablero.getVertical(), 2, 0);
            listMovimiento.add(new Movimiento(posicionTablero, posicionDestino));
        }
    }

    private boolean seDanCondicionesParaEnroqueIzquierda(final PosicionTablero posicionTablero, final Tablero tablero, final Bando bando) {

        return esFichaNullEnTodosLosIncrementosDados(tablero, posicionTablero, -1, -2, -3)
                && hayTorreDeBandoDadoEnIncremento(tablero, posicionTablero, -4, bando);
    }

    private boolean seDanCondicionesParaEnroqueDerecha(final PosicionTablero posicionTablero, final Tablero tablero, final Bando bando) {

        return esFichaNullEnTodosLosIncrementosDados(tablero, posicionTablero, 1, 2)
                && hayTorreDeBandoDadoEnIncremento(tablero, posicionTablero, 3, bando);
    }

    private boolean hayTorreDeBandoDadoEnIncremento(final Tablero tablero, final PosicionTablero posicionTablero,
            final int incrementoHorizontal, final Bando bando) {

        final PosicionTablero newPosicionTablero = RepositorioPosicionesTablero.generaNuevaPosicionMovida(posicionTablero.getHorizontal(),
                posicionTablero.getVertical(), incrementoHorizontal, 0);

        final Ficha ficha = tablero.getFicha(newPosicionTablero);

        if (ficha == null) {
            return false;
        }

        return TipoFicha.TORRE.equals(ficha.getTipoFicha()) && bando.equals(ficha.getBando());
    }

    private boolean esFichaNullEnTodosLosIncrementosDados(final Tablero tablero, final PosicionTablero posicionTablero,
            final int... listIncrementoHorizontal) {

        for (final int incrementoHorizontal : listIncrementoHorizontal) {

            final Ficha ficha = getFichaEnPosicionMovida(tablero, posicionTablero, incrementoHorizontal);

            if (ficha != null) {
                return false;
            }
        }

        return true;
    }

    private Ficha getFichaEnPosicionMovida(final Tablero tablero, final PosicionTablero posicionTablero, final int incrementoHorizontal) {

        final PosicionTablero newPosicionTablero = RepositorioPosicionesTablero.generaNuevaPosicionMovida(posicionTablero.getHorizontal(),
                posicionTablero.getVertical(), incrementoHorizontal, 0);

        return tablero.getFicha(newPosicionTablero);
    }
}

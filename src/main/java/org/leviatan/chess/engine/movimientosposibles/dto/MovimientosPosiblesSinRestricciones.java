package org.leviatan.chess.engine.movimientosposibles.dto;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.engine.repositorios.RepositorioMovimientos;

/**
 * MovimientosPosiblesSinRestricciones.
 *
 * @author Alejandro
 */
public class MovimientosPosiblesSinRestricciones {

    // Ponerlos en funcion de la ficha

    private final List<Movimiento> listaMovimientosFinales = new ArrayList<Movimiento>();
    private final List<List<Movimiento>> listaDeListasMovimientosDireccionales = new ArrayList<List<Movimiento>>();
    private final List<MovimientosPeon> listaMovimientosPeon = new ArrayList<MovimientosPeon>();

    public List<List<Movimiento>> getListaDeListasMovimientosDireccionales() {
        return listaDeListasMovimientosDireccionales;
    }

    public List<Movimiento> getListaMovimientosFinales() {
        return listaMovimientosFinales;
    }

    public List<MovimientosPeon> getListaMovimientosPeon() {
        return listaMovimientosPeon;
    }

    /**
     * Añade a la lista un movimiento direccional.
     *
     * @param posicionTableroInicial
     *            posicionTableroInicial
     * @param listaPosicionTableroFinalesSecuenciales
     *            listaPosicionTableroFinalesSecuenciales
     */
    public void addListMovimientoDireccional(final PosicionTablero posicionTableroInicial,
            final List<PosicionTablero> listaPosicionTableroFinalesSecuenciales) {
        final List<Movimiento> listaMovimientosDireccionales = new ArrayList<Movimiento>();

        for (final PosicionTablero posicionTableroFinal : listaPosicionTableroFinalesSecuenciales) {
            final Movimiento movimiento = RepositorioMovimientos.getMovimiento(posicionTableroInicial, posicionTableroFinal);
            listaMovimientosDireccionales.add(movimiento);
        }

        listaDeListasMovimientosDireccionales.add(listaMovimientosDireccionales);
    }

    /**
     * Añade a la lista un movimiento final.
     *
     * @param posicionTableroInicial
     *            posicionTableroInicial
     * @param listaPosicionTableroFinales
     *            listaPosicionTableroFinales
     */
    public void addAllMovimientosFinales(final PosicionTablero posicionTableroInicial,
            final List<PosicionTablero> listaPosicionTableroFinales) {

        for (final PosicionTablero posicionTableroFinal : listaPosicionTableroFinales) {
            final Movimiento movimiento = RepositorioMovimientos.getMovimiento(posicionTableroInicial, posicionTableroFinal);
            listaMovimientosFinales.add(movimiento);
        }
    }

    /**
     * Añade a la lista un movimiento de peon.
     *
     * @param posicionTableroInicial
     *            posicionTableroInicial
     * @param posicionesPeonFinales
     *            posicionesPeonFinales
     */
    public void addMovimientosPeon(final PosicionTablero posicionTableroInicial, final PosicionesPeon posicionesPeonFinales) {
        final MovimientosPeon movimientosPeon = new MovimientosPeon(posicionTableroInicial, posicionesPeonFinales);
        listaMovimientosPeon.add(movimientosPeon);
    }
}

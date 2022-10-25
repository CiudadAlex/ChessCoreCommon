package org.leviatan.chess.engine.intel.generadorarbol;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;
import org.leviatan.chess.engine.intel.dto.TableroModificado;

/**
 * EvaluadorDeMovimientosA1Jugada.
 *
 * @author Alejandro
 */
public final class EvaluadorDeMovimientosA1Jugada {

    private EvaluadorDeMovimientosA1Jugada() {
    }

    /**
     * Obtiene los MovimientoYConsecuencias posibles del bando dado.
     *
     * @param tableroModificado
     *            tableroModificado
     * @param bando
     *            bando
     * @return los MovimientoYConsecuencias posibles del bando dado
     */
    public static List<MovimientoYConsecuencias> getMovimientoYConsecuenciasPosiblesPorBando(final TableroModificado tableroModificado,
            final Bando bando) {

        final Tablero tablero = tableroModificado.devuelveTableroFinalDeCalculos();
        final Tablero tableroInicial = tableroModificado.getTableroInicial();
        final List<Movimiento> listaMovimientosPrevios = tableroModificado.getListaMovimientos();

        final List<MovimientoYConsecuencias> listaMovimientosYConsecuencias = new ArrayList<MovimientoYConsecuencias>(
                Configuracion.TALLA_INICIAL_ARRAYLIST_MOVIMIENTOS);

        final List<Movimiento> listaMovimientosPosiblesBando = HelperMovimientosPosibles.getMovimientosPosiblesDeBando(tablero, bando);

        for (final Movimiento movimiento : listaMovimientosPosiblesBando) {
            final MovimientoYConsecuencias movimientoYConsecuencias = construyeMovimientoYConsecuenciasAPartirDelMovimiento(
                    listaMovimientosPrevios, movimiento, tablero, tableroInicial, bando);
            listaMovimientosYConsecuencias.add(movimientoYConsecuencias);
        }

        return listaMovimientosYConsecuencias;
    }

    /** Construye un MovimientoCPU a partir del Movimiento. */
    private static MovimientoYConsecuencias construyeMovimientoYConsecuenciasAPartirDelMovimiento(
            final List<Movimiento> listaMovimientosPrevios, final Movimiento movimiento, final Tablero tablero,
            final Tablero tableroInicial, final Bando bando) {

        final float ganancia = calculaLaGanancia(movimiento, tablero);

        final boolean protegido = HelperMovimientosProtegidos.estaProtegidosElMovimientoConPosicionesFinalesProtegidasDeBando(movimiento,
                tablero, bando);

        final MovimientoYConsecuencias movimientoYConsecuencias = new MovimientoYConsecuencias(movimiento, listaMovimientosPrevios,
                tableroInicial, ganancia, protegido, bando);

        return movimientoYConsecuencias;
    }

    /** Calcula la ganancia del movimiento. */
    private static float calculaLaGanancia(final Movimiento movimiento, final Tablero tablero) {

        float ganancia = 0;

        final PosicionTablero posicionDestino = movimiento.getPosicionDestino();
        final Ficha ficha = tablero.getFicha(posicionDestino);

        if (ficha != null) {
            ganancia = ficha.getTipoFicha().getValor();
        }

        return ganancia;
    }
}

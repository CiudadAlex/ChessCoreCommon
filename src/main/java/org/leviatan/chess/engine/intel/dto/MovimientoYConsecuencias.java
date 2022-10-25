package org.leviatan.chess.engine.intel.dto;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * MovimientoYConsecuencias.
 *
 * @author Alejandro
 */
public class MovimientoYConsecuencias extends ProfiledObject {

    /** Movimiento a realizar. */
    private final Movimiento movimiento;

    /** Lista de los movimientos previos. */
    private final List<Movimiento> listaMovimientosPrevios = new ArrayList<Movimiento>();

    /** Tablero antes de todos los movimientos calculados. */
    private final Tablero tableroInicial;

    /** Ganancia de puntos. */
    private final float ganancia;

    /** Indica si el movimiento está protegido. */
    private final boolean protegido;

    /** Bando con el que se corresponde este movimiento. */
    private final Bando bandoDelMovimiento;

    /** Ganancia acumulada en el arbol de decision. */
    private float gananciaAcumulada;

    /**
     * Constructor de MovimientoYConsecuencias.
     *
     * @param movimiento
     *            movimiento
     * @param listaMovimientosPrevios
     *            listaMovimientosPrevios
     * @param tableroInicial
     *            tableroInicial
     * @param ganancia
     *            ganancia
     * @param protegido
     *            protegido
     * @param bandoDelMovimiento
     *            bandoDelMovimiento
     */
    public MovimientoYConsecuencias(final Movimiento movimiento, final List<Movimiento> listaMovimientosPrevios,
            final Tablero tableroInicial, final float ganancia, final boolean protegido, final Bando bandoDelMovimiento) {
        this.movimiento = movimiento;
        this.listaMovimientosPrevios.addAll(listaMovimientosPrevios);
        this.tableroInicial = tableroInicial;
        this.ganancia = ganancia;
        this.protegido = protegido;
        this.bandoDelMovimiento = bandoDelMovimiento;

        gananciaAcumulada = getGananciaMejorada();
    }

    /**
     * Devuelve la ganancia de mejora.
     *
     * @return la ganancia de mejora
     */
    public final float getGananciaMejorada() {

        float gananciaMejorada = ganancia;

        if (protegido) {
            gananciaMejorada = gananciaMejorada + Configuracion.VALOR_PROTECCION;
        }

        return gananciaMejorada;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    /**
     * Obtiene la lista total de movimientos.
     *
     * @return la lista total de movimientos
     */
    private List<Movimiento> obtenerListaMovimientosTotal() {

        final List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        listaMovimientos.addAll(listaMovimientosPrevios);
        listaMovimientos.add(movimiento);

        return listaMovimientos;
    }

    /**
     * Obtener el tablero de despues.
     *
     * @return el tablero de despues
     */
    public TableroModificado getTableroDespues() {

        final TableroModificado tableroModificado = new TableroModificado(tableroInicial, obtenerListaMovimientosTotal());
        return tableroModificado;
    }

    public Bando getBandoDelMovimiento() {
        return bandoDelMovimiento;
    }

    public float getGananciaAcumulada() {
        return gananciaAcumulada;
    }

    /**
     * Suma a la ganancia acumulada.
     *
     * @param ganancia
     *            ganancia
     */
    public void sumaAGananciaAcumulada(final float ganancia) {
        gananciaAcumulada += ganancia;
    }

    /**
     * Multiplica a la ganancia acumulada.
     *
     * @param factor
     *            factor
     */
    public void multiplicaGananciaAcumuladaPorFactor(final float factor) {
        gananciaAcumulada *= factor;
    }
}

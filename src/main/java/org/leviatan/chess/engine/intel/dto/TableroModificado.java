package org.leviatan.chess.engine.intel.dto;

import java.util.List;

import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * Representa una versión de baja memoria de un tablero modificado con respecto
 * a uno inicial por una lista de movimientos.
 *
 * @author Alejandro
 */
public class TableroModificado extends ProfiledObject {

    /** Tablero para efectuar calculos. */
    private static Tablero tableroDeCalculos = new Tablero();

    private final Tablero tableroInicial;
    private final List<Movimiento> listaMovimientos;

    /**
     * Constructor para TableroModificado.
     *
     * @param tableroInicial
     *            tableroInicial
     * @param listaMovimientos
     *            listaMovimientos
     */
    public TableroModificado(final Tablero tableroInicial, final List<Movimiento> listaMovimientos) {
        this.tableroInicial = tableroInicial;
        this.listaMovimientos = listaMovimientos;
    }

    /**
     * Constructor para TableroModificado.
     *
     * @param tableroInicial
     *            tableroInicial
     */
    public TableroModificado(final Tablero tableroInicial) {
        this(tableroInicial, null);
    }

    public Tablero getTableroInicial() {
        return tableroInicial;
    }

    /**
     * Devuelve el tablero final en el de calculos.
     *
     * @return el tablero final en el de calculos
     */
    public Tablero devuelveTableroFinalDeCalculos() {
        copiaTableroFinal(tableroDeCalculos, listaMovimientos);
        return tableroDeCalculos;
    }

    public List<Movimiento> getListaMovimientos() {
        return listaMovimientos;
    }

    /**
     * Situa el tablero de calculos en la misma posicion que se desea con los
     * movimientos.
     *
     * @param tableroDeCalculos
     *            tableroDeCalculos
     * @param listaMovimientos
     *            listaMovimientos
     */
    private void copiaTableroFinal(final Tablero tableroDeCalculos, final List<Movimiento> listaMovimientos) {

        igualaElTableroModificableAlNoModificable(tableroInicial, tableroDeCalculos);

        if (listaMovimientos != null) {
            for (final Movimiento movimientoItem : listaMovimientos) {
                tableroDeCalculos.realizarMovimiento(movimientoItem);
            }
        }
    }

    /**
     * Iguala el tablero modificable al no modificable.
     *
     * @param tableroNOMod
     *            tablero NO Modificable
     * @param tableroModificable
     *            tableroModificable
     */
    public static void igualaElTableroModificableAlNoModificable(final Tablero tableroNOMod, final Tablero tableroModificable) {

        for (int vertical = 0; vertical < Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < Tablero.TALLA_TABLERO; horizontal++) {

                final Ficha ficha = tableroNOMod.getCasilla(horizontal, vertical).getFicha();
                tableroModificable.getCasilla(horizontal, vertical).setFicha(ficha);
            }
        }
    }
}

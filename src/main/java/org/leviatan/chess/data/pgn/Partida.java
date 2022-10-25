package org.leviatan.chess.data.pgn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.leviatan.chess.board.Movimiento;

/**
 * Partida.
 *
 * @author Alejandro
 *
 */
public class Partida {

    private final List<Movimiento> listMovimiento = new ArrayList<Movimiento>();

    /**
     * Añade movimiento.
     *
     * @param movimiento
     *            movimiento
     */
    public void addMovimiento(final Movimiento movimiento) {
        this.listMovimiento.add(movimiento);
    }

    public Iterator<Movimiento> getIteratorMovimiento() {
        return this.listMovimiento.iterator();
    }

    public List<Movimiento> getListMovimiento() {
        return this.listMovimiento;
    }

}

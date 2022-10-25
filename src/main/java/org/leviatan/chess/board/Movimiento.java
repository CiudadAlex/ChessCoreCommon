package org.leviatan.chess.board;

import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * Movimiento.
 *
 * @author Alejandro
 *
 */
public class Movimiento extends ProfiledObject {

    private final PosicionTablero posicionOrigen;
    private final PosicionTablero posicionDestino;

    /**
     * Constructor para Movimiento.
     *
     * @param posicionOrigen
     *            posicionOrigen
     * @param posicionDestino
     *            posicionDestino
     */
    public Movimiento(final PosicionTablero posicionOrigen, final PosicionTablero posicionDestino) {
        this.posicionOrigen = posicionOrigen;
        this.posicionDestino = posicionDestino;
    }

    public PosicionTablero getPosicionDestino() {
        return this.posicionDestino;
    }

    public PosicionTablero getPosicionOrigen() {
        return this.posicionOrigen;
    }

    /**
     * Devuelve si es el movimiento root.
     *
     * @return si es el movimiento root
     */
    public boolean esMovimientoRoot() {
        return this.posicionOrigen.esCeroCero() && this.posicionDestino.esCeroCero();
    }

    @Override
    public String toString() {
        return "posicionOrigen" + this.posicionOrigen + " / posicionDestino" + this.posicionDestino;
    }

    @Override
    public boolean equals(final Object object) {

        if (object instanceof Movimiento) {
            final Movimiento mov = (Movimiento) object;

            if (this.posicionOrigen.equals(mov.posicionOrigen) && this.posicionDestino.equals(mov.posicionDestino)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 100 * this.posicionOrigen.hashCode() + this.posicionDestino.hashCode();
    }
}

package org.leviatan.chess.board;

import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * Casilla.
 *
 * @author Alejandro
 */
public class Casilla extends ProfiledObject {

    /** Ficha que contiene la casilla. Si no tiene ficha es null */
    private Ficha ficha;

    /** Posición en el tablero de la casilla. */
    private final PosicionTablero posicionTablero;

    /** Tipo de casilla. */
    private final TipoCasilla tipoCasilla;

    /**
     * Constructor para Casilla.
     *
     * @param ficha
     *            ficha
     * @param posicionTablero
     *            posicionTablero
     * @param tipoCasilla
     *            tipoCasilla
     */
    public Casilla(final Ficha ficha, final PosicionTablero posicionTablero, final TipoCasilla tipoCasilla) {
        this.ficha = ficha;
        this.posicionTablero = posicionTablero;
        this.tipoCasilla = tipoCasilla;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public PosicionTablero getPosicionTablero() {
        return posicionTablero;
    }

    public TipoCasilla getTipoCasilla() {
        return tipoCasilla;
    }

    public void setFicha(final Ficha ficha) {
        this.ficha = ficha;
    }
}

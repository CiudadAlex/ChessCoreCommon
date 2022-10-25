package org.leviatan.chess.engine.movimientosposibles.dto;

import java.util.Arrays;
import java.util.List;

import org.leviatan.chess.board.PosicionTablero;

/**
 * PosicionesPeon.
 *
 * @author Alejandro
 */
public class PosicionesPeon {

    private final PosicionTablero posicionTableroDiagonalIzquierda;
    private final PosicionTablero posicionTableroCentro;
    private final PosicionTablero posicionTableroCentroDoble;
    private final PosicionTablero posicionTableroDiagonalDerecha;

    /**
     * Constructor para PosicionesPeon.
     *
     * @param posicionTableroDiagonalIzquierda
     *            posicionTableroDiagonalIzquierda
     * @param posicionTableroCentro
     *            posicionTableroCentro
     * @param posicionTableroCentroDoble
     *            posicionTableroCentroDoble
     * @param posicionTableroDiagonalDerecha
     *            posicionTableroDiagonalDerecha
     */
    public PosicionesPeon(final PosicionTablero posicionTableroDiagonalIzquierda, final PosicionTablero posicionTableroCentro,
            final PosicionTablero posicionTableroCentroDoble, final PosicionTablero posicionTableroDiagonalDerecha) {
        this.posicionTableroDiagonalIzquierda = posicionTableroDiagonalIzquierda;
        this.posicionTableroCentro = posicionTableroCentro;
        this.posicionTableroCentroDoble = posicionTableroCentroDoble;
        this.posicionTableroDiagonalDerecha = posicionTableroDiagonalDerecha;
    }

    public PosicionTablero getPosicionTableroCentro() {
        return this.posicionTableroCentro;
    }

    public PosicionTablero getPosicionTableroCentroDoble() {
        return this.posicionTableroCentroDoble;
    }

    public PosicionTablero getPosicionTableroDiagonalDerecha() {
        return this.posicionTableroDiagonalDerecha;
    }

    public PosicionTablero getPosicionTableroDiagonalIzquierda() {
        return this.posicionTableroDiagonalIzquierda;
    }

    public List<PosicionTablero> getListPosicionTablero() {
        return Arrays.asList(this.posicionTableroCentro, this.posicionTableroCentroDoble, this.posicionTableroDiagonalDerecha,
                this.posicionTableroDiagonalIzquierda);
    }
}

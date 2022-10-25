package org.leviatan.chess.board;

/**
 * TipoCasilla.
 *
 * @author Alejandro
 */
public enum TipoCasilla {

    /** BLANCA. */
    BLANCA,

    /** NEGRA. */
    NEGRA;

    /**
     * Obtiene el tipo blanco o negro segun el boolean.
     *
     * @param blanca
     *            blanca
     * @return el tipo blanco o negro segun el boolean
     */
    public static TipoCasilla get(final boolean blanca) {

        if (blanca) {
            return BLANCA;
        }

        return NEGRA;
    }
}

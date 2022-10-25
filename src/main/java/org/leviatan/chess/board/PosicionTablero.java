package org.leviatan.chess.board;

import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * PosicionTablero.
 *
 * @author Alejandro
 *
 */
public class PosicionTablero extends ProfiledObject {

    private final int horizontal;
    private final int vertical;

    /**
     * Constructor for PosicionTablero.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     */
    public PosicionTablero(final int horizontal, final int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return this.horizontal;
    }

    public int getVertical() {
        return this.vertical;
    }

    @Override
    public boolean equals(final Object object) {

        if (object instanceof PosicionTablero) {

            final PosicionTablero posT = (PosicionTablero) object;

            if (this.horizontal == posT.horizontal && this.vertical == posT.vertical) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 10 * this.horizontal + this.vertical;
    }

    @Override
    public String toString() {
        return "(horizontal=" + this.horizontal + ", vertical=" + this.vertical + ")";
    }

    /**
     * To string corto.
     *
     * @return To string corto
     */
    public String toStringCorto() {
        return "" + getLetraHorizontal() + (this.vertical + 1);
    }

    private String getLetraHorizontal() {

        int ch = 'A';
        ch = ch + this.horizontal;

        final Character character = new Character((char) ch);
        return character.toString();
    }

    /**
     * Devuelve si es la posicion (0,0).
     *
     * @return si es la posicion (0,0)
     */
    public boolean esCeroCero() {
        return this.horizontal == 0 && this.vertical == 0;
    }

}

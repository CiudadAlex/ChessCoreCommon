package org.leviatan.chess.engine.movimientosposibles;

import java.util.ArrayList;
import java.util.List;

/**
 * Direccion.
 *
 * @author Alejandro
 */
public enum Direccion {

    /** VERTICAL_ARRIBA. */
    VERTICAL_ARRIBA(0, 1, 0, false),

    /** VERTICAL_ABAJO. */
    VERTICAL_ABAJO(0, -1, 1, false),

    /** HORIZONTAL_IZQUIERDA. */
    HORIZONTAL_IZQUIERDA(-1, 0, 2, false),

    /** HORIZONTAL_DERECHA. */
    HORIZONTAL_DERECHA(1, 0, 3, false),

    /** DIAGONAL_ARRIBA_IZQUIERDA. */
    DIAGONAL_ARRIBA_IZQUIERDA(-1, 1, 4, true),

    /** DIAGONAL_ARRIBA_DERECHA. */
    DIAGONAL_ARRIBA_DERECHA(1, 1, 5, true),

    /** DIAGONAL_ABAJO_IZQUIERDA. */
    DIAGONAL_ABAJO_IZQUIERDA(-1, -1, 6, true),

    /** DIAGONAL_ABAJO_DERECHA. */
    DIAGONAL_ABAJO_DERECHA(1, -1, 7, true);

    private int incrementoHorizontal;
    private int incrementoVertical;
    private int idDireccion;
    private boolean diagonalONo;

    private Direccion(final int incrementoHorizontal, final int incrementoVertical, final int idDireccion, final boolean diagonalONo) {
        this.incrementoHorizontal = incrementoHorizontal;
        this.incrementoVertical = incrementoVertical;
        this.idDireccion = idDireccion;
        this.diagonalONo = diagonalONo;
    }

    public int getIncrementoHorizontal() {
        return this.incrementoHorizontal;
    }

    public int getIncrementoVertical() {
        return this.incrementoVertical;
    }

    public int getIdDireccion() {
        return this.idDireccion;
    }

    /**
     * Devuelve un subconjunto de direcciones.
     *
     * @param diagonalONo
     *            diagonalONo
     * @return un subconjunto de direcciones
     */
    public static List<Direccion> getSubconjuntoDirecciones(final boolean diagonalONo) {

        final List<Direccion> listDireccion = new ArrayList<Direccion>();

        for (final Direccion direccion : values()) {

            if (direccion.diagonalONo == diagonalONo) {
                listDireccion.add(direccion);
            }
        }

        return listDireccion;
    }

    /**
     * Devuelve las direcciones.
     *
     * @return las direcciones
     */
    public static List<Direccion> getDirecciones() {

        final List<Direccion> listDireccion = new ArrayList<Direccion>();

        for (final Direccion direccion : values()) {
            listDireccion.add(direccion);
        }

        return listDireccion;
    }
}

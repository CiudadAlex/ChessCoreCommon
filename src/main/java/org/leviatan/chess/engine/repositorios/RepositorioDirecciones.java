package org.leviatan.chess.engine.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.engine.movimientosposibles.Direccion;

/**
 * RepositorioDirecciones.
 *
 * @author Alejandro
 */
public final class RepositorioDirecciones {

    private static List<Direccion> listaDiagonales = generaDiagonales();
    private static List<Direccion> listaNoDiagonales = generaNoDiagonales();
    private static List<Direccion> listaTodas = generaTodas();

    private RepositorioDirecciones() {
    }

    /** Genera las direcciones diagonales. */
    private static List<Direccion> generaDiagonales() {

        final List<Direccion> listaDireccion = new ArrayList<Direccion>();

        listaDireccion.add(Direccion.DIAGONAL_ARRIBA_IZQUIERDA);
        listaDireccion.add(Direccion.DIAGONAL_ARRIBA_DERECHA);
        listaDireccion.add(Direccion.DIAGONAL_ABAJO_IZQUIERDA);
        listaDireccion.add(Direccion.DIAGONAL_ABAJO_DERECHA);

        return listaDireccion;
    }

    /** Genera las direcciones NO diagonales. */
    private static List<Direccion> generaNoDiagonales() {

        final List<Direccion> listaDireccion = new ArrayList<Direccion>();

        listaDireccion.add(Direccion.VERTICAL_ARRIBA);
        listaDireccion.add(Direccion.VERTICAL_ABAJO);
        listaDireccion.add(Direccion.HORIZONTAL_IZQUIERDA);
        listaDireccion.add(Direccion.HORIZONTAL_DERECHA);

        return listaDireccion;
    }

    /** Genera todas las direcciones. */
    private static List<Direccion> generaTodas() {

        final List<Direccion> listaDireccion = new ArrayList<Direccion>();

        listaDireccion.addAll(generaNoDiagonales());
        listaDireccion.addAll(generaDiagonales());

        return listaDireccion;
    }

    /**
     * Devuelve las direcciones diagonales.
     *
     * @return las direcciones diagonales
     */
    public static List<Direccion> getDiagonales() {
        return listaDiagonales;
    }

    /**
     * Devuelve las direcciones NO diagonales.
     *
     * @return las direcciones NO diagonales
     */
    public static List<Direccion> getNoDiagonales() {
        return listaNoDiagonales;
    }

    /**
     * Devuelve todas las direcciones.
     *
     * @return todas las direcciones
     */
    public static List<Direccion> getTodas() {
        return listaTodas;
    }
}

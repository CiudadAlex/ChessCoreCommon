package org.leviatan.chess.engine.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.movimientosposibles.Direccion;
import org.leviatan.chess.tools.profiler.Cronometro;

/**
 * RepositorioPosicionesTableroDireccion.
 *
 * @author Alejandro
 */
public final class RepositorioPosicionesTableroDireccion {

    /** Matriz de las posiciones de alrededor. */
    private static List<PosicionTablero>[][][] listaPosicionTableroPosicionesDireccionMatrix = generaListaPosicionTableroPosicionesDireccionMatrix();

    private RepositorioPosicionesTableroDireccion() {
    }

    /**
     * Devuelve la lista de posiciones de la direccion dada.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @param direccion
     *            direccion
     * @return la lista de posiciones de la direccion dada
     */
    public static List<PosicionTablero> getListaPosicionTableroPosicionesDireccion(final int horizontal, final int vertical,
            final Direccion direccion) {
        return listaPosicionTableroPosicionesDireccionMatrix[horizontal][vertical][direccion.getIdDireccion()];
    }

    /** Generador de la matriz. */
    private static List<PosicionTablero>[][][] generaListaPosicionTableroPosicionesDireccionMatrix() {

        final Cronometro cronometro = new Cronometro(true);
        cronometro.tic("Cargando RepositorioPosicionesDireccion...");

        @SuppressWarnings("unchecked")
        final List<PosicionTablero>[][][] listaPosicionTableroPosicionesDireccionMatrixTemp = new ArrayList[Tablero.TALLA_TABLERO][Tablero.TALLA_TABLERO][Direccion
                .values().length];

        for (int vertical = 0; vertical < Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < Tablero.TALLA_TABLERO; horizontal++) {
                for (final Direccion direccion : Direccion.values()) {
                    listaPosicionTableroPosicionesDireccionMatrixTemp[horizontal][vertical][direccion
                            .getIdDireccion()] = generaPosicionesDeDireccion(horizontal, vertical, direccion);
                }
            }
        }

        cronometro.toc("RepositorioPosicionesDireccion");

        return listaPosicionTableroPosicionesDireccionMatrixTemp;
    }

    /** Genera las posiciones en la posición dada. */
    private static List<PosicionTablero> generaPosicionesDeDireccion(final int horizontal, final int vertical, final Direccion direccion) {

        final List<PosicionTablero> listaPosicionTablero = new ArrayList<PosicionTablero>();

        PosicionTablero posicionTableroIndex = RepositorioPosicionesTablero.getPosicionTablero(horizontal, vertical);

        final int incrementoHorizontal = direccion.getIncrementoHorizontal();
        final int incrementoVertical = direccion.getIncrementoVertical();

        for (int i = 0; i < Tablero.TALLA_TABLERO; i++) {
            posicionTableroIndex = UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero,
                    posicionTableroIndex.getHorizontal(), posicionTableroIndex.getVertical(), incrementoHorizontal, incrementoVertical);
        }

        return listaPosicionTablero;
    }
}

package org.leviatan.chess.engine.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.tools.profiler.Cronometro;

/**
 * RepositorioPosicionesTableroEnL.
 *
 * @author Alejandro
 */
public final class RepositorioPosicionesTableroEnL {

    /** Matriz de las posiciones de en L. */
    private static List<PosicionTablero>[][] listaPosicionTableroPosicionesEnLMatrix = generaListaPosicionTableroPosicionesEnLMatrix();

    private RepositorioPosicionesTableroEnL() {
    }

    /**
     * Devuelve la lista de posiciones en L.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @return la lista de posiciones en L
     */
    public static List<PosicionTablero> getListaPosicionTableroPosicionesEnL(final int horizontal, final int vertical) {
        return listaPosicionTableroPosicionesEnLMatrix[horizontal][vertical];
    }

    /** Generador de la matriz. */
    private static List<PosicionTablero>[][] generaListaPosicionTableroPosicionesEnLMatrix() {

        final Cronometro cronometro = new Cronometro(true);
        cronometro.tic("Cargando RepositorioPosicionesEnL...");

        @SuppressWarnings("unchecked")
        final List<PosicionTablero>[][] listaPosicionTableroPosicionesEnLMatrixTemp = new ArrayList[Tablero.TALLA_TABLERO][Tablero.TALLA_TABLERO];

        for (int vertical = 0; vertical < Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < Tablero.TALLA_TABLERO; horizontal++) {
                listaPosicionTableroPosicionesEnLMatrixTemp[horizontal][vertical] = generaPosicionesEnL(horizontal, vertical);
            }
        }

        cronometro.toc("RepositorioPosicionesEnL");

        return listaPosicionTableroPosicionesEnLMatrixTemp;
    }

    /** Genera las posiciones de alrededor. */
    private static List<PosicionTablero> generaPosicionesEnL(final int horizontal, final int vertical) {

        final List<PosicionTablero> listaPosicionTablero = new ArrayList<PosicionTablero>();

        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, -1, 2);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 1, 2);

        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, -2, 1);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 2, 1);

        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, -2,
                -1);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 2, -1);

        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, -1,
                -2);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 1, -2);

        return listaPosicionTablero;
    }
}

package org.leviatan.chess.engine.repositorios;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.tools.profiler.Cronometro;

/**
 * RepositorioPosicionesTableroAlrededor.
 *
 * @author Alejandro
 */
public final class RepositorioPosicionesTableroAlrededor {

    /** Matriz de las posiciones de alrededor. */
    private static List<PosicionTablero>[][] listaPosicionTableroPosicionesAlrededorMatrix = generaListaPosicionTableroPosicionesAlrededorMatrix();

    private RepositorioPosicionesTableroAlrededor() {
    }

    /**
     * Devuelve la lista de posiciones de alrededor.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @return la lista de posiciones de alrededor
     */
    public static List<PosicionTablero> getListaPosicionTableroPosicionesAlrededor(final int horizontal, final int vertical) {
        return listaPosicionTableroPosicionesAlrededorMatrix[horizontal][vertical];
    }

    /** Generador de la matriz. */
    private static List<PosicionTablero>[][] generaListaPosicionTableroPosicionesAlrededorMatrix() {

        final Cronometro cronometro = new Cronometro(true);
        cronometro.tic("Cargando RepositorioPosicionesAlrededor...");

        @SuppressWarnings("unchecked")
        final List<PosicionTablero>[][] listaPosicionTableroPosicionesAlrededorMatrixTemp = new ArrayList[Tablero.TALLA_TABLERO][Tablero.TALLA_TABLERO];

        for (int vertical = 0; vertical < Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < Tablero.TALLA_TABLERO; horizontal++) {
                listaPosicionTableroPosicionesAlrededorMatrixTemp[horizontal][vertical] = generaPosicionesAlrededor(horizontal, vertical);
            }
        }

        cronometro.toc("RepositorioPosicionesAlrededor");

        return listaPosicionTableroPosicionesAlrededorMatrixTemp;
    }

    /** Genera las posiciones de alrededor. */
    private static List<PosicionTablero> generaPosicionesAlrededor(final int horizontal, final int vertical) {

        final List<PosicionTablero> listaPosicionTablero = new ArrayList<PosicionTablero>();

        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, -1, 1);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 0, 1);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 1, 1);

        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, -1, 0);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 1, 0);

        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, -1,
                -1);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 0, -1);
        UtilRepositorios.addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(listaPosicionTablero, horizontal, vertical, 1, -1);

        return listaPosicionTablero;
    }
}

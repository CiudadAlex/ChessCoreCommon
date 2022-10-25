package org.leviatan.chess.engine.repositorios;

import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.tools.profiler.Cronometro;

/**
 * RepositorioPosicionesTablero.
 *
 * @author Alejandro
 */
public final class RepositorioPosicionesTablero {

    private RepositorioPosicionesTablero() {
    }

    private static PosicionTablero[][] posicionTableroMatrixDesplazadas = creaPosicionTableroMatrixDesplazadas();

    private static PosicionTablero[][] creaPosicionTableroMatrixDesplazadas() {

        final Cronometro cronometro = new Cronometro(true);
        cronometro.tic("Cargando RepositorioPosicionesTablero...");

        final PosicionTablero[][] posicionTableroMatrixDesplazadasTemp = new PosicionTablero[3 * Tablero.TALLA_TABLERO][3
                * Tablero.TALLA_TABLERO];

        for (int vertical = 0; vertical < 3 * Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < 3 * Tablero.TALLA_TABLERO; horizontal++) {
                posicionTableroMatrixDesplazadasTemp[horizontal][vertical] = new PosicionTablero(horizontal - Tablero.TALLA_TABLERO,
                        vertical - Tablero.TALLA_TABLERO);
            }
        }

        cronometro.toc("RepositorioPosicionesTablero");

        return posicionTableroMatrixDesplazadasTemp;
    }

    /**
     * Devuelve la posicion Tablero dada.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @return la posicion Tablero dada
     */
    public static PosicionTablero getPosicionTablero(final int horizontal, final int vertical) {
        return posicionTableroMatrixDesplazadas[horizontal + Tablero.TALLA_TABLERO][vertical + Tablero.TALLA_TABLERO];
    }

    /**
     * Genera una nueva posición desplazada.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @param incrementoHorizontal
     *            incrementoHorizontal
     * @param incrementoVertical
     *            incrementoVertical
     * @return una nueva posición desplazada
     */
    public static PosicionTablero generaNuevaPosicionMovida(final int horizontal, final int vertical, final int incrementoHorizontal,
            final int incrementoVertical) {

        final int nuevaHorizontal = horizontal + incrementoHorizontal;
        final int nuevaVertical = vertical + incrementoVertical;

        return getPosicionTablero(nuevaHorizontal, nuevaVertical);
    }
}

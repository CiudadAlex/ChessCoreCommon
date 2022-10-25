package org.leviatan.chess.engine.repositorios;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.movimientosposibles.dto.PosicionesPeon;
import org.leviatan.chess.tools.profiler.Cronometro;

/**
 * RepositorioPosicionesTableroPeon.
 *
 * @author Alejandro
 */
public final class RepositorioPosicionesTableroPeon {

    /** Matriz de las posiciones del peon. */
    private static PosicionesPeon[][][] listaPosicionTableroPosicionesPeonMatrix = generaListaPosicionTableroPosicionesPeonMatrix();

    private RepositorioPosicionesTableroPeon() {
    }

    /**
     * Devuelve la lista de posiciones del peon.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @param bando
     *            bando
     * @return la lista de posiciones del peon
     */
    public static PosicionesPeon getListaPosicionTableroPosicionesPeon(final int horizontal, final int vertical, final Bando bando) {
        return listaPosicionTableroPosicionesPeonMatrix[horizontal][vertical][bando.getIdentificador()];
    }

    /** Generador de la matriz. */
    private static PosicionesPeon[][][] generaListaPosicionTableroPosicionesPeonMatrix() {

        final Cronometro cronometro = new Cronometro(true);
        cronometro.tic("Cargando RepositorioPosicionesPeon...");

        final PosicionesPeon[][][] listaPosicionTableroPosicionesPeonMatrixTemp = new PosicionesPeon[Tablero.TALLA_TABLERO][Tablero.TALLA_TABLERO][Bando
                .values().length];

        for (int vertical = 0; vertical < Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < Tablero.TALLA_TABLERO; horizontal++) {
                for (final Bando bando : Bando.values()) {
                    listaPosicionTableroPosicionesPeonMatrixTemp[horizontal][vertical][bando.getIdentificador()] = generaPosicionesPeon(
                            horizontal, vertical, bando);
                }
            }
        }

        cronometro.toc("RepositorioPosicionesPeon");

        return listaPosicionTableroPosicionesPeonMatrixTemp;
    }

    /** Genera la posicion del peon. */
    private static PosicionesPeon generaPosicionesPeon(final int horizontal, final int vertical, final Bando bando) {

        int direccionVertical = 1;
        int posicionInicioVerticalPeon = 1;

        if (bando == Bando.NEGRO) {
            direccionVertical = -1;
            posicionInicioVerticalPeon = Tablero.TALLA_TABLERO - 2;
        }

        final PosicionTablero posicionTableroDiagonalIzquierda = devuelveOFiltraPosicionSiEstaFueraDeTablero(
                RepositorioPosicionesTablero.generaNuevaPosicionMovida(horizontal, vertical, -1, direccionVertical));
        final PosicionTablero posicionTableroCentro = devuelveOFiltraPosicionSiEstaFueraDeTablero(
                RepositorioPosicionesTablero.generaNuevaPosicionMovida(horizontal, vertical, 0, direccionVertical));
        PosicionTablero posicionTableroCentroDoble = null;
        final PosicionTablero posicionTableroDiagonalDerecha = devuelveOFiltraPosicionSiEstaFueraDeTablero(
                RepositorioPosicionesTablero.generaNuevaPosicionMovida(horizontal, vertical, 1, direccionVertical));

        if (vertical == posicionInicioVerticalPeon) {
            posicionTableroCentroDoble = devuelveOFiltraPosicionSiEstaFueraDeTablero(
                    RepositorioPosicionesTablero.generaNuevaPosicionMovida(horizontal, vertical, 0, direccionVertical * 2));
        }

        return new PosicionesPeon(posicionTableroDiagonalIzquierda, posicionTableroCentro, posicionTableroCentroDoble,
                posicionTableroDiagonalDerecha);
    }

    /**
     * Devuelve la posicion dada si esta dentro del tablero. Si esta fuera
     * devuelve null
     */
    private static PosicionTablero devuelveOFiltraPosicionSiEstaFueraDeTablero(final PosicionTablero posicionTablero) {

        final boolean estaPosicionFueraDelTablero = UtilRepositorios.estaPosicionFueraDelTablero(posicionTablero);

        if (estaPosicionFueraDelTablero) {
            return null;
        }

        return posicionTablero;
    }
}

package org.leviatan.chess.board;

import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTablero;
import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * MatrizTablero.
 *
 * @author Alejandro
 */
public class MatrizTablero extends ProfiledObject {

    /* Tablero, si la posicion es null es que la casilla está vacia */
    private final Casilla[][] matriz;

    /* Tamaño de la matriz del tablero */
    private final int tallaTablero;

    /**
     * Constructor para MatrizTablero.
     *
     * @param tallaTableroArg
     *            tallaTableroArg
     */
    public MatrizTablero(final int tallaTableroArg) {
        tallaTablero = tallaTableroArg;
        matriz = new Casilla[tallaTablero][tallaTablero];

        creaCasillas();
    }

    /**
     * Coge la casilla de la matriz.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @return Casilla
     */
    public Casilla getFromMatrix(final int horizontal, final int vertical) {
        return matriz[horizontal][vertical];
    }

    private void creaCasillas() {

        for (int vertical = 0; vertical < tallaTablero; vertical++) {
            for (int horizontal = 0; horizontal < tallaTablero; horizontal++) {
                crearCasillaEnMatrix(horizontal, vertical);
            }
        }
    }

    private void crearCasillaEnMatrix(final int horizontal, final int vertical) {

        final boolean horizontalImpar = horizontal % 2 != 0;
        final boolean verticalImpar = vertical % 2 != 0;

        final boolean blanco = horizontalImpar && !verticalImpar || !horizontalImpar && verticalImpar;

        final PosicionTablero posicionTablero = RepositorioPosicionesTablero.getPosicionTablero(horizontal, vertical);
        final TipoCasilla tipoCasilla = TipoCasilla.get(blanco);
        final Casilla casilla = new Casilla(null, posicionTablero, tipoCasilla);

        setToMatrix(casilla, horizontal, vertical);
    }

    private void setToMatrix(final Casilla casilla, final int horizontal, final int vertical) {
        matriz[horizontal][vertical] = casilla;
    }
}

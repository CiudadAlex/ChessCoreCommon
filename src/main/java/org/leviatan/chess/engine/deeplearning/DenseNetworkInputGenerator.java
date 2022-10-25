package org.leviatan.chess.engine.deeplearning;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTablero;

/**
 * DenseNetworkInputGenerator.
 *
 * @author Alejandro
 *
 */
public class DenseNetworkInputGenerator implements NetworkInputGenerator {

    /** NUM_INPUTS_TABLERO. */
    public static final int NUM_INPUTS_TABLERO = (TipoFicha.values().length + Bando.values().length + 1) * Tablero.TALLA_TABLERO
            * Tablero.TALLA_TABLERO;

    @Override
    public int getInputSize() {
        return NUM_INPUTS_TABLERO;
    }

    @Override
    public double[] getInput(final Tablero tablero) {

        final double[] data = new double[NUM_INPUTS_TABLERO];
        int index = 0;

        for (int v = 0; v < Tablero.TALLA_TABLERO; v++) {

            for (int h = 0; h < Tablero.TALLA_TABLERO; h++) {

                final Ficha ficha = tablero.getFicha(RepositorioPosicionesTablero.getPosicionTablero(h, v));
                final TipoFicha tipoFicha = ficha != null ? ficha.getTipoFicha() : null;
                final Bando bandoFicha = ficha != null ? ficha.getBando() : null;

                for (final TipoFicha tipoFichaItem : TipoFicha.values()) {

                    if (tipoFichaItem.equals(tipoFicha)) {
                        data[index] = 1;
                    }

                    index++;
                }

                for (final Bando bandoItem : Bando.values()) {

                    if (bandoItem.equals(bandoFicha)) {
                        data[index] = 1;
                    }

                    index++;
                }

                if (ficha == null) {
                    data[index] = 1;
                }

                index++;
            }
        }

        return data;
    }

}

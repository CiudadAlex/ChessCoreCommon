package org.leviatan.chess.engine.deeplearning;

import org.leviatan.chess.board.Tablero;

/**
 * NetworkInputGenerator.
 *
 * @author Alejandro
 *
 */
public interface NetworkInputGenerator {

    /**
     * The input size.
     *
     * @return input size
     */
    public int getInputSize();

    /**
     * Devuelve el input de un tablero.
     *
     * @param tablero
     *            tablero
     * @return el input de un tablero
     */
    public double[] getInput(final Tablero tablero);
}

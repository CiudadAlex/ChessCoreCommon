package org.leviatan.chess.engine;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.ui.UserIntefaceInteractor;

/**
 * CPUPlayer.
 *
 * @author Alejandro
 *
 */
public interface CPUPlayer {

    /**
     * Realiza la jugada de la CPU.
     *
     * @param tablero
     *            tablero
     * @param userIntefaceInteractor
     *            userIntefaceInteractor
     * @param bandoCPU
     *            bandoCPU
     * @return tablero con movimiento realizado
     */
    public Tablero realizarJugadaCPU(final Tablero tablero, final UserIntefaceInteractor userIntefaceInteractor, Bando bandoCPU);
}

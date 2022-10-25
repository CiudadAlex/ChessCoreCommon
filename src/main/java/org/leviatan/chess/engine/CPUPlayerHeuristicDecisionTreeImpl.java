package org.leviatan.chess.engine;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.intel.Inteligencia;
import org.leviatan.chess.ui.UserIntefaceInteractor;

/**
 * CPUPlayerHeuristicDecisionTreeImpl.
 *
 * @author Alejandro
 *
 */
public class CPUPlayerHeuristicDecisionTreeImpl implements CPUPlayer {

    @Override
    public Tablero realizarJugadaCPU(final Tablero tablero, final UserIntefaceInteractor userIntefaceInteractor, final Bando bandoCPU) {

        if (Bando.NEGRO.equals(bandoCPU)) {
            return Inteligencia.realizarJugadaCPU(tablero, userIntefaceInteractor);

        } else {
            return Inteligencia.realizarJugadaCPUConBlancas(tablero, userIntefaceInteractor);
        }
    }

}

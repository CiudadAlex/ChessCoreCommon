package org.leviatan.chess.ui;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.Tablero;

/**
 * ChessCore.
 *
 * @author Alejandro
 */
public interface ChessCore {

    /**
     * Jugada de la CPU.
     *
     * @param tablero
     *            tablero
     * @param mostradorDeMensajes
     *            mostradorDeMensajes
     */
    public void realizarJugadaCPU(Tablero tablero, UserIntefaceInteractor mostradorDeMensajes);

    /**
     * Devuelve si el movimiento es correcto.
     *
     * @param movimiento
     *            movimiento
     * @param tableroInicial
     *            tableroInicial
     * @param userIntefaceInteractor
     *            userIntefaceInteractor
     * @return si el movimiento es correcto
     */
    public boolean esElMovimientoJugadorCorrecto(Movimiento movimiento, Tablero tableroInicial,
            UserIntefaceInteractor userIntefaceInteractor);

    /**
     * Calcula puntuación del bando.
     *
     * @param tablero
     *            tablero
     * @param bando
     *            bando
     * @return puntuación del bando
     */
    public int calculaPuntuacionBando(Tablero tablero, Bando bando);
}

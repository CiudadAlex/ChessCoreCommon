package org.leviatan.chess.engine.repositorios;

import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.movimientosposibles.Direccion;
import org.leviatan.chess.engine.movimientosposibles.EstadoCasilla;

/**
 * UtilRepositorios.
 *
 * @author Alejandro
 */
public final class UtilRepositorios {

    private static int POSICION_PRIMERA_CASILLA_VALIDA;
    private static int POSICION_ULTIMA_CASILLA_VALIDA = Tablero.TALLA_TABLERO - 1;

    private UtilRepositorios() {
    }

    /** Añade la nueva posicion a la lista. */
    protected static PosicionTablero addPosicionAListaSiNoEstaFueraDelTableroYDevuelvePosicionMovida(
            final List<PosicionTablero> listaPosicionTablero, final int horizontal, final int vertical, final int incrementoHorizontal,
            final int incrementoVertical) {

        final PosicionTablero posicionTableroMovida = RepositorioPosicionesTablero.generaNuevaPosicionMovida(horizontal, vertical,
                incrementoHorizontal, incrementoVertical);
        final boolean estaPosicionFueraDelTablero = estaPosicionFueraDelTablero(posicionTableroMovida);

        if (!estaPosicionFueraDelTablero) {
            listaPosicionTablero.add(posicionTableroMovida);
        }

        return posicionTableroMovida;
    }

    /**
     * Añade la nueva posicion a la lista teniendo en cuenta el estado de la
     * casilla dada.
     */
    protected static PosicionTablero addPosicionAListaSiNoEstaFueraDelTableroYEncajaConEstadoCasillaDadaYDevuelvePosicionMovida(
            final List<PosicionTablero> listaPosicionTablero, final int horizontal, final int vertical, final int incrementoHorizontal,
            final int incrementoVertical, final EstadoCasilla estadoCasillaFinal) {

        final PosicionTablero posicionTableroMovida = RepositorioPosicionesTablero.generaNuevaPosicionMovida(horizontal, vertical,
                incrementoHorizontal, incrementoVertical);
        final boolean estaPosicionFueraDelTablero = estaPosicionFueraDelTablero(posicionTableroMovida);

        if (!estaPosicionFueraDelTablero && estadoCasillaFinal != EstadoCasilla.OCUPADA_FICHA_ALIADA) {
            listaPosicionTablero.add(posicionTableroMovida);
        }

        return posicionTableroMovida;
    }

    /**
     * Devuelve si la posicion esta fuera del tablero.
     *
     * @param posicionTablero
     *            posicionTablero
     * @return si la posicion esta fuera del tablero
     */
    public static boolean estaPosicionFueraDelTablero(final PosicionTablero posicionTablero) {

        final int horizontal = posicionTablero.getHorizontal();
        final int vertical = posicionTablero.getVertical();

        return estaPosicionFueraDeRango(horizontal) || estaPosicionFueraDeRango(vertical);
    }

    /** Devuelve si la posicion esta fuera del rango. */
    private static boolean estaPosicionFueraDeRango(final int posicion) {
        return posicion < POSICION_PRIMERA_CASILLA_VALIDA || posicion > POSICION_ULTIMA_CASILLA_VALIDA;
    }

    /** Precarga los repositorios. */
    public static void precargarRepositorios() {

        RepositorioPosicionesTablero.getPosicionTablero(0, 0);
        RepositorioPosicionesTableroAlrededor.getListaPosicionTableroPosicionesAlrededor(0, 0);
        RepositorioPosicionesTableroEnL.getListaPosicionTableroPosicionesEnL(0, 0);
        RepositorioPosicionesTableroDireccion.getListaPosicionTableroPosicionesDireccion(0, 0, Direccion.VERTICAL_ARRIBA);
        RepositorioPosicionesTableroPeon.getListaPosicionTableroPosicionesPeon(0, 0, Bando.BLANCO);

        final PosicionTablero posicionTablero = RepositorioPosicionesTablero.getPosicionTablero(0, 0);
        RepositorioMovimientos.getMovimiento(posicionTablero, posicionTablero);

        RepositorioDirecciones.getTodas();
    }
}

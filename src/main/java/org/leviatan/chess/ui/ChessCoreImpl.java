package org.leviatan.chess.ui;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.Inteligencia;
import org.leviatan.chess.engine.movimientosposibles.AbstractGeneradorMovimientosPosibles;

/**
 * ChessCoreImpl.
 *
 * @author Alejandro
 */
public class ChessCoreImpl implements ChessCore {

    /** Jugada de la CPU. */
    @Override
    public void realizarJugadaCPU(final Tablero tablero, final UserIntefaceInteractor mostradorDeMensajes) {

        Inteligencia.realizarJugadaCPU(tablero, mostradorDeMensajes);
    }

    /** Devuelve si el movimiento del jugador es correcto. */
    @Override
    public boolean esElMovimientoJugadorCorrecto(final Movimiento movimiento, final Tablero tableroInicial,
            final UserIntefaceInteractor userIntefaceInteractor) {

        final PosicionTablero posicionOrigen = movimiento.getPosicionOrigen();

        final Ficha ficha = tableroInicial.getFicha(posicionOrigen);

        if (ficha != null && Bando.BLANCO == ficha.getBando()) {

            final TipoFicha tipoFicha = ficha.getTipoFicha();
            final AbstractGeneradorMovimientosPosibles generadorMovimientosPosibles = tipoFicha.getGeneradorMovimientosPosibles();

            final PosicionTablero posicionDestino = movimiento.getPosicionDestino();
            final List<Movimiento> listaMovimientosPosibles = new ArrayList<Movimiento>();
            generadorMovimientosPosibles.generaMovimientosPosiblesYAddToLista(posicionOrigen, tableroInicial, listaMovimientosPosibles);

            final boolean estaLaPosicionDestinoEntreLosMovimientosPosibles = estaLaPosicionDestinoEntreLosMovimientosPosibles(
                    posicionDestino, listaMovimientosPosibles);

            if (estaLaPosicionDestinoEntreLosMovimientosPosibles) {

                final boolean conEseMovimientoLaCPUPuedeMatarAlReyHumano = Inteligencia
                        .conEseMovimientoSePuedeMatarAlReyDelBandoContrarioAlDado(tableroInicial, movimiento, Configuracion.BANDO_CPU);

                if (conEseMovimientoLaCPUPuedeMatarAlReyHumano) {
                    userIntefaceInteractor.mostrarMensaje("Con ese movimiento te comeré el rey...");

                } else {
                    return true;
                }
            }
        }

        return false;
    }

    /** Esta la posicionDestino entre los movimientos posibles. */
    private static boolean estaLaPosicionDestinoEntreLosMovimientosPosibles(final PosicionTablero posicionDestino,
            final List<Movimiento> listaMovimientosPosibles) {

        for (final Movimiento movimiento : listaMovimientosPosibles) {

            final PosicionTablero posicionDestinoItem = movimiento.getPosicionDestino();

            if (posicionDestino.equals(posicionDestinoItem)) {
                return true;
            }
        }

        return false;
    }

    /** Calcula puntuación del bando. */
    @Override
    public int calculaPuntuacionBando(final Tablero tablero, final Bando bando) {

        final float valorTodasLasFichas = getValorTodasLasFichas();
        final float valorFichasRival = getValorFichasDeBando(tablero, Bando.getBandoOpuesto(bando));

        return (int) (valorTodasLasFichas - valorFichasRival);
    }

    /**
     * Devuelve la puntiación máxima.
     *
     * @return la puntiación máxima
     */
    public float getValorTodasLasFichas() {

        return 1 * TipoFicha.REY.getValor() + 1 * TipoFicha.REINA.getValor() + 2 * TipoFicha.TORRE.getValor()
                + 2 * TipoFicha.ALFIL.getValor() + 2 * TipoFicha.CABALLO.getValor() + 8 * TipoFicha.PEON.getValor();
    }

    /** Devuelve el valor de las fichas del bando. */
    private static float getValorFichasDeBando(final Tablero tablero, final Bando bando) {

        float valorFichasBando = 0;

        for (int vertical = 0; vertical < Tablero.TALLA_TABLERO; vertical++) {
            for (int horizontal = 0; horizontal < Tablero.TALLA_TABLERO; horizontal++) {

                final Ficha ficha = tablero.getCasilla(horizontal, vertical).getFicha();

                if (ficha != null && bando.equals(ficha.getBando())) {
                    valorFichasBando = valorFichasBando + ficha.getTipoFicha().getValor();
                }
            }
        }

        return valorFichasBando;
    }
}

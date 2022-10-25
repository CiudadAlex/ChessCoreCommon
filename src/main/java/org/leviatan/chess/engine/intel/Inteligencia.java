package org.leviatan.chess.engine.intel;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;
import org.leviatan.chess.engine.intel.dto.TableroModificado;
import org.leviatan.chess.engine.intel.generadorarbol.EvaluadorDeMovimientosA1Jugada;
import org.leviatan.chess.tools.log.Logger;
import org.leviatan.chess.tools.profiler.Cronometro;
import org.leviatan.chess.ui.UserIntefaceInteractor;

/**
 * Inteligencia.
 *
 * @author Alejandro
 */
public final class Inteligencia {

    private Inteligencia() {
    }

    /**
     * Jugada de la CPU con las blancas.
     *
     * @param tableroInicial
     *            tableroInicial
     * @param userIntefaceInteractor
     *            userIntefaceInteractor
     * @return Tablero
     */
    public static Tablero realizarJugadaCPUConBlancas(final Tablero tableroInicial, final UserIntefaceInteractor userIntefaceInteractor) {

        final Tablero tableroEspecular = tableroInicial.creaTableroEspecular();
        final Tablero tableroJugadoCPU = realizarJugadaCPU(tableroEspecular, userIntefaceInteractor);
        return tableroJugadoCPU.creaTableroEspecular();
    }

    /**
     * Jugada de la CPU.
     *
     * @param tableroInicial
     *            tableroInicial
     * @param userIntefaceInteractor
     *            userIntefaceInteractor
     * @return Tablero
     */
    public static Tablero realizarJugadaCPU(final Tablero tableroInicial, final UserIntefaceInteractor userIntefaceInteractor) {

        userIntefaceInteractor.inicioCalculos();

        final MovimientoYConsecuencias movimientoYConsecuencias = decideMovimientoARealizar(tableroInicial, userIntefaceInteractor,
                Configuracion.PROFUNDIDAD_ARBOL_DE_MOVIMIENTOS);

        if (movimientoYConsecuencias == null) {
            userIntefaceInteractor.mostrarMensajeEmpate();
            userIntefaceInteractor.finCalculos("Tablas");
            return tableroInicial;
        }

        final float gananciaAcumulada = movimientoYConsecuencias.getGananciaAcumulada();
        final Movimiento movimiento = movimientoYConsecuencias.getMovimiento();
        final String strMovimiento = generaMensajeJugada(movimiento, tableroInicial);

        if (gananciaAcumulada < -TipoFicha.REY.getValor() / 2) {
            userIntefaceInteractor.mostrarMensajeVictoriaHumano();

        } else if (gananciaAcumulada > TipoFicha.REY.getValor() / 2) {
            userIntefaceInteractor.mostrarMensajeVictoriaCPU();
            tableroInicial.realizarMovimiento(movimiento);

        } else {
            tableroInicial.realizarMovimiento(movimiento);
        }

        userIntefaceInteractor.finCalculos(strMovimiento);

        return tableroInicial;
    }

    /** Genera el mensaje de la jugada. */
    private static String generaMensajeJugada(final Movimiento movimiento, final Tablero tableroInicial) {

        final PosicionTablero posicionTableroOrigen = movimiento.getPosicionOrigen();
        final PosicionTablero posicionTableroDestino = movimiento.getPosicionDestino();

        final Ficha fichaMovida = tableroInicial.getFicha(posicionTableroOrigen);

        final String strMovimiento = fichaMovida.getTipoFicha() + "\n" + posicionTableroOrigen.toStringCorto().toLowerCase() + " "
                + posicionTableroDestino.toStringCorto().toLowerCase();
        Logger.log(strMovimiento);

        return strMovimiento;
    }

    /**
     * Decide la jugada a realizar.
     *
     * @param tableroInicial
     *            tableroInicial
     * @param numeroDeParesDeJugadasACalcular
     *            numeroDeParesDeJugadasACalcular
     * @return MovimientoYConsecuencias
     */
    public static MovimientoYConsecuencias decideMovimientoARealizar(final Tablero tableroInicial,
            final int numeroDeParesDeJugadasACalcular) {

        final UserIntefaceInteractor userIntefaceInteractor = UserIntefaceInteractor.getOnlyLogInstance();
        return decideMovimientoARealizar(tableroInicial, userIntefaceInteractor, numeroDeParesDeJugadasACalcular);
    }

    /** Decide la jugada a realizar. */
    private static MovimientoYConsecuencias decideMovimientoARealizar(final Tablero tableroInicial,
            final UserIntefaceInteractor userIntefaceInteractor, final int numeroDeParesDeJugadasACalcular) {

        final Cronometro cronometro = new Cronometro(false);
        cronometro.tic("Pensando...");

        final List<MovimientoYConsecuencias> listaMovimientoYConsecuenciasPosibles = GeneradorArbolDeJugadas
                .generarYDevolverMovimientosPosibles(tableroInicial, userIntefaceInteractor, numeroDeParesDeJugadasACalcular);
        Logger.log(listaMovimientoYConsecuenciasPosibles);

        final List<MovimientoYConsecuencias> listaMovimientoYConsecuenciasMejores = DecisorMejoresJugadasARealizarPorGanancia
                .decideMejoresJugadas(listaMovimientoYConsecuenciasPosibles);
        final MovimientoYConsecuencias movimientoYConsecuencias = DecisorUltimoDeMovimiento
                .decisionUltimaDelMovimiento(listaMovimientoYConsecuenciasMejores, tableroInicial);

        cronometro.toc("La decision");

        return movimientoYConsecuencias;
    }

    /**
     * Devuelve si con ese movimiento la CPU puede matar al rey humano.
     *
     * @param tableroInicial
     *            tableroInicial
     * @param movimiento
     *            movimiento
     * @param bando
     *            bando
     * @return si con ese movimiento la CPU puede matar al rey humano
     */
    public static boolean conEseMovimientoSePuedeMatarAlReyDelBandoContrarioAlDado(final Tablero tableroInicial,
            final Movimiento movimiento, final Bando bando) {

        final List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        listaMovimientos.add(movimiento);

        final TableroModificado tableroModificado = new TableroModificado(tableroInicial, listaMovimientos);

        final List<MovimientoYConsecuencias> listaMovimientosYConsecuenciasPosibles = EvaluadorDeMovimientosA1Jugada
                .getMovimientoYConsecuenciasPosiblesPorBando(tableroModificado, bando);
        final float gananciaMejoradaMaxima = obtenerGananciaMejoradaMaxima(listaMovimientosYConsecuenciasPosibles);

        if (gananciaMejoradaMaxima > TipoFicha.REY.getValor() / 2) {
            return true;
        }

        return false;
    }

    /** Calcula las ganancias de la Jugada. */
    private static float obtenerGananciaMejoradaMaxima(final List<MovimientoYConsecuencias> listaMovimientosYConsecuencias) {

        // Ganancia mejorada solo puede ser positiva
        float gananciaMejoradaMaxima = 0;

        for (final MovimientoYConsecuencias movimientoYConsecuencias : listaMovimientosYConsecuencias) {

            final float gananciaMejoradaItem = movimientoYConsecuencias.getGananciaMejorada();
            gananciaMejoradaMaxima = Math.max(gananciaMejoradaMaxima, gananciaMejoradaItem);
        }

        return gananciaMejoradaMaxima;
    }
}

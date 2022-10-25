package org.leviatan.chess.engine.intel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;
import org.leviatan.chess.engine.intel.dto.TableroModificado;
import org.leviatan.chess.engine.intel.generadorarbol.HelperMovimientosPosibles;

/**
 * DecisorUltimoDeMovimiento.
 *
 * @author Alejandro
 */
public final class DecisorUltimoDeMovimiento {

    private DecisorUltimoDeMovimiento() {
    }

    /**
     * Decision ultima con la lista mas firme de movimientos.
     *
     * @param listaMovimientoYConsecuenciasMejoresArg
     *            listaMovimientoYConsecuenciasMejoresArg
     * @param tableroInicial
     *            tableroInicial
     * @return Decision ultima con la lista mas firme de movimientos
     */
    public static MovimientoYConsecuencias decisionUltimaDelMovimiento(
            final List<MovimientoYConsecuencias> listaMovimientoYConsecuenciasMejoresArg, final Tablero tableroInicial) {

        List<MovimientoYConsecuencias> listaMovimientoYConsecuenciasMejores = listaMovimientoYConsecuenciasMejoresArg;

        if (listaMovimientoYConsecuenciasMejores.isEmpty()) {
            // Si no puede mover se devuelve null
            return null;
        }

        final List<MovimientoYConsecuencias> listaQueNoMuevenAlRey = elijeMovimientosQueNoMuevanAlRey(listaMovimientoYConsecuenciasMejores,
                tableroInicial);

        if (!listaQueNoMuevenAlRey.isEmpty()) {
            // Si se puede evitar mover al rey nos quedamos con esas
            listaMovimientoYConsecuenciasMejores = listaQueNoMuevenAlRey;
        }

        listaMovimientoYConsecuenciasMejores = elijeMovimientosDeMaximaMovilidad(listaMovimientoYConsecuenciasMejores, tableroInicial);

        final MovimientoYConsecuencias movimientoYConsecuencias = eligeMovimientoAleatoriamente(listaMovimientoYConsecuenciasMejores);
        return movimientoYConsecuencias;
    }

    /** Elige los movimientos que aumantan la movilidad. */
    private static List<MovimientoYConsecuencias> elijeMovimientosDeMaximaMovilidad(
            final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias, final Tablero tableroInicial) {

        int numeroDeMovimientosPosiblesMaximo = -1;
        final List<MovimientoYConsecuencias> listaMaximaMovilidad = new ArrayList<MovimientoYConsecuencias>();

        for (final MovimientoYConsecuencias movimientoYConsecuencias : listaMovimientoYConsecuencias) {
            final Movimiento movimiento = movimientoYConsecuencias.getMovimiento();
            final int numeroDeMovimientosPosiblesIteracion = calculaMovimientosPosiblesDespuesDeMovimiento(movimiento, tableroInicial);

            if (numeroDeMovimientosPosiblesIteracion > numeroDeMovimientosPosiblesMaximo) {
                listaMaximaMovilidad.clear();
                listaMaximaMovilidad.add(movimientoYConsecuencias);

                numeroDeMovimientosPosiblesMaximo = numeroDeMovimientosPosiblesIteracion;

            } else if (numeroDeMovimientosPosiblesIteracion == numeroDeMovimientosPosiblesMaximo) {
                listaMaximaMovilidad.add(movimientoYConsecuencias);
            }
        }

        return listaMaximaMovilidad;
    }

    /** Calcula el numero de movimientos posibles despues del movimiento. */
    private static int calculaMovimientosPosiblesDespuesDeMovimiento(final Movimiento movimiento, final Tablero tableroInicial) {

        final List<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
        listaMovimientos.add(movimiento);
        final TableroModificado tableroModificado = new TableroModificado(tableroInicial, listaMovimientos);
        final Tablero tablero = tableroModificado.devuelveTableroFinalDeCalculos();
        final Bando bando = Configuracion.BANDO_CPU;

        final List<Movimiento> listaMovimientosPosibles = HelperMovimientosPosibles.getMovimientosPosiblesDeBando(tablero, bando);

        return listaMovimientosPosibles.size();
    }

    /** Devuelve los movimientos que no mueven al rey. */
    private static List<MovimientoYConsecuencias> elijeMovimientosQueNoMuevanAlRey(
            final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias, final Tablero tableroInicial) {

        final List<MovimientoYConsecuencias> listaQueNoMuevenAlRey = new ArrayList<MovimientoYConsecuencias>();

        for (final MovimientoYConsecuencias movimientoYConsecuencias : listaMovimientoYConsecuencias) {
            final PosicionTablero posicionTableroInicio = movimientoYConsecuencias.getMovimiento().getPosicionOrigen();
            final TipoFicha tipoFicha = tableroInicial.getFicha(posicionTableroInicio).getTipoFicha();

            if (!tipoFicha.equals(TipoFicha.REY)) {
                listaQueNoMuevenAlRey.add(movimientoYConsecuencias);
            }
        }

        return listaQueNoMuevenAlRey;
    }

    /** Elige aleatoriamente un movimiento. */
    private static MovimientoYConsecuencias eligeMovimientoAleatoriamente(
            final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias) {

        final Random random = new Random();
        final int indiceMovimientoAleatorio = Math.abs(random.nextInt()) % listaMovimientoYConsecuencias.size();
        return listaMovimientoYConsecuencias.get(indiceMovimientoAleatorio);
    }
}

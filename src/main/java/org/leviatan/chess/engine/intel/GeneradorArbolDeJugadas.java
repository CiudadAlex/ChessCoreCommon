package org.leviatan.chess.engine.intel;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;
import org.leviatan.chess.engine.intel.dto.TableroModificado;
import org.leviatan.chess.engine.intel.generadorarbol.EvaluadorDeMovimientosA1Jugada;
import org.leviatan.chess.engine.repositorios.RepositorioMovimientos;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTablero;
import org.leviatan.chess.tools.profiler.ProgresoArbol;
import org.leviatan.chess.ui.UserIntefaceInteractor;

/**
 * GeneradorArbolDeJugadas.
 *
 * @author Alejandro
 */
public final class GeneradorArbolDeJugadas {

    private GeneradorArbolDeJugadas() {
    }

    /**
     * Genera el arbol de jugadas.
     *
     * @param tableroInicial
     *            tableroInicial
     * @param numeroDeParesDeJugadasACalcular
     *            numeroDeParesDeJugadasACalcular
     * @return el arbol de jugadas
     */
    public static List<MovimientoYConsecuencias> generarYDevolverMovimientosPosibles(final Tablero tableroInicial,
            final int numeroDeParesDeJugadasACalcular) {

        final UserIntefaceInteractor userIntefaceInteractor = UserIntefaceInteractor.getOnlyLogInstance();
        return generarYDevolverMovimientosPosibles(tableroInicial, userIntefaceInteractor, numeroDeParesDeJugadasACalcular);
    }

    /**
     * Genera el arbol de jugadas.
     *
     * @param tableroInicial
     *            tableroInicial
     * @param userIntefaceInteractor
     *            userIntefaceInteractor
     * @param numeroDeParesDeJugadasACalcular
     *            numeroDeParesDeJugadasACalcular
     * @return el arbol de jugadas
     */
    public static List<MovimientoYConsecuencias> generarYDevolverMovimientosPosibles(final Tablero tableroInicial,
            final UserIntefaceInteractor userIntefaceInteractor, final int numeroDeParesDeJugadasACalcular) {

        final int profundidad = 0;
        final MovimientoYConsecuencias movimientoYConsecuenciasRoot = generaMovimientoYConsecuenciasRoot(tableroInicial);
        final ProgresoArbol progresoArbol = new ProgresoArbol(userIntefaceInteractor);

        final List<MovimientoYConsecuencias> listaMovimientoYConsecuenciasPosibles = generaReplicasYCalculaGananciaAcumulada(
                movimientoYConsecuenciasRoot, profundidad, progresoArbol, numeroDeParesDeJugadasACalcular);

        return listaMovimientoYConsecuenciasPosibles;
    }

    /**
     * Genera las replicas y calcula la ganancia acumulada del nodo y la
     * establece en el propio nodo. La profundidad indica el numero de jugada en
     * el que está el movimientoYConsecuenciasActual
     */
    private static List<MovimientoYConsecuencias> generaReplicasYCalculaGananciaAcumulada(
            final MovimientoYConsecuencias movimientoYConsecuenciasActual, final int profundidad, final ProgresoArbol progresoArbol,
            final int numeroDeParesDeJugadasACalcular) {

        if (profundidad >= 2 * numeroDeParesDeJugadasACalcular) {

            // En el ultimo nivel de profundidad no hay que calcular el
            // siguiente nivel. Solo calculamos el propio nodo
            CalculadorGananciaAcumulada.calculaGananciaDeNodoYEstableceEnNodo(movimientoYConsecuenciasActual,
                    new ArrayList<MovimientoYConsecuencias>());

            return null;
        }

        final TableroModificado tableroModificado = movimientoYConsecuenciasActual.getTableroDespues();
        final Bando bandoQueVaAMover = Bando.getBandoOpuesto(movimientoYConsecuenciasActual.getBandoDelMovimiento());

        final List<MovimientoYConsecuencias> listaReplicas = EvaluadorDeMovimientosA1Jugada
                .getMovimientoYConsecuenciasPosiblesPorBando(tableroModificado, bandoQueVaAMover);

        final int profundidadReplicas = profundidad + 1;
        progresoArbol.inicioNivel(profundidadReplicas, listaReplicas.size());

        for (final MovimientoYConsecuencias movimientoYConsecuenciasReplicaItem : listaReplicas) {
            generaReplicasYCalculaGananciaAcumulada(movimientoYConsecuenciasReplicaItem, profundidadReplicas, progresoArbol,
                    numeroDeParesDeJugadasACalcular);
        }

        // Debug
        // boolean coincide =
        // InspectorArbol.printArbolParaDebug(tableroModificado,
        // movimientoYConsecuenciasActual, listaReplicas);

        CalculadorGananciaAcumulada.calculaGananciaDeNodoYEstableceEnNodo(movimientoYConsecuenciasActual, listaReplicas);
        progresoArbol.progresoEnNivel(profundidad);

        return listaReplicas;
    }

    /** Genera el Movimiento Root. */
    private static MovimientoYConsecuencias generaMovimientoYConsecuenciasRoot(final Tablero tableroInicial) {

        final PosicionTablero posicionOrigen = RepositorioPosicionesTablero.getPosicionTablero(0, 0);

        // Sin movimiento
        final Movimiento movimiento = RepositorioMovimientos.getMovimiento(posicionOrigen, posicionOrigen);
        final float ganancia = 0;
        final boolean protegido = false;
        final Bando bandoHumano = Bando.getBandoOpuesto(Configuracion.BANDO_CPU);

        return new MovimientoYConsecuencias(movimiento, new ArrayList<Movimiento>(), tableroInicial, ganancia, protegido, bandoHumano);
    }
}

package org.leviatan.chess.engine.intel;

import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;

/**
 * CalculadorGananciaAcumulada.
 *
 * @author Alejandro
 */
public final class CalculadorGananciaAcumulada {

    private static final float FACTOR_CORRECTOR_JUGADA_CPU = 1;
    private static final float FACTOR_CORRECTOR_JUGADA_HUMANO = -1;

    private CalculadorGananciaAcumulada() {
    }

    /**
     * Calcula la de un nodo del arbol. El factor corrector permite ponderar si
     * son de un bando o del otro las ganancias.
     *
     * @param movimientoYConsecuencias
     *            movimientoYConsecuencias
     * @param listaReplicas
     *            listaReplicas
     */
    public static void calculaGananciaDeNodoYEstableceEnNodo(final MovimientoYConsecuencias movimientoYConsecuencias,
            final List<MovimientoYConsecuencias> listaReplicas) {

        final Bando bandoMovimiento = movimientoYConsecuencias.getBandoDelMovimiento();
        final boolean jugadaCPUConReplicasHumanas = Configuracion.BANDO_CPU.equals(bandoMovimiento);

        if (jugadaCPUConReplicasHumanas) {
            calculaNodoCPUConReplicasHumanas(movimientoYConsecuencias, listaReplicas);

        } else {
            calculaNodoHumanoConReplicasCPU(movimientoYConsecuencias, listaReplicas);
        }
    }

    private static void calculaNodoHumanoConReplicasCPU(final MovimientoYConsecuencias movimientoYConsecuencias,
            final List<MovimientoYConsecuencias> listaReplicas) {

        // El nodo actual es de Humano y las replicas son de la CPU
        final float gananciaMaximaReplicas = obtenerGananciaAcumuladaMaxima(listaReplicas);

        // La ganancia mejorada se multiplica por el factor corrector de ser
        // humano en el turno actual
        movimientoYConsecuencias.multiplicaGananciaAcumuladaPorFactor(FACTOR_CORRECTOR_JUGADA_HUMANO);

        // Sumamos la ganancia acumulada maxima de las replicas ya que la CPU
        // eligirá la del maximo valor
        movimientoYConsecuencias.sumaAGananciaAcumulada(gananciaMaximaReplicas);
    }

    private static void calculaNodoCPUConReplicasHumanas(final MovimientoYConsecuencias movimientoYConsecuencias,
            final List<MovimientoYConsecuencias> listaReplicas) {

        // El nodo actual es de CPU y las replicas son de la humano
        final float gananciaMinimaReplicas = obtenerGananciaAcumuladaMinima(listaReplicas);

        // La ganancia mejorada se multiplica por el factor corrector de ser CPU
        // en el turno actual
        movimientoYConsecuencias.multiplicaGananciaAcumuladaPorFactor(FACTOR_CORRECTOR_JUGADA_CPU);

        // Sumamos la ganancia acumulada minima de las replicas ya que el Humano
        // eligirá la del minimo valor
        movimientoYConsecuencias.sumaAGananciaAcumulada(gananciaMinimaReplicas);
    }

    /**
     * Calcula las ganancias acumulada maxima de la Jugada.
     *
     * @param listaMovimientoYConsecuencias
     *            listaMovimientoYConsecuencias
     * @return las ganancias acumulada maxima de la Jugada
     */
    public static float obtenerGananciaAcumuladaMaxima(final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias) {

        if (listaMovimientoYConsecuencias == null || listaMovimientoYConsecuencias.isEmpty()) {
            // Si no hay replicas (ultimo escalon calculado no hay ganancia)
            return 0;
        }

        // La ganancia acumulada puede ser negativa
        float gananciaAcumuladaMaxima = Float.NEGATIVE_INFINITY;

        for (final MovimientoYConsecuencias movimientoYConsecuencias : listaMovimientoYConsecuencias) {

            final float gananciaAcumuladaMaximaItem = movimientoYConsecuencias.getGananciaAcumulada();
            gananciaAcumuladaMaxima = Math.max(gananciaAcumuladaMaxima, gananciaAcumuladaMaximaItem);
        }

        return gananciaAcumuladaMaxima;
    }

    /**
     * Calcula las ganancias acumulada minima de la Jugada.
     *
     * @param listaMovimientoYConsecuencias
     *            listaMovimientoYConsecuencias
     * @return las ganancias acumulada minima de la Jugada
     */
    public static float obtenerGananciaAcumuladaMinima(final List<MovimientoYConsecuencias> listaMovimientoYConsecuencias) {

        if (listaMovimientoYConsecuencias == null || listaMovimientoYConsecuencias.isEmpty()) {
            // Si no hay replicas (ultimo escalon calculado no hay ganancia)
            return 0;
        }

        // La ganancia acumulada puede ser negativa
        float gananciaAcumuladaMinima = Float.POSITIVE_INFINITY;

        for (final MovimientoYConsecuencias movimientoYConsecuencias : listaMovimientoYConsecuencias) {

            final float gananciaAcumuladaMinimaItem = movimientoYConsecuencias.getGananciaAcumulada();
            gananciaAcumuladaMinima = Math.min(gananciaAcumuladaMinima, gananciaAcumuladaMinimaItem);
        }

        return gananciaAcumuladaMinima;
    }
}

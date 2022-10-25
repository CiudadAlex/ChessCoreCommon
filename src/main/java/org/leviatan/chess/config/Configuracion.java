package org.leviatan.chess.config;

import org.leviatan.chess.board.Bando;

/**
 * Configuracion.
 *
 * @author Alejandro
 */
public final class Configuracion {

    private Configuracion() {
    }

    /** TALLA_TABLERO. */
    public static final int TALLA_TABLERO = 8;

    /** VALOR_PROTECCION. */
    public static float VALOR_PROTECCION = 0.5f;

    /** VALOR_FICHA_REY. */
    public static float VALOR_FICHA_REY = 1000;

    /** VALOR_FICHA_REINA. */
    public static float VALOR_FICHA_REINA = 10;

    /** VALOR_FICHA_TORRE. */
    public static float VALOR_FICHA_TORRE = 5;

    /** VALOR_FICHA_ALFIL. */
    public static float VALOR_FICHA_ALFIL = 3;

    /** VALOR_FICHA_CABALLO. */
    public static float VALOR_FICHA_CABALLO = 3;

    /** VALOR_FICHA_PEON. */
    public static float VALOR_FICHA_PEON = 1;

    /** Bando de la CPU. */
    public static final Bando BANDO_CPU = Bando.NEGRO;

    /** Numero de pares jugada-replica a calcular. */
    public static final int PROFUNDIDAD_ARBOL_DE_MOVIMIENTOS = 2;

    /** TALLA_INICIAL_ARRAYLIST_MOVIMIENTOS (Performance). */
    public static final int TALLA_INICIAL_ARRAYLIST_MOVIMIENTOS = 30;

    /** Indica si el traceado esta habilitado. */
    public static final boolean IS_TRACE_ENABLED = false;

    /** Indica si el perfilado esta habilitado. */
    public static final boolean IS_PROFILING_ENABLED = false;
}

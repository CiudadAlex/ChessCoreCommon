package org.leviatan.chess.board;

import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.movimientosposibles.AbstractGeneradorMovimientosPosibles;
import org.leviatan.chess.engine.movimientosposibles.GeneradorMovimientosPosiblesAlfil;
import org.leviatan.chess.engine.movimientosposibles.GeneradorMovimientosPosiblesCaballo;
import org.leviatan.chess.engine.movimientosposibles.GeneradorMovimientosPosiblesPeon;
import org.leviatan.chess.engine.movimientosposibles.GeneradorMovimientosPosiblesReina;
import org.leviatan.chess.engine.movimientosposibles.GeneradorMovimientosPosiblesRey;
import org.leviatan.chess.engine.movimientosposibles.GeneradorMovimientosPosiblesTorre;

/**
 * TipoFicha.
 *
 * @author Alejandro
 *
 */
public enum TipoFicha {

    /** REY. */
    REY(Configuracion.VALOR_FICHA_REY, new GeneradorMovimientosPosiblesRey(), "K"),

    /** REINA. */
    REINA(Configuracion.VALOR_FICHA_REINA, new GeneradorMovimientosPosiblesReina(), "Q"),

    /** TORRE. */
    TORRE(Configuracion.VALOR_FICHA_TORRE, new GeneradorMovimientosPosiblesTorre(), "R"),

    /** ALFIL. */
    ALFIL(Configuracion.VALOR_FICHA_ALFIL, new GeneradorMovimientosPosiblesAlfil(), "B"),

    /** CABALLO. */
    CABALLO(Configuracion.VALOR_FICHA_CABALLO, new GeneradorMovimientosPosiblesCaballo(), "N"),

    /** PEON. */
    PEON(Configuracion.VALOR_FICHA_PEON, new GeneradorMovimientosPosiblesPeon(), "P");

    private final float valor;

    private final AbstractGeneradorMovimientosPosibles generadorMovimientosPosibles;

    private final String letra;

    private TipoFicha(final float valor, final AbstractGeneradorMovimientosPosibles generadorMovimientosPosibles, final String letra) {

        this.valor = valor;
        this.generadorMovimientosPosibles = generadorMovimientosPosibles;
        this.letra = letra;
    }

    public float getValor() {
        return this.valor;
    }

    public AbstractGeneradorMovimientosPosibles getGeneradorMovimientosPosibles() {
        return this.generadorMovimientosPosibles;
    }

    public String getLetra() {
        return this.letra;
    }

}

package org.leviatan.chess.board;

import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * Ficha.
 *
 * @author Alejandro
 *
 */
public class Ficha extends ProfiledObject {

    /** Bando al que pertenece esta ficha. */
    private final Bando bando;

    /** Tipo de ficha. */
    private final TipoFicha tipoFicha;

    /**
     * Constructor para Ficha.
     *
     * @param bando
     *            bando
     * @param tipoFicha
     *            tipoFicha
     */
    public Ficha(final Bando bando, final TipoFicha tipoFicha) {
        this.bando = bando;
        this.tipoFicha = tipoFicha;
    }

    public Bando getBando() {
        return this.bando;
    }

    public TipoFicha getTipoFicha() {
        return this.tipoFicha;
    }

    /**
     * Clona la ficha.
     *
     * @return la ficha clonada
     */
    public Ficha clonar() {
        return new Ficha(this.bando, this.tipoFicha);
    }
}

package org.leviatan.chess.engine.movimientosposibles;

/**
 * EstadoCasilla.
 *
 * @author Alejandro
 */
public enum EstadoCasilla {

    /** . */
    VACIA(0),

    /** OCUPADA_FICHA_ALIADA. */
    OCUPADA_FICHA_ALIADA(1),

    /** OCUPADA_FICHA_ENEMIGA. */
    OCUPADA_FICHA_ENEMIGA(2);

    private int id;

    private EstadoCasilla(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

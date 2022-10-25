package org.leviatan.chess.board;

/**
 * Bando.
 *
 * @author Alejandro
 *
 */
public enum Bando {

    /** BLANCO. */
    BLANCO(0),

    /** NEGRO. */
    NEGRO(1);

    private int identificador;

    private Bando(final int identificador) {
        this.identificador = identificador;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public Bando getBandoOpuesto() {
        return getBandoOpuesto(this);
    }

    /**
     * Devuelve el bando por el nombre.
     *
     * @param nombreBando
     *            nombreBando
     * @return el bando por el nombre
     */
    public static Bando getBandoByName(final String nombreBando) {

        for (final Bando bando : values()) {

            if (bando.name().equals(nombreBando)) {
                return bando;
            }
        }

        return null;
    }

    /**
     * Devuelve el bando opuesto del dado.
     *
     * @param bando
     *            bando
     * @return el bando opuesto del dado
     */
    public static Bando getBandoOpuesto(final Bando bando) {

        if (bando == BLANCO) {
            return NEGRO;
        }

        return BLANCO;
    }

}

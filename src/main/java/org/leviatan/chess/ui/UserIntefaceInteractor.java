package org.leviatan.chess.ui;

import java.util.Date;

/**
 * UserIntefaceInteractor.
 *
 * @author Alejandro
 */
public interface UserIntefaceInteractor {

    /**
     * Mostrar mensaje.
     *
     * @param text
     *            text
     */
    public void mostrarMensaje(String text);

    /** Muestra mensaje victoria CPU. */
    public void mostrarMensajeVictoriaCPU();

    /** Muestra mensaje victoria Humano. */
    public void mostrarMensajeVictoriaHumano();

    /** Muestra mensaje empate. */
    public void mostrarMensajeEmpate();

    /** Inicio calculos. */
    public void inicioCalculos();

    /**
     * Muestra progreso calculos.
     *
     * @param progresado
     *            progresado
     */
    public void mostrarProgreso(double progresado);

    /**
     * Fin calculos.
     *
     * @param textoJugada
     *            textoJugada
     */
    public void finCalculos(String textoJugada);

    /**
     * Returns an instance that logs only.
     *
     * @return an instance that logs only
     */
    public static UserIntefaceInteractor getOnlyLogInstance() {

        final UserIntefaceInteractor userIntefaceInteractor = new UserIntefaceInteractor() {

            private Date init;

            @Override
            public void mostrarMensaje(final String text) {
            }

            @Override
            public void mostrarMensajeVictoriaCPU() {
            }

            @Override
            public void mostrarMensajeVictoriaHumano() {
            }

            @Override
            public void mostrarMensajeEmpate() {
            }

            @Override
            public void inicioCalculos() {
                init = new Date();
            }

            @Override
            public void mostrarProgreso(final double progresado) {
                // System.out.println("Progreso = " + progresado);
            }

            @Override
            public void finCalculos(final String textoJugada) {

                final Date now = new Date();
                final long millis = now.getTime() - init.getTime();
                System.out.println("Fin Calculos partida: " + millis);
            }

        };

        return userIntefaceInteractor;
    }
}

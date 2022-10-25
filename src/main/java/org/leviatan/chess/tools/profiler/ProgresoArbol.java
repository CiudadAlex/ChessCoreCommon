package org.leviatan.chess.tools.profiler;

import org.leviatan.chess.ui.UserIntefaceInteractor;

/**
 * ProgresoArbol.
 *
 * @author Alejandro
 */
public class ProgresoArbol {

    private int progresoNivel1;
    private int nivel1Total = 1;

    private int progresoNivel2;
    private int nivel2Total = 1;

    private final UserIntefaceInteractor userIntefaceInteractor;

    /**
     * Constructor para ProgresoArbol.
     *
     * @param userIntefaceInteractor
     *            userIntefaceInteractor
     */
    public ProgresoArbol(final UserIntefaceInteractor userIntefaceInteractor) {
        this.userIntefaceInteractor = userIntefaceInteractor;
    }

    /**
     * Inicio del nivel dado.
     *
     * @param nivel
     *            nivel
     * @param numeroNodos
     *            numeroNodos
     */
    public void inicioNivel(final int nivel, final int numeroNodos) {

        if (nivel == 1) {
            inicioNivel1(numeroNodos);

        } else if (nivel == 2) {
            inicioNivel2(numeroNodos);
        }

        // El resto de niveles se ignora
    }

    /** Inicio del nivel 1. */
    private void inicioNivel1(final int numeroNodos) {
        progresoNivel1 = 0;
        nivel1Total = numeroNodos;

        progresoNivel2 = 0;
        nivel2Total = 1;
    }

    /** Inicio del nivel 2. */
    private void inicioNivel2(final int numeroNodos) {
        progresoNivel2 = 0;
        nivel2Total = numeroNodos;
    }

    /**
     * Calcula el nuevo progreso y actualiza el interfaz.
     *
     * @param nivel
     *            nivel
     */
    public void progresoEnNivel(final int nivel) {

        if (nivel == 1) {
            progresoEnNivel1();

        } else if (nivel == 2) {
            progresoEnNivel2();
        }

        // El resto de niveles se ignora
    }

    /** Calcula el progreso e informa al UI. */
    private void calculaProgresoEInformaAUI() {

        final double progresoPorUnaje = calculaProgreso();
        userIntefaceInteractor.mostrarProgreso(progresoPorUnaje);
    }

    /** Calcula el progreso actual. */
    private double calculaProgreso() {

        final double porUnajeNivel1 = (double) progresoNivel1 / (double) nivel1Total;

        final double valorNodoNivel1 = 1d / nivel1Total;
        final double porUnajeNivel2 = valorNodoNivel1 * progresoNivel2 / nivel2Total;

        return porUnajeNivel1 + porUnajeNivel2;
    }

    private void progresoEnNivel1() {
        progresoNivel1++;
        progresoNivel2 = 0;
        calculaProgresoEInformaAUI();
    }

    private void progresoEnNivel2() {
        progresoNivel2++;
        calculaProgresoEInformaAUI();
    }
}

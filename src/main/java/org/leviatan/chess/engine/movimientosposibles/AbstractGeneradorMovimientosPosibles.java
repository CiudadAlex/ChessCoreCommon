package org.leviatan.chess.engine.movimientosposibles;

import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.movimientosposibles.dto.MovimientosPosiblesSinRestricciones;
import org.leviatan.chess.engine.repositorios.RepositorioMovimientos;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTableroDireccion;

/**
 * AbstractGeneradorMovimientosPosibles.
 *
 * @author Alejandro
 */
public abstract class AbstractGeneradorMovimientosPosibles {

    /**
     * Genera los movimientos posibles.
     *
     * @param posicionTablero
     *            posicionTablero
     * @param tablero
     *            tablero
     * @param listaMovimientos
     *            listaMovimientos
     */
    public abstract void generaMovimientosPosiblesYAddToLista(PosicionTablero posicionTablero, Tablero tablero,
            List<Movimiento> listaMovimientos);

    /**
     * Genera los movimientos posibles sin restricciones.
     *
     * @param posicionTablero
     *            posicionTablero
     * @param movimientosPosiblesSinRestricciones
     *            movimientosPosiblesSinRestricciones
     * @param bando
     *            bando
     */
    public abstract void generaMovimientosPosiblesSinRestriccionesYAddToMovimientosPosiblesSinRestricciones(PosicionTablero posicionTablero,
            MovimientosPosiblesSinRestricciones movimientosPosiblesSinRestricciones, Bando bando);

    /**
     * Devuelve true si la casilla existe y esta o vacia o ocupara con una ficha
     * del bando contrario.
     */
    private boolean laPosicionTableroExisteYEstaVaciaOConFichaBandoContrario(final Bando bando, final PosicionTablero posicionTablero,
            final Tablero tablero) {

        final EstadoCasilla estadoCasilla = obtenerEstadoCasillaSabiendoQueExiste(bando, posicionTablero, tablero);

        return estadoCasilla == EstadoCasilla.VACIA || estadoCasilla == EstadoCasilla.OCUPADA_FICHA_ENEMIGA;
    }

    /** Obtiene el estado de la casilla dada. */
    protected EstadoCasilla obtenerEstadoCasillaSabiendoQueExiste(final Bando bando, final PosicionTablero posicionTablero,
            final Tablero tablero) {

        final Ficha ficha = tablero.getFicha(posicionTablero);

        if (ficha != null) {
            final Bando bandoFichaDestino = ficha.getBando();

            if (bando != bandoFichaDestino) {
                return EstadoCasilla.OCUPADA_FICHA_ENEMIGA;

            } else {
                return EstadoCasilla.OCUPADA_FICHA_ALIADA;
            }

        } else {
            return EstadoCasilla.VACIA;
        }
    }

    /**
     * Genera los movimientos posibles considerando que se puede llegar a todos
     * las posiciones finales.
     */
    protected void generaMovimientosPosiblesConsiderandoQueSePuedeLlegarALosFinalesYQueNoSonFueraYAddToLista(
            final PosicionTablero posicionTablero, final Tablero tablero, final List<PosicionTablero> listaPosicionTableroFinales,
            final List<Movimiento> listaMovimientos) {

        final Bando bando = tablero.getFicha(posicionTablero).getBando();

        for (final PosicionTablero posicionTableroFinal : listaPosicionTableroFinales) {

            final boolean posicionCorrecta = laPosicionTableroExisteYEstaVaciaOConFichaBandoContrario(bando, posicionTableroFinal, tablero);

            if (posicionCorrecta) {
                listaMovimientos.add(RepositorioMovimientos.getMovimiento(posicionTablero, posicionTableroFinal));
            }
        }
    }

    /** Genera los movimientos posibles en las direcciones dadas. */
    protected void generaMovimientosPosiblesEnDireccionesDadasYAddToLista(final PosicionTablero posicionTablero, final Tablero tablero,
            final List<Direccion> listaDirecciones, final List<Movimiento> listaMovimientos) {

        for (final Direccion direccion : listaDirecciones) {
            llenaListadoMovimientosPosiblesConPosicionesDeDireccionDada(posicionTablero, tablero, direccion, listaMovimientos);
        }
    }

    /**
     * Llena la lista de movimientos posibles con las posiciones de la direccion
     * dada.
     */
    private void llenaListadoMovimientosPosiblesConPosicionesDeDireccionDada(final PosicionTablero posicionTablero, final Tablero tablero,
            final Direccion direccion, final List<Movimiento> listaMovimientosPosibles) {

        final Bando bando = tablero.getFicha(posicionTablero).getBando();
        final List<PosicionTablero> listaPosicionTableroFinalesSecuenciales = RepositorioPosicionesTableroDireccion
                .getListaPosicionTableroPosicionesDireccion(posicionTablero.getHorizontal(), posicionTablero.getVertical(), direccion);

        for (final PosicionTablero posicionTableroFinal : listaPosicionTableroFinalesSecuenciales) {

            final EstadoCasilla estadoCasilla = obtenerEstadoCasillaSabiendoQueExiste(bando, posicionTableroFinal, tablero);

            if (estadoCasilla == EstadoCasilla.VACIA || estadoCasilla == EstadoCasilla.OCUPADA_FICHA_ENEMIGA) {
                listaMovimientosPosibles.add(RepositorioMovimientos.getMovimiento(posicionTablero, posicionTableroFinal));
            }

            if (estadoCasilla != EstadoCasilla.VACIA) {
                // Solo si la posicion esta vacia interesa seguir iterando
                return;
            }
        }
    }
}

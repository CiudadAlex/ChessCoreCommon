package org.leviatan.chess.engine.movimientosposibles;

import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.engine.movimientosposibles.dto.MovimientosPosiblesSinRestricciones;
import org.leviatan.chess.engine.movimientosposibles.dto.PosicionesPeon;
import org.leviatan.chess.engine.repositorios.RepositorioMovimientos;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTableroPeon;

/**
 * GeneradorMovimientosPosiblesPeon.
 *
 * @author Alejandro
 */
public class GeneradorMovimientosPosiblesPeon extends AbstractGeneradorMovimientosPosibles {

    /** Genera los movimientos posibles sin restricciones. */
    @Override
    public void generaMovimientosPosiblesSinRestriccionesYAddToMovimientosPosiblesSinRestricciones(final PosicionTablero posicionTablero,
            final MovimientosPosiblesSinRestricciones movimientosPosiblesSinRestricciones, final Bando bando) {

        final PosicionesPeon posicionesPeonFinales = RepositorioPosicionesTableroPeon
                .getListaPosicionTableroPosicionesPeon(posicionTablero.getHorizontal(), posicionTablero.getVertical(), bando);
        movimientosPosiblesSinRestricciones.addMovimientosPeon(posicionTablero, posicionesPeonFinales);
    }

    @Override
    public void generaMovimientosPosiblesYAddToLista(final PosicionTablero posicionTablero, final Tablero tablero,
            final List<Movimiento> listaMovimientos) {

        final Ficha ficha = tablero.getFicha(posicionTablero);
        final Bando bando = ficha.getBando();

        final PosicionesPeon posicionesPeon = RepositorioPosicionesTableroPeon
                .getListaPosicionTableroPosicionesPeon(posicionTablero.getHorizontal(), posicionTablero.getVertical(), bando);

        final PosicionTablero posicionTableroDiagonalIzquierda = posicionesPeon.getPosicionTableroDiagonalIzquierda();
        final PosicionTablero posicionTableroCentro = posicionesPeon.getPosicionTableroCentro();
        final PosicionTablero posicionTableroCentroDoble = posicionesPeon.getPosicionTableroCentroDoble();
        final PosicionTablero posicionTableroDiagonalDerecha = posicionesPeon.getPosicionTableroDiagonalDerecha();

        if (posicionTableroCentro != null && laPosicionTableroExisteYEstaVacia(posicionTableroCentro, tablero)) {
            listaMovimientos.add(RepositorioMovimientos.getMovimiento(posicionTablero, posicionTableroCentro));

            // Solamente si existe la posibilidad de avanzar una podriamos
            // avanzar 2
            if (posicionTableroCentroDoble != null && laPosicionTableroExisteYEstaVacia(posicionTableroCentroDoble, tablero)) {
                listaMovimientos.add(RepositorioMovimientos.getMovimiento(posicionTablero, posicionTableroCentroDoble));
            }
        }

        if (posicionTableroDiagonalIzquierda != null
                && laPosicionTableroExisteYContieneFichaBandoContrario(bando, posicionTableroDiagonalIzquierda, tablero)) {
            listaMovimientos.add(RepositorioMovimientos.getMovimiento(posicionTablero, posicionTableroDiagonalIzquierda));
        }

        if (posicionTableroDiagonalDerecha != null
                && laPosicionTableroExisteYContieneFichaBandoContrario(bando, posicionTableroDiagonalDerecha, tablero)) {
            listaMovimientos.add(RepositorioMovimientos.getMovimiento(posicionTablero, posicionTableroDiagonalDerecha));
        }
    }

    /**
     * Devuelve true si la casilla existe y esta vacia.
     *
     * @param posicionTablero
     *            posicionTablero
     * @param tablero
     *            tablero
     * @return true si la casilla existe y esta vacia
     */
    private boolean laPosicionTableroExisteYEstaVacia(final PosicionTablero posicionTablero, final Tablero tablero) {

        final Bando bandoAleatorioYaQueNoImporta = Bando.BLANCO;
        final EstadoCasilla estadoCasilla = obtenerEstadoCasillaSabiendoQueExiste(bandoAleatorioYaQueNoImporta, posicionTablero, tablero);

        return estadoCasilla == EstadoCasilla.VACIA;
    }

    /**
     * Devuelve true si la casilla existe y contiene una ficha del bando
     * contrario.
     */
    private boolean laPosicionTableroExisteYContieneFichaBandoContrario(final Bando bando, final PosicionTablero posicionTablero,
            final Tablero tablero) {

        final EstadoCasilla estadoCasilla = obtenerEstadoCasillaSabiendoQueExiste(bando, posicionTablero, tablero);

        return estadoCasilla == EstadoCasilla.OCUPADA_FICHA_ENEMIGA;
    }

}

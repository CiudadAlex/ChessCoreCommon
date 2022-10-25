package org.leviatan.chess.engine.movimientosposibles.dto;

import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.engine.repositorios.RepositorioMovimientos;

/**
 * MovimientosPeon.
 *
 * @author Alejandro
 */
public class MovimientosPeon {

    private final Movimiento movimientoDiagonalIzquierda;
    private final Movimiento movimientoCentro;
    private final Movimiento movimientoCentroDoble;
    private final Movimiento movimientoDiagonalDerecha;

    /**
     * Constructor para MovimientosPeon.
     *
     * @param posicionTableroInicial
     *            posicionTableroInicial
     * @param posicionesPeonFinales
     *            posicionesPeonFinales
     */
    public MovimientosPeon(final PosicionTablero posicionTableroInicial, final PosicionesPeon posicionesPeonFinales) {

        movimientoDiagonalIzquierda = RepositorioMovimientos.getMovimiento(posicionTableroInicial,
                posicionesPeonFinales.getPosicionTableroDiagonalIzquierda());
        movimientoCentro = RepositorioMovimientos.getMovimiento(posicionTableroInicial, posicionesPeonFinales.getPosicionTableroCentro());
        movimientoCentroDoble = RepositorioMovimientos.getMovimiento(posicionTableroInicial,
                posicionesPeonFinales.getPosicionTableroCentroDoble());
        movimientoDiagonalDerecha = RepositorioMovimientos.getMovimiento(posicionTableroInicial,
                posicionesPeonFinales.getPosicionTableroDiagonalDerecha());
    }

    public Movimiento getMovimientoCentro() {
        return movimientoCentro;
    }

    public Movimiento getMovimientoCentroDoble() {
        return movimientoCentroDoble;
    }

    public Movimiento getMovimientoDiagonalDerecha() {
        return movimientoDiagonalDerecha;
    }

    public Movimiento getMovimientoDiagonalIzquierda() {
        return movimientoDiagonalIzquierda;
    }
}

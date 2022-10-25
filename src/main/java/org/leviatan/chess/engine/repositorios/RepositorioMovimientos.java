package org.leviatan.chess.engine.repositorios;

import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.tools.profiler.Cronometro;

/**
 * RepositorioMovimientos.
 *
 * @author Alejandro
 */
public final class RepositorioMovimientos {

    private static Movimiento[][][][] movimientoMatrix = generaMatrizMovimientos();

    private RepositorioMovimientos() {
    }

    private static Movimiento[][][][] generaMatrizMovimientos() {

        final Cronometro cronometro = new Cronometro(true);
        cronometro.tic("Cargando RepositorioMovimientos...");

        final Movimiento[][][][] movimientoMatrixTemp = new Movimiento[Tablero.TALLA_TABLERO][Tablero.TALLA_TABLERO][Tablero.TALLA_TABLERO][Tablero.TALLA_TABLERO];

        for (int verticalInicial = 0; verticalInicial < Tablero.TALLA_TABLERO; verticalInicial++) {
            for (int horizontalInicial = 0; horizontalInicial < Tablero.TALLA_TABLERO; horizontalInicial++) {

                final PosicionTablero posicionTableroInicial = RepositorioPosicionesTablero.getPosicionTablero(horizontalInicial,
                        verticalInicial);

                for (int verticalFinal = 0; verticalFinal < Tablero.TALLA_TABLERO; verticalFinal++) {
                    for (int horizontalFinal = 0; horizontalFinal < Tablero.TALLA_TABLERO; horizontalFinal++) {

                        final PosicionTablero posicionTableroFinal = RepositorioPosicionesTablero.getPosicionTablero(horizontalFinal,
                                verticalFinal);
                        final Movimiento movimiento = new Movimiento(posicionTableroInicial, posicionTableroFinal);
                        movimientoMatrixTemp[posicionTableroInicial.getHorizontal()][posicionTableroInicial
                                .getVertical()][posicionTableroFinal.getHorizontal()][posicionTableroFinal.getVertical()] = movimiento;
                    }
                }
            }
        }

        cronometro.toc("RepositorioMovimientos");

        return movimientoMatrixTemp;
    }

    /**
     * Obtiene el movimiento.
     *
     * @param posicionTableroInicial
     *            posicionTableroInicial
     * @param posicionTableroFinal
     *            posicionTableroFinal
     * @return el movimiento
     */
    public static Movimiento getMovimiento(final PosicionTablero posicionTableroInicial, final PosicionTablero posicionTableroFinal) {
        return movimientoMatrix[posicionTableroInicial.getHorizontal()][posicionTableroInicial.getVertical()][posicionTableroFinal
                .getHorizontal()][posicionTableroFinal.getVertical()];
    }
}

package org.leviatan.chess.tools.debugger;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.engine.intel.dto.MovimientoYConsecuencias;
import org.leviatan.chess.engine.intel.dto.TableroModificado;
import org.leviatan.chess.engine.repositorios.RepositorioMovimientos;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTablero;
import org.leviatan.chess.tools.log.Logger;

/**
 * InspectorArbol.
 *
 * @author Alejandro
 */
public final class InspectorArbol {

    private InspectorArbol() {
    }

    /**
     * Printea parte del arbol.
     *
     * @param tableroModificado
     *            tableroModificado
     * @param movimientoYConsecuenciasActual
     *            movimientoYConsecuenciasActual
     * @param listaReplicas
     *            listaReplicas
     * @return resultado
     */
    public static boolean printArbolParaDebug(final TableroModificado tableroModificado,
            final MovimientoYConsecuencias movimientoYConsecuenciasActual, final List<MovimientoYConsecuencias> listaReplicas) {

        // Movimiento Root
        final PosicionTablero posicionTableroMov0Orig = RepositorioPosicionesTablero.getPosicionTablero(0, 0);
        final PosicionTablero posicionTableroMov0Dest = RepositorioPosicionesTablero.getPosicionTablero(0, 0);

        final PosicionTablero posicionTableroMov1Orig = RepositorioPosicionesTablero.getPosicionTablero(0, 6);
        final PosicionTablero posicionTableroMov1Dest = RepositorioPosicionesTablero.getPosicionTablero(0, 5);

        final PosicionTablero posicionTableroMov2Orig = RepositorioPosicionesTablero.getPosicionTablero(7, 4);
        final PosicionTablero posicionTableroMov2Dest = RepositorioPosicionesTablero.getPosicionTablero(5, 6);

        final PosicionTablero posicionTableroMov3Orig = RepositorioPosicionesTablero.getPosicionTablero(4, 7);
        final PosicionTablero posicionTableroMov3Dest = RepositorioPosicionesTablero.getPosicionTablero(5, 6);

        final Movimiento movimiento0 = RepositorioMovimientos.getMovimiento(posicionTableroMov0Orig, posicionTableroMov0Dest);
        final Movimiento movimiento1 = RepositorioMovimientos.getMovimiento(posicionTableroMov1Orig, posicionTableroMov1Dest);
        final Movimiento movimiento2 = RepositorioMovimientos.getMovimiento(posicionTableroMov2Orig, posicionTableroMov2Dest);
        final Movimiento movimiento3 = RepositorioMovimientos.getMovimiento(posicionTableroMov3Orig, posicionTableroMov3Dest);

        final List<Movimiento> listaMovimientoDeseados = new ArrayList<Movimiento>();
        listaMovimientoDeseados.add(movimiento0);
        listaMovimientoDeseados.add(movimiento1);
        listaMovimientoDeseados.add(movimiento2);
        listaMovimientoDeseados.add(movimiento3);

        return printArbolParaDebug(tableroModificado, movimientoYConsecuenciasActual, listaReplicas, listaMovimientoDeseados);
    }

    /** Printea parte del arbol. */
    private static boolean printArbolParaDebug(final TableroModificado tableroModificado,
            final MovimientoYConsecuencias movimientoYConsecuenciasActual, final List<MovimientoYConsecuencias> listaReplicas,
            final List<Movimiento> listaMovimientoDeseados) {

        final List<Movimiento> listaMovimientoTablero = tableroModificado.getListaMovimientos();

        final int tallaMovimientosDeseados = listaMovimientoDeseados.size();
        final int tallaMovimientosTablero = listaMovimientoTablero.size();

        boolean coincide = true;

        for (int i = 0; i < tallaMovimientosDeseados; i++) {

            if (i < tallaMovimientosTablero) {
                final Movimiento movimientoDeseado = listaMovimientoDeseados.get(i);
                final Movimiento movimientoTablero = listaMovimientoTablero.get(i);

                if (!movimientoDeseado.equals(movimientoTablero)) {
                    coincide = false;
                    break;
                }

            } else {
                coincide = false;
                break;
            }
        }

        if (coincide) {
            Logger.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Inspector del Arbol <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            Logger.log(movimientoYConsecuenciasActual);
            Logger.log(listaReplicas);

            return true;
        }

        return false;
    }
}

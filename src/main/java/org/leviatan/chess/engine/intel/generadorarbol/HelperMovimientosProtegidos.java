package org.leviatan.chess.engine.intel.generadorarbol;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Casilla;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.TableroModificado;

/**
 * HelperMovimientosProtegidos.
 *
 * @author Alejandro
 */
public final class HelperMovimientosProtegidos {

    private static final Ficha CABALLO_BLANCO = new Ficha(Bando.BLANCO, TipoFicha.CABALLO);
    private static final Ficha CABALLO_NEGRO = new Ficha(Bando.NEGRO, TipoFicha.CABALLO);

    private HelperMovimientosProtegidos() {
    }

    private static Tablero tableroCalculosMovimientosProtegidos = new Tablero();

    /**
     * Copia el tablero dado en el tableroCalculosMovimientosProtegidos y
     * devuelve este ultimo.
     *
     * @param tablero
     *            tablero
     */
    private static Tablero getTableroCalculosMovimientosProtegidosCopiadoDelDado(final Tablero tablero) {

        TableroModificado.igualaElTableroModificableAlNoModificable(tablero, tableroCalculosMovimientosProtegidos);
        return tableroCalculosMovimientosProtegidos;
    }

    private static Ficha getFichaBandoOpuesto(final Bando bando) {

        if (bando == Bando.BLANCO) {
            return CABALLO_NEGRO;
        }

        return CABALLO_BLANCO;
    }

    /**
     * Marca los MovimientoYConsecuencias con posiciones finales protegidas.
     *
     * @param movimiento
     *            movimiento
     * @param tablero
     *            tablero
     * @param bando
     *            bando
     * @return si esta el movimiento protegido
     */
    public static boolean estaProtegidosElMovimientoConPosicionesFinalesProtegidasDeBando(final Movimiento movimiento,
            final Tablero tablero, final Bando bando) {

        final Tablero tableroConFichaDeBandoOpuestoEnDestino = creaTableroConCasillaDestinoDeMovimientoDelBandoOpuesto(movimiento, tablero,
                bando);
        final List<Movimiento> listaMovimientosPosiblesBando = HelperMovimientosPosibles
                .getMovimientosPosiblesDeBando(tableroConFichaDeBandoOpuestoEnDestino, bando);

        if (estaElMovimientoProtegido(movimiento, listaMovimientosPosiblesBando)) {
            return true;
        }

        return false;
    }

    /**
     * Crea un tablero con la casilla de destino del movimiento blanca para
     * simular que si nos comen podemos vengarnos.
     *
     * @param movimiento
     *            movimiento
     * @param tablero
     *            tablero
     * @param bando
     *            bando
     * @return Tablero
     */
    private static Tablero creaTableroConCasillaDestinoDeMovimientoDelBandoOpuesto(final Movimiento movimiento, final Tablero tablero,
            final Bando bando) {

        final PosicionTablero posicionDestino = movimiento.getPosicionDestino();
        final Tablero tableroConBlancaEnDestino = getTableroCalculosMovimientosProtegidosCopiadoDelDado(tablero);

        final Casilla casillaFinalTableroConBlancaEnDestino = tableroConBlancaEnDestino.getCasilla(posicionDestino);
        casillaFinalTableroConBlancaEnDestino.setFicha(getFichaBandoOpuesto(bando));

        return tableroConBlancaEnDestino;
    }

    private static boolean estaElMovimientoProtegido(final Movimiento movimiento,
            final List<Movimiento> listaMovimientosPosiblesTodasLasFichas) {

        final PosicionTablero posicionOrigenVetada = movimiento.getPosicionOrigen();
        final PosicionTablero posicionDestino = movimiento.getPosicionDestino();

        final List<Movimiento> listaMovimientosPosiblesOtrasFichas = quitaLosMovimientosQueEmpiezenEnLaPosicionDada(
                listaMovimientosPosiblesTodasLasFichas, posicionOrigenVetada);

        for (final Movimiento movimientoOtraFicha : listaMovimientosPosiblesOtrasFichas) {
            final PosicionTablero posicionDestinoOtraFicha = movimientoOtraFicha.getPosicionDestino();

            if (posicionDestino.equals(posicionDestinoOtraFicha)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Crea una lista nueva sin los movimientos que empiezen el la posicion de
     * origen dada.
     *
     * @param listaMovimientos
     *            listaMovimientos
     * @param posicionOrigenVetada
     *            posicionOrigenVetada
     * @return List<Movimiento>
     */
    private static List<Movimiento> quitaLosMovimientosQueEmpiezenEnLaPosicionDada(final List<Movimiento> listaMovimientos,
            final PosicionTablero posicionOrigenVetada) {

        final List<Movimiento> listaMovimientosFiltrada = new ArrayList<Movimiento>(Configuracion.TALLA_INICIAL_ARRAYLIST_MOVIMIENTOS);

        for (final Movimiento movimiento : listaMovimientos) {

            final PosicionTablero posicionOrigenItem = movimiento.getPosicionOrigen();

            if (!posicionOrigenItem.equals(posicionOrigenVetada)) {
                listaMovimientosFiltrada.add(movimiento);
            }
        }

        return listaMovimientosFiltrada;
    }
}

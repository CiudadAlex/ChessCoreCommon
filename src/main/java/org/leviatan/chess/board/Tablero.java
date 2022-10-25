package org.leviatan.chess.board;

import java.util.ArrayList;
import java.util.List;

import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.engine.intel.dto.TableroModificado;
import org.leviatan.chess.engine.repositorios.RepositorioPosicionesTablero;
import org.leviatan.chess.tools.profiler.ProfiledObject;

/**
 * Tablero.
 *
 * Identificadores de casillas del tablero.
 *
 * <code>
         T = TALLA_TABLERO-1

         [0,T] ... [T,T]
         ...       ...
         [0,0] ... [T,0]
  </code>
 *
 * @author Alejandro
 *
 *
 */
public class Tablero extends ProfiledObject {

    /** Tamaño del tablero. */
    public static final int TALLA_TABLERO = Configuracion.TALLA_TABLERO;

    /** Matriz del tablero. */
    private final MatrizTablero matrizTablero;

    /** Constructor para Tablero. */
    public Tablero() {
        this(true);
    }

    /**
     * Constructor para Tablero.
     *
     * @param startPartida
     *            startPartida
     */
    public Tablero(final boolean startPartida) {

        this.matrizTablero = new MatrizTablero(TALLA_TABLERO);

        if (startPartida) {
            nuevaPartida();
        }
    }

    /** Inicializa el tablero a nueva partida. */
    private void nuevaPartida() {

        ponerFichasBlancas();
        ponerFichasNegras();
    }

    private void ponerFichasNegras() {

        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(0, 6));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(1, 6));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(2, 6));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(3, 6));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(4, 6));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(5, 6));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(6, 6));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(7, 6));

        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.TORRE), RepositorioPosicionesTablero.getPosicionTablero(0, 7));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.CABALLO), RepositorioPosicionesTablero.getPosicionTablero(1, 7));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.ALFIL), RepositorioPosicionesTablero.getPosicionTablero(2, 7));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.REINA), RepositorioPosicionesTablero.getPosicionTablero(3, 7));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.REY), RepositorioPosicionesTablero.getPosicionTablero(4, 7));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.ALFIL), RepositorioPosicionesTablero.getPosicionTablero(5, 7));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.CABALLO), RepositorioPosicionesTablero.getPosicionTablero(6, 7));
        ponerFichaEnPosicionTablero(new Ficha(Bando.NEGRO, TipoFicha.TORRE), RepositorioPosicionesTablero.getPosicionTablero(7, 7));
    }

    private void ponerFichasBlancas() {

        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.TORRE), RepositorioPosicionesTablero.getPosicionTablero(0, 0));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.CABALLO), RepositorioPosicionesTablero.getPosicionTablero(1, 0));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.ALFIL), RepositorioPosicionesTablero.getPosicionTablero(2, 0));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.REINA), RepositorioPosicionesTablero.getPosicionTablero(3, 0));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.REY), RepositorioPosicionesTablero.getPosicionTablero(4, 0));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.ALFIL), RepositorioPosicionesTablero.getPosicionTablero(5, 0));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.CABALLO), RepositorioPosicionesTablero.getPosicionTablero(6, 0));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.TORRE), RepositorioPosicionesTablero.getPosicionTablero(7, 0));

        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(0, 1));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(1, 1));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(2, 1));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(3, 1));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(4, 1));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(5, 1));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(6, 1));
        ponerFichaEnPosicionTablero(new Ficha(Bando.BLANCO, TipoFicha.PEON), RepositorioPosicionesTablero.getPosicionTablero(7, 1));
    }

    /**
     * Pone la ficha en el tablero en la posicion dada.
     *
     *
     * @param ficha
     *            ficha
     * @param posicionTablero
     *            posicionTablero
     */
    public void ponerFichaEnPosicionTablero(final Ficha ficha, final PosicionTablero posicionTablero) {

        final int horizontal = posicionTablero.getHorizontal();
        final int vertical = posicionTablero.getVertical();

        final Casilla casilla = this.matrizTablero.getFromMatrix(horizontal, vertical);
        casilla.setFicha(ficha);
    }

    /**
     * Realiza el movimiento dado.
     *
     * @param movimiento
     *            movimiento
     */
    public void realizarMovimiento(final Movimiento movimiento) {

        if (movimiento.esMovimientoRoot()) {
            return;
        }

        final PosicionTablero posicionOrigen = movimiento.getPosicionOrigen();
        final PosicionTablero posicionDestino = movimiento.getPosicionDestino();

        final Ficha ficha = getFicha(posicionOrigen);

        ponerFichaEnPosicionTablero(null, posicionOrigen);
        ponerFichaEnPosicionTablero(ficha, posicionDestino);

        detectaEnroqueYMueveTorre(posicionOrigen, posicionDestino, ficha);
        detectaPromocionAReina(posicionDestino, ficha);
    }

    private void detectaPromocionAReina(final PosicionTablero posicionDestino, final Ficha ficha) {

        if (TipoFicha.PEON.equals(ficha.getTipoFicha())) {

            final Bando bando = ficha.getBando();

            if (posicionDestino.getVertical() == 0 || posicionDestino.getVertical() == TALLA_TABLERO - 1) {

                final Ficha fichaNuevaReina = new Ficha(bando, TipoFicha.REINA);
                ponerFichaEnPosicionTablero(fichaNuevaReina, posicionDestino);
            }
        }
    }

    private void detectaEnroqueYMueveTorre(final PosicionTablero posicionOrigen, final PosicionTablero posicionDestino, final Ficha ficha) {

        if (TipoFicha.REY.equals(ficha.getTipoFicha())) {

            final int diffHorizontal = posicionDestino.getHorizontal() - posicionOrigen.getHorizontal();

            if (diffHorizontal == 2) {

                final PosicionTablero posicionTorre = RepositorioPosicionesTablero.generaNuevaPosicionMovida(posicionOrigen.getHorizontal(),
                        posicionOrigen.getVertical(), 3, 0);
                final PosicionTablero posicionTorreDestino = RepositorioPosicionesTablero
                        .generaNuevaPosicionMovida(posicionOrigen.getHorizontal(), posicionOrigen.getVertical(), 1, 0);
                final Ficha torre = getFicha(posicionTorre);

                ponerFichaEnPosicionTablero(null, posicionTorre);
                ponerFichaEnPosicionTablero(torre, posicionTorreDestino);

            } else if (diffHorizontal == -2) {

                final PosicionTablero posicionTorre = RepositorioPosicionesTablero.generaNuevaPosicionMovida(posicionOrigen.getHorizontal(),
                        posicionOrigen.getVertical(), -4, 0);
                final PosicionTablero posicionTorreDestino = RepositorioPosicionesTablero
                        .generaNuevaPosicionMovida(posicionOrigen.getHorizontal(), posicionOrigen.getVertical(), -1, 0);
                final Ficha torre = getFicha(posicionTorre);

                ponerFichaEnPosicionTablero(null, posicionTorre);
                ponerFichaEnPosicionTablero(torre, posicionTorreDestino);
            }
        }
    }

    /**
     * Obtiene la casilla de la posicion dada.
     *
     * @param posicionTablero
     *            posicionTablero
     * @return la casilla de la posicion dada
     */
    public Casilla getCasilla(final PosicionTablero posicionTablero) {

        final int horizontal = posicionTablero.getHorizontal();
        final int vertical = posicionTablero.getVertical();

        return getCasilla(horizontal, vertical);
    }

    /**
     * Obtiene la casilla de la posicion dada.
     *
     * @param horizontal
     *            horizontal
     * @param vertical
     *            vertical
     * @return la casilla de la posicion dada
     */
    public Casilla getCasilla(final int horizontal, final int vertical) {
        return this.matrizTablero.getFromMatrix(horizontal, vertical);
    }

    /**
     * Obtiene la ficha de la posicion dada.
     *
     * @param posicionTablero
     *            posicionTablero
     * @return la ficha de la posicion dada
     */
    public Ficha getFicha(final PosicionTablero posicionTablero) {

        final Casilla casilla = getCasilla(posicionTablero);
        final Ficha ficha = casilla.getFicha();

        return ficha;
    }

    protected void print(final PosicionTablero posicionOrigen, final PosicionTablero posicionDestino) {

        System.out.println("origen  >> " + posicionOrigen.toString());
        System.out.println("destino >> " + posicionDestino.toString());

        final StringBuilder sb = new StringBuilder();

        for (int row = 7; row >= 0; row--) {

            for (int col = 0; col < 8; col++) {

                final Casilla casilla = this.getCasilla(col, row);
                final Ficha ficha = casilla.getFicha();

                if (ficha != null) {
                    sb.append(ficha.getTipoFicha().getLetra());

                } else {
                    sb.append(".");
                }

            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    /**
     * Devuelve un tablero especular. Al hacerlo especular los calculos se
     * pueden hacer de la misma manera y sigue aplicando la logica habitual del
     * enroque.
     *
     * @return un tablero especular
     */
    public Tablero creaTableroEspecular() {

        final Tablero especular = new Tablero(false);

        for (int v = 0; v < TALLA_TABLERO; v++) {

            for (int h = 0; h < TALLA_TABLERO; h++) {

                final Ficha ficha = getFicha(RepositorioPosicionesTablero.getPosicionTablero(h, v));

                if (ficha == null) {
                    continue;
                }

                final Ficha fichaEspecular = new Ficha(ficha.getBando().getBandoOpuesto(), ficha.getTipoFicha());
                especular.ponerFichaEnPosicionTablero(fichaEspecular,
                        RepositorioPosicionesTablero.getPosicionTablero(h, TALLA_TABLERO - 1 - v));
            }
        }

        return especular;
    }

    /**
     * Devuelve todas las fichas de bando y tipo en orden de izquierda a derecha
     * y en cada columna de abajo a arriba.
     *
     * @param bando
     *            bando
     * @param tipoFicha
     *            tipoFicha
     * @return todas las fichas de bando y tipo en orden de izquierda a derecha
     *         y en cada columna de arriba a abajo
     */
    public List<Casilla> getCasillasConFichaDeBandoYTipo(final Bando bando, final TipoFicha tipoFicha) {

        final List<Casilla> listCasilla = new ArrayList<Casilla>();

        for (int h = 0; h < TALLA_TABLERO; h++) {

            for (int v = 0; v < TALLA_TABLERO; v++) {

                final Casilla casilla = getCasilla(RepositorioPosicionesTablero.getPosicionTablero(h, v));
                final Ficha ficha = casilla.getFicha();

                if (ficha != null && bando.equals(ficha.getBando()) && tipoFicha.equals(ficha.getTipoFicha())) {
                    listCasilla.add(casilla);
                }
            }
        }

        return listCasilla;
    }

    /**
     * Clona el tablero.
     *
     * @return el tablero
     */
    public Tablero clonar() {

        final Tablero tableroModificable = new Tablero();
        TableroModificado.igualaElTableroModificableAlNoModificable(this, tableroModificable);
        return tableroModificable;
    }
}

package org.leviatan.chess.tools.persister;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.leviatan.chess.board.Bando;
import org.leviatan.chess.board.Ficha;
import org.leviatan.chess.board.Tablero;
import org.leviatan.chess.board.TipoFicha;
import org.leviatan.chess.config.Configuracion;
import org.leviatan.chess.tools.log.Logger;

/**
 * SaverLoaderTablero.
 *
 * @author Alejandro
 */
public final class SaverLoaderTablero {

    /** Nombre del fichero donde guardar la partida salvada. */
    public static final String NOMBRE_FICHERO_PARTIDA_SALVADA = "PartidaSalvadaChess.data";

    /** String separador entre casillas. */
    public static final String STRING_SEPARADOR_ENTRE_CASILLAS = "¬";

    /** String separador entre subelementos. */
    public static final String STRING_SEPARADOR_ENTRE_SUBELEMENTOS = ",";

    private SaverLoaderTablero() {
    }

    /**
     * Salva el tablero de forma persistente.
     *
     * @param tablero
     *            tablero
     */
    public static void save(final Tablero tablero) {

        final int tallaTablero = Configuracion.TALLA_TABLERO;
        final StringBuilder sb = new StringBuilder();

        for (int vertical = 0; vertical < tallaTablero; vertical++) {
            for (int horizontal = 0; horizontal < tallaTablero; horizontal++) {
                incluyeCasilla(sb, horizontal, vertical, tablero);
            }
        }

        final String texto = sb.toString();

        guardarEnFichero(texto.substring(0, texto.length() - 1));
    }

    /** Incluye la casilla dada. */
    private static void incluyeCasilla(final StringBuilder sb, final int horizontal, final int vertical, final Tablero tablero) {

        final Ficha ficha = tablero.getCasilla(horizontal, vertical).getFicha();

        if (ficha != null) {

            final Bando bando = ficha.getBando();
            final TipoFicha tipoFicha = ficha.getTipoFicha();

            sb.append(horizontal).append(STRING_SEPARADOR_ENTRE_SUBELEMENTOS);
            sb.append(vertical).append(STRING_SEPARADOR_ENTRE_SUBELEMENTOS);
            sb.append(bando).append(STRING_SEPARADOR_ENTRE_SUBELEMENTOS);
            sb.append(tipoFicha);

            sb.append(STRING_SEPARADOR_ENTRE_CASILLAS);
        }
    }

    /** Guardar en fichero de partida guardada. */
    private static void guardarEnFichero(final String txt) {

        final String path = "./" + NOMBRE_FICHERO_PARTIDA_SALVADA;
        final File file = new File(path);

        try {
            final FileOutputStream fos = new FileOutputStream(file);
            fos.write(txt.getBytes());
            fos.flush();
            fos.close();

        } catch (final Exception e) {
            Logger.logError("Error guardando el archivo de pa partida salvada", e);
        }
    }

    /**
     * Carga el tablero con los datos persistentes.
     *
     * @param tablero
     *            tablero
     */
    public static void load(final Tablero tablero) {

        limpiaTablero(tablero);
        final String text = cargarDeFichero();

        final String[] listaCasillas = text.split(STRING_SEPARADOR_ENTRE_CASILLAS);

        for (final String strCasilla : listaCasillas) {
            estableceCasillaDeTablero(strCasilla, tablero);
        }
    }

    /**
     * Establece la casilla del tablero.
     *
     * @param strCasilla
     *            strCasilla
     * @param tablero
     *            tablero
     */
    private static void estableceCasillaDeTablero(final String strCasilla, final Tablero tablero) {

        final String[] listaSubcomponentes = strCasilla.split(STRING_SEPARADOR_ENTRE_SUBELEMENTOS);

        final String strHorizontal = listaSubcomponentes[0];
        final String strVertical = listaSubcomponentes[1];
        final String strBando = listaSubcomponentes[2];
        final String strTipoFicha = listaSubcomponentes[3];

        final int horizontal = Integer.parseInt(strHorizontal);
        final int vertical = Integer.parseInt(strVertical);
        final Bando bando = encuentraToStringIgual(Bando.values(), strBando);
        final TipoFicha tipoFicha = encuentraToStringIgual(TipoFicha.values(), strTipoFicha);

        final Ficha ficha = new Ficha(bando, tipoFicha);
        tablero.getCasilla(horizontal, vertical).setFicha(ficha);
    }

    /**
     * Encuentra el objeto que tenga el mismo toString().
     *
     * @param lista
     *            lista
     * @param str
     *            str
     * @return el objeto que tenga el mismo toString
     */
    private static <T> T encuentraToStringIgual(final T[] lista, final String str) {

        for (final T t : lista) {

            if (t.toString().equals(str)) {
                return t;
            }
        }

        return null;
    }

    /** Limpia al tablero. */
    private static void limpiaTablero(final Tablero tablero) {

        final int tallaTablero = Configuracion.TALLA_TABLERO;

        for (int vertical = 0; vertical < tallaTablero; vertical++) {
            for (int horizontal = 0; horizontal < tallaTablero; horizontal++) {
                tablero.getCasilla(horizontal, vertical).setFicha(null);
            }
        }
    }

    /** Carga el fichero de partida guardada. */
    private static String cargarDeFichero() {

        final String path = "./" + NOMBRE_FICHERO_PARTIDA_SALVADA;
        final File file = new File(path);

        try {
            final FileInputStream fis = new FileInputStream(file);
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (fis.available() > 0) {
                final byte b = (byte) fis.read();
                baos.write(b);
            }

            fis.close();
            return baos.toString();

        } catch (final Exception e) {
            Logger.logError("Error guardando el archivo de pa partida salvada", e);
        }

        return null;
    }
}

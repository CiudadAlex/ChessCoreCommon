package org.leviatan.chess.data.pgn;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leviatan.chess.board.Movimiento;
import org.leviatan.chess.board.PosicionTablero;
import org.leviatan.chess.engine.deeplearning.ConfiguracionDeepLearning;
import org.leviatan.chess.tools.platform.SortCriteria;
import org.leviatan.chess.tools.platform.SortUtils;

import chesspresso.Chess;
import chesspresso.game.Game;
import chesspresso.move.Move;
import chesspresso.pgn.PGNReader;

/**
 * PGNReaderManager.
 *
 * @author Alejandro
 *
 */
public final class PGNReaderManager {

    private static final Map<String, Integer> MAP_COLUMNS_INDEX = getMapColumnsIndex();

    private PGNReaderManager() {
    }

    /**
     * Main method.
     *
     * @param args
     *            args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        getTodasLasPartidasDelCorpus();
    }

    /**
     * Devuelve todas las partidas del corpus.
     *
     * @return todas las partidas del corpus
     * @throws Exception
     */
    public static List<Partida> getTodasLasPartidasDelCorpus() throws Exception {

        final List<File> listFiles = getFilesCorpus();
        return getPartidas(listFiles);
    }

    /**
     * Devuelve todas las partidas del fichero de numero dado.
     *
     * @param numeroDelFile
     *            numeroDelFile
     * @return todas las partidas del fichero de numero dado
     * @throws Exception
     */
    public static List<Partida> getPartidasDelFichero(final int numeroDelFile) throws Exception {

        final List<File> listFiles = getFilesCorpus();

        if (numeroDelFile >= 0 && numeroDelFile < listFiles.size()) {
            return getPartidas(Arrays.asList(listFiles.get(numeroDelFile)));
        }

        return new ArrayList<Partida>();
    }

    private static List<Partida> getPartidas(final List<File> listFiles) throws Exception {

        final List<Partida> listPartida = new ArrayList<Partida>();

        for (final File filePGN : listFiles) {

            final PGNReader pgnReader = new PGNReader(new FileInputStream(filePGN), filePGN.getName());

            boolean end = false;

            while (!end) {

                end = processGameReturnEnd(listPartida, pgnReader);

                if (listPartida.size() % 100 == 0) {
                    System.out.println("Partidas parseadas: " + listPartida.size());
                }
            }
        }

        System.out.println("Partidas parseadas: " + listPartida.size());

        return listPartida;
    }

    private static List<File> getFilesCorpus() {

        final File dirCorpus = new File(ConfiguracionDeepLearning.DIR_CORPUS);
        final File[] arrayFiles = dirCorpus.listFiles();

        final List<File> listFiles = new ArrayList<File>();

        for (final File file : arrayFiles) {
            listFiles.add(file);
        }

        final SortCriteria<File, String> sortCriteria = new SortCriteria<File, String>(f -> f.getName());
        SortUtils.sort(listFiles, sortCriteria);

        return listFiles;
    }

    private static boolean processGameReturnEnd(final List<Partida> listPartida, final PGNReader pgnReader) {

        try {
            final Game game = pgnReader.parseGame();

            if (game == null) {
                return true;
            }

            final Partida partida = buildPartida(game);
            listPartida.add(partida);

        } catch (final Exception e) {
            System.out.println("Error parsing game: " + e.getMessage());
        }

        return false;
    }

    private static Partida buildPartida(final Game game) {

        final Partida partida = new Partida();

        game.gotoStart();
        final Move[] arrMove = game.getMainLine();

        for (final Move move : arrMove) {
            partida.addMovimiento(buildMovimiento(move));
        }

        return partida;
    }

    private static Movimiento buildMovimiento(final Move move) {

        final String sOrigen = Chess.sqiToStr(move.getFromSqi());
        final String sDestino = Chess.sqiToStr(move.getToSqi());
        // System.out.println(sOrigen + " >> " + sDestino);
        // g1 >> f3

        final PosicionTablero posicionOrigen = parseStringPosicion(sOrigen);
        final PosicionTablero posicionDestino = parseStringPosicion(sDestino);

        return new Movimiento(posicionOrigen, posicionDestino);
    }

    private static PosicionTablero parseStringPosicion(final String sPosicionArg) {

        // g1
        final String sPosicion = sPosicionArg.toLowerCase();

        final char chHoriz = sPosicion.charAt(0);
        final char chVert = sPosicion.charAt(1);

        final int horiz = MAP_COLUMNS_INDEX.get("" + chHoriz);
        final int vert = Integer.parseInt("" + chVert) - 1;

        return new PosicionTablero(horiz, vert);
    }

    private static Map<String, Integer> getMapColumnsIndex() {

        final Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("a", 0);
        map.put("b", 1);
        map.put("c", 2);
        map.put("d", 3);
        map.put("e", 4);
        map.put("f", 5);
        map.put("g", 6);
        map.put("h", 7);

        return map;
    }

}

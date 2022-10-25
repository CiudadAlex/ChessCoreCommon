package org.leviatan.chess.ui;

/**
 * ChessCoreFactory.
 *
 * @author Alejandro
 */
public final class ChessCoreFactory {

    private ChessCoreFactory() {
    }

    public static ChessCore getChessCore() {
        return new ChessCoreImpl();
    }
}

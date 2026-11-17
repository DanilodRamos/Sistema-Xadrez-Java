
package application;

import javax.swing.SwingUtilities;

import chess.ChessMatch;

public class ProgramGUI {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ChessMatch chessMatch = new ChessMatch();

            ChessWindow window = new ChessWindow(chessMatch);

            window.setVisible(true);
        });
    }
}


package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knigth;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessBoardPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color LIGHT_SQUARE = new Color(240, 217, 181);
    private static final Color DARK_SQUARE = new Color(181, 136, 99);

    private JButton[][] squares = new JButton[8][8];

    private ChessMatch chessMatch;

    public ChessBoardPanel() {
        this(new ChessMatch());
    }

    public ChessBoardPanel(ChessMatch chessMatch) {

        this.chessMatch = chessMatch;

        setLayout(new GridLayout(8, 8));
        setPreferredSize(new Dimension(600, 600));

        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {

                JButton square = new JButton();

                if ((row + col) % 2 == 0) {
                    square.setBackground(LIGHT_SQUARE);
                } else {
                    square.setBackground(DARK_SQUARE);
                }

                square.setFont(new Font("Serif", Font.PLAIN, 42));
                square.setFocusPainted(false);
                square.setBorder(
                    BorderFactory.createLineBorder(
                        new Color(80, 60, 40), 1
                    )
                );

                squares[row][col] = square;

                add(square);
            }
        }

        updateBoard();
    }

    public void updateBoard() {

        ChessPiece[][] pieces = chessMatch.getPieces();

        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {

                ChessPiece piece = pieces[row][col];

                if (piece == null) {
                    squares[row][col].setText("");
                } else {
                    squares[row][col].setText(getPieceSymbol(piece));
                }
            }
        }
    }

    private String getPieceSymbol(ChessPiece piece) {

        boolean white = piece.getColor() == chess.Color.WHITE;

        if (piece instanceof King) {
            return white ? "♔" : "♚";
        }

        if (piece instanceof Queen) {
            return white ? "♕" : "♛";
        }

        if (piece instanceof Rook) {
            return white ? "♖" : "♜";
        }

        if (piece instanceof Bishop) {
            return white ? "♗" : "♝";
        }

        if (piece instanceof Knigth) {
            return white ? "♘" : "♞";
        }

        if (piece instanceof Pawn) {
            return white ? "♙" : "♟";
        }

        return "";
    }
}

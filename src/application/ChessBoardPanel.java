
package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
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
    private static final Color SELECTED_SQUARE = new Color(246, 246, 105);
    private static final Color POSSIBLE_MOVE = new Color(120, 190, 100);

    private JButton[][] squares = new JButton[8][8];

    private ChessMatch chessMatch;

    private ChessPosition selectedPosition;
    private boolean[][] possibleMoves;

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

                square.setBackground(getSquareColor(row, col));
                square.setFont(new Font("Serif", Font.PLAIN, 42));
                square.setFocusPainted(false);
                square.setBorder(BorderFactory.createLineBorder(
                    new Color(80, 60, 40), 1
                ));

                final int selectedRow = row;
                final int selectedCol = col;

                square.addActionListener((ActionEvent e) -> {
                    handleSquareClick(selectedRow, selectedCol);
                });

                squares[row][col] = square;

                add(square);
            }
        }

        updateBoard();
    }

    private Color getSquareColor(int row, int col) {

        if ((row + col) % 2 == 0) {
            return LIGHT_SQUARE;
        }

        return DARK_SQUARE;
    }

    private void handleSquareClick(int row, int col) {

        ChessPosition clickedPosition =
            new ChessPosition((char) ('a' + col), 8 - row);

        // Se já existe uma peça selecionada, tenta movimentá-la.
        if (selectedPosition != null && possibleMoves[row][col]) {

            try {

                chessMatch.performChessMove(selectedPosition, clickedPosition);

                selectedPosition = null;
                possibleMoves = null;

                updateBoard();

            } catch (ChessException | IllegalStateException e) {

                JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Movimento inválido",
                    JOptionPane.WARNING_MESSAGE
                );

                selectedPosition = null;
                possibleMoves = null;

                updateBoard();
            }

            return;
        }

        // Tenta selecionar uma peça.
        try {

            possibleMoves = chessMatch.possibleMoves(clickedPosition);

            selectedPosition = clickedPosition;

            updateBoard();

        } catch (ChessException | IllegalStateException e) {

            selectedPosition = null;
            possibleMoves = null;

            updateBoard();
        }
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

                squares[row][col].setBackground(getSquareColor(row, col));

                if (possibleMoves != null && possibleMoves[row][col]) {
                    squares[row][col].setBackground(POSSIBLE_MOVE);
                }
            }
        }

        // Destaca a peça selecionada.
        if (selectedPosition != null) {

            int row = 8 - selectedPosition.getRow();
            int col = selectedPosition.getColumn() - 'a';

            squares[row][col].setBackground(SELECTED_SQUARE);
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

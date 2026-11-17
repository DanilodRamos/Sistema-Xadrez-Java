
package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import chess.ChessMatch;

public class ChessWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public ChessWindow(ChessMatch chessMatch) {

        setTitle("Xadrez Político");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 650));
        setSize(800, 850);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(35, 35, 35));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel(
            "XADREZ POLÍTICO",
            SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        mainPanel.add(title, BorderLayout.NORTH);

        // Tabuleiro gráfico com a partida atual
        ChessBoardPanel boardPanel = new ChessBoardPanel(chessMatch);

        mainPanel.add(boardPanel, BorderLayout.CENTER);

        JLabel footer = new JLabel(
            "Lula x Bolsonaro - Projeto Java do Danilo Ramos",
            SwingConstants.CENTER
        );

        footer.setForeground(Color.WHITE);
        footer.setFont(new Font("Arial", Font.PLAIN, 14));

        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }
}

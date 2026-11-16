
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
import javax.swing.SwingUtilities;

public class ChessWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    public ChessWindow() {

        // Configurações da janela
        setTitle("Xadrez Político");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 650));
        setSize(800, 850);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(35, 35, 35));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Título do jogo
        JLabel title = new JLabel("XADREZ POLÍTICO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        mainPanel.add(title, BorderLayout.NORTH);

        // Área provisória do tabuleiro
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(new Color(220, 220, 220));
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        
        JLabel message = new JLabel(
            "Nosso tabuleiro será criado na próxima etapa!",
            SwingConstants.CENTER
        );

        message.setFont(new Font("Arial", Font.BOLD, 18));
        boardPanel.add(message);

        mainPanel.add(boardPanel, BorderLayout.CENTER);

        // Rodapé
        JLabel footer = new JLabel(
            "Lula x Bolsonaro - Projeto Java",
            SwingConstants.CENTER
        );

        footer.setForeground(Color.WHITE);
        footer.setFont(new Font("Arial", Font.PLAIN, 14));

        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ChessWindow window = new ChessWindow();
            window.setVisible(true);
        });
    }
}

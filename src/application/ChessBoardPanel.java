
package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class ChessBoardPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color LIGHT_SQUARE = new Color(240, 217, 181);
    private static final Color DARK_SQUARE = new Color(181, 136, 99);

    public ChessBoardPanel() {

        // Cria um tabuleiro com 8 linhas e 8 colunas
        setLayout(new GridLayout(8, 8));

        // Define o tamanho preferencial do tabuleiro
        setPreferredSize(new Dimension(600, 600));

        // Cria as 64 casas
        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {

                JPanel square = new JPanel();

                // Alterna as cores das casas
                if ((row + col) % 2 == 0) {
                    square.setBackground(LIGHT_SQUARE);
                } else {
                    square.setBackground(DARK_SQUARE);
                }

                // Adiciona uma borda discreta
                square.setBorder(
                    BorderFactory.createLineBorder(
                        new Color(80, 60, 40), 1
                    )
                );

                // Adiciona a casa ao tabuleiro
                add(square);
            }
        }
    }
}

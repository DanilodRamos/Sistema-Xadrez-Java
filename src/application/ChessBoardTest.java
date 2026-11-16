
package application;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ChessBoardTest {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame window = new JFrame("Teste do Tabuleiro");

            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            window.add(new ChessBoardPanel());

            window.pack();

            window.setLocationRelativeTo(null);

            window.setVisible(true);
        });
    }
}

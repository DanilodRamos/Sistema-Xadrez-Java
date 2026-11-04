
package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {//rainha

    // Construtor da rainha.
    // O super passa o tabuleiro e a cor para a classe ChessPiece.
    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q"; // Representação da Rainha no tabuleiro.
    }

    @Override
    public boolean[][] possibleMovies() {

        // Cria uma matriz de movimentos possíveis.
        // Cada posição representa uma casa do tabuleiro.
        // true = movimento permitido.
        // false = movimento não permitido.
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // Objeto utilizado para percorrer as posições do tabuleiro.
        Position p = new Position(0, 0);

        // =====================================
        // MOVIMENTOS PARA CIMA
        // =====================================

        // Começa uma linha acima da posição atual da Torre.
        p.setValues(position.getRow() - 1, position.getColumn());

        // Enquanto a posição existir e estiver vazia,
        // marca a posição como um movimento possível.
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua subindo uma linha.
            p.setRow(p.getRow() - 1);
        }

        // Se encontrar uma peça adversária, permite capturá-la.
        // Não permite capturar peças da própria cor.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // =====================================
        // MOVIMENTOS PARA ESQUERDA
        // =====================================

        // Começa uma coluna à esquerda da Torre.
        p.setValues(position.getRow(), position.getColumn() - 1);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua andando para a esquerda.
            p.setColumn(p.getColumn() - 1);
        }

        // Se encontrar uma peça adversária, permite a captura.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // =====================================
        // MOVIMENTOS PARA DIREITA
        // =====================================

        // Começa uma coluna à direita da Torre.
        p.setValues(position.getRow(), position.getColumn() + 1);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua andando para a direita.
            p.setColumn(p.getColumn() + 1);
        }

        // IMPORTANTE:
        // Não colocar ponto e vírgula depois do if!
        // Se existir uma peça adversária, permite capturá-la.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // =====================================
        // MOVIMENTOS PARA BAIXO
        // =====================================

        // Começa uma linha abaixo da Torre.
        p.setValues(position.getRow() + 1, position.getColumn());

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua descendo uma linha.
            p.setRow(p.getRow() + 1);
        }

        // Se encontrar uma peça adversária, permite a captura.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }
     // =====================================
        // MOVIMENTOS PARA Noroeste
        // =====================================

        // Começa uma linha noroeste da posição atual do bispo.
        p.setValues(position.getRow() - 1, position.getColumn() - 1);

        // Enquanto a posição existir e estiver vazia,
        // marca a posição como um movimento possível.
        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua diagonal subindo uma linha.
            p.setValues(p.getRow() - 1,p.getColumn() - 1);
        }

        // Se encontrar uma peça adversária, permite capturá-la.
        // Não permite capturar peças da própria cor.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // =====================================
        // MOVIMENTOS PARA NORDESTE
        // =====================================

        // Começa uma coluna à NORDESTE dO BISPO.
        p.setValues(position.getRow() - 1, position.getColumn() + 1);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua andando para a NORDESTE.
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }

        // Se encontrar uma peça adversária, permite a captura.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // =====================================
        // MOVIMENTOS PARA SUDESTE
        // =====================================

        // Começa uma coluna à SUDESTE dO BISPO.
        p.setValues(position.getRow() + 1, position.getColumn() + 1);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua andando para a SUDESTE.
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }

        // IMPORTANTE:
        // Não colocar ponto e vírgula depois do if!
        // Se existir uma peça adversária, permite capturá-la.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // =====================================
        // MOVIMENTOS PARA SUDOESTE
        // =====================================

        // Começa uma linha abaixo da Torre.
        p.setValues(position.getRow() + 1, position.getColumn() - 1);

        while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {

            mat[p.getRow()][p.getColumn()] = true;

            // Continua descendo uma linha PARA SUDOESTE.
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }

        // Se encontrar uma peça adversária, permite a captura.
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // Retorna a matriz com todos os movimentos possíveis.
        return mat;
    }
}

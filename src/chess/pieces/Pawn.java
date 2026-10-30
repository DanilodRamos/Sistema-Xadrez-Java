package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);

	}

	@Override
	public boolean[][] possibleMovies() {
		//logica dos movimentos de um peão
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        // Objeto utilizado para percorrer as posições do tabuleiro.
        Position p = new Position(0, 0);
		
        
        //regra do peao branco
        
        if (getColor() == Color.WHITE) {
        	
        	p.setValues(position.getRow() - 1, position.getColumn());
        	if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
        		mat[p.getRow()][p.getColumn()] = true;
        	}
        	// Define uma nova posição no tabuleiro.
        	// Diminui 1 da linha e 1 da coluna da posição atual.
        	// Isso representa uma posição na diagonal superior esquerda.
        	p.setValues(position.getRow() - 2, position.getColumn());
        	Position p2 = new Position(position.getRow() - 1, position.getColumn());
        	if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)
        			&& getBoard().positionExists(p2)
        			&& !getBoard().thereIsAPiece(p2)
        			&& getMoveCount() == 0) {
        		mat[p.getRow()][p.getColumn()] = true;
        	}

        	// Verifica duas condições:
        	// 1. Se a posição está dentro dos limites do tabuleiro.
        	// 2. Se existe uma peça adversária nessa posição.
        	p.setValues(position.getRow() - 1, position.getColumn() - 1);
        	if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        		  // Marca a posição como movimento possível.
        		mat[p.getRow()][p.getColumn()] = true;
        	}// Verifica se a posição existe e está vazia.
        	p.setValues(position.getRow() - 1, position.getColumn() + 1);
        	if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        		mat[p.getRow()][p.getColumn()] = true;
        	}
        	
        }
        else { // PEÃO PRETO: movimenta-se para baixo.
            // Aumenta 1 na linha e mantém a mesma coluna.
         	p.setValues(position.getRow() + 1, position.getColumn());
        	if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
        		mat[p.getRow()][p.getColumn()] = true;
        	}
        	// Define uma nova posição no tabuleiro.
        	// Diminui 1 da linha e 1 da coluna da posição atual.
        	// Isso representa uma posição na diagonal superior esquerda.
        	p.setValues(position.getRow() + 2, position.getColumn());
        	Position p2 = new Position(position.getRow() + 1, position.getColumn());
        	if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)
        			&& getBoard().positionExists(p2)
        			&& !getBoard().thereIsAPiece(p2)
        			&& getMoveCount() == 0) {
        		   // Marca a posição como possível captura.
        		mat[p.getRow()][p.getColumn()] = true;
        	}

        	// Verifica duas condições:
        	// 1. Se a posição está dentro dos limites do tabuleiro.
        	// 2. Se existe uma peça adversária nessa posição.
        	p.setValues(position.getRow() + 1, position.getColumn() - 1);
        	if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        		   // Marca a posição como possível captura.
        		mat[p.getRow()][p.getColumn()] = true;
        	}
        	p.setValues(position.getRow() + 1, position.getColumn() + 1);
        	if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        		   // Marca a posição como possível captura.
        		mat[p.getRow()][p.getColumn()] = true;
        	}
        }
        // Retorna a matriz preenchida com os movimentos possíveis.
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
}

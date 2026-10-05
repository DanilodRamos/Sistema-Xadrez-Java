package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
	//construtor passando chamada para superclasse
	public Rook(Board board, Color color) {
		super(board, color);
		
	}
	@Override
	public String toString() {
		return "R";//convertendo a peça para String
	}
	

}	

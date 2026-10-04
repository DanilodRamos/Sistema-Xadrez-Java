package chess;

import boardgame.Board;
import boardgame.Piece;

public class ChessPiece extends Piece {// subclasse de peça
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color; //nao q modifica a cor tira o set
	}


	
}

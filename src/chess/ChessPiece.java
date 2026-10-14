package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public abstract class ChessPiece extends Piece {// subclasse de peça
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color; // nao q modifica a cor tira o set
	}

	// assecivel mesmo pacote e subclasse
	protected boolean isThereOpponentPiece(Position position) {
		// vereficar se existe uma peça adversaria na posicao
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		// testar p diferente de null e p.get color diferente da cor que estou
		return p != null && p.getColor() != color;
	}

}

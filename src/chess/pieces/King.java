package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

//pessa rei
public class King extends ChessPiece {
	
	//dependencia para partida
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch)
	{//referencia para partida no construtor chessmatch
		super(board, color);
		this.chessMatch = chessMatch;//associando os dois
	}

	@Override
	public String toString() {
		return "K";// mudando para to string e retornando o k de rei
	}

	// possiveis movimentos do rei para uma determinada posicao
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	//metodo auxiliar para testar posicao do roque 
	private boolean testRookCastling(Position position) {
		//testar se a torre esta apto para o roque
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor()
				== getColor() && p.getMoveCount() == 0;
	}
	@Override
	public boolean[][] possibleMovies() {// possivel movimento do rei
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);

		// acima
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// edireita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// noroeste
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// nordeste
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		// sudoeste
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// sudeste
				p.setValues(position.getRow() + 1, position.getColumn() + 1);
				if (getBoard().positionExists(p) && canMove(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				//especial move castling roque pequeno
				if (getMoveCount() == 0 && !chessMatch.getCheck()) {
					//especial move castling kingside rook roque pequeno
					Position postT1 = new Position(position.getRow(), position.getColumn() + 3);
					if(testRookCastling(postT1)) {
						Position p1 = new Position(position.getRow(), position.getColumn() + 1);
						Position p2 = new Position(position.getRow(), position.getColumn() + 2);
						if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
							mat[position.getRow()][position.getColumn() + 2] = true;
						}
					}
						//especial move castling queenside rook roque pequeno
						Position postT2 = new Position(position.getRow(), position.getColumn() - 4);
						if(testRookCastling(postT2)) {
							Position p1 = new Position(position.getRow(), position.getColumn() - 1);
							Position p2 = new Position(position.getRow(), position.getColumn() - 2);
							Position p3 = new Position(position.getRow(), position.getColumn() - 3);
							if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
								mat[position.getRow()][position.getColumn() - 2] = true;
							}
						}
				}
				
		return mat;
	}
}

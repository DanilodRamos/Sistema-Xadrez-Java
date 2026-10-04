package boardgame;

public class Board {
	private int row;//linhas
	private int columns; //colunas
	private Piece[][] pieces;// matriz de peça
	
	public Board(int rows, int columns) {
		
		this.row = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}
	
	
	
}

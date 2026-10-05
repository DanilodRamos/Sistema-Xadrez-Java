package boardgame;

public class Board {
	private int rows;// linhas
	private int columns; // colunas
	private Piece[][] pieces;// matriz de peça

	public Board(int rows, int columns) {

		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int row) {
		this.rows = row;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	//sobrecarga
	public Piece piece(Position position) {//retorna na posiçao
		return pieces[position.getRow()][position.getColumn()];
	}
	//colocando peças metodo recebendo peça e posicion
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}//estou pegando a matriz na posicao dada e atribuindo as pécas
}

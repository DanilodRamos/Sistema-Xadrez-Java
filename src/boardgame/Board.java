package boardgame;

public class Board {
	private int rows;// linhas
	private int columns; // colunas
	private Piece[][] pieces;// matriz de peça

	public Board(int rows, int columns) {
		// programaçao defensiva
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	
	public int getColumns() {
		return columns;
	}
 //foi retirado setRows e set Columns programaçao defensiva para impedir de inserir no tabuleiro

	public Piece piece(int row, int column) {
		//programacao defensiva aki tbem 
		if(!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	// sobrecarga
	public Piece piece(Position position) {
		//programacao defensiva aki tbem 
				if(!positionExists(position)) {
					throw new BoardException("Position not on the board");
				}
		
		// retorna na posiçao
		return pieces[position.getRow()][position.getColumn()];
	}

	// colocando peças metodo recebendo peça e posicion
	public void placePiece(Piece piece, Position position) {
		//testando se existe a peça na posicao
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}// estou pegando a matriz na posicao dada e atribuindo as pécas

	
	//metodo para remover peças
	public Piece removePiece(Position position) {
		if(!positionExists(position)) {//caso nao existe excessao
			throw new BoardException("Position not on the board");
		}
		if(piece(position) == null){
			return null;
		}
		//proceddimento retirar a peca do tabuleiro
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	// metodo auxiliar
	public boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
		// quando a posicao esta dentro do tabuleiro ela e maior ou igual a 0 e menor
		// que altura do tabeleiro e coluna maior ou igual a 0 e menor q a coluna do
		// tabuleiro

	}

	// ver se existe a peça
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	// implementando o metodo pra ve se tem a peça na posicao

	public boolean thereIsAPiece(Position position) {
		//programacao defensiva aki tbem 
		if(!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return piece(position) != null;
	}
}

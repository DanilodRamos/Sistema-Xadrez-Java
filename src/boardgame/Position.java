package boardgame;

public class Position {
	// atributos
	private int row;
	private int column;
	//construtor com argumento
	public Position(int row, int column) {
	
		this.row = row;
		this.column = column;
	}
	//gets e sets
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	//to string
	@Override
	public String toString() {
		return row + ", " + column;//imprimir uma posicao na tela
	}

}

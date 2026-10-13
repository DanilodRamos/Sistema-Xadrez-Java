package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;

	public Piece(Board board) {

		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}

	// metodo para mover as peças
	public abstract boolean[][] possibleMovies();

	public boolean possibleMove(Position position) {// metodo utilizando metodo abstrato
		return possibleMovies()[position.getRow()][position.getColumn()];
	}// metodo chamando implementacao subclasse concreta
//operaçao q chama o metodo pra voltar a matriz

	public boolean isTheAnyPossibleMove() {
		boolean[][] mat = possibleMovies();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}

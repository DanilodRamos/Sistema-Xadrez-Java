package chess;// Define que esta classe pertence ao pacote chess.
//Importa a classe Board, que representa o tabuleiro.
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
//Importa a classe Piece, que é a classe pai de ChessPiece.
//Classe abstrata: serve como modelo para as peças do xadrez.
//Herda os atributos e métodos da classe Piece.
public abstract class ChessPiece extends Piece {// subclasse de peça
	private Color color;    // Guarda a cor da peça (WHITE ou BLACK).
	private int moveCount; 
    // Guarda quantos movimentos a peça realizou.
    // Construtor da classe ChessPiece.
    // Recebe o tabuleiro e a cor da peça.
	public ChessPiece(Board board, Color color) {
		  // Chama o construtor da classe pai (Piece).
        // Passa o tabuleiro para a classe herdada.
		super(board);
		// Guarda a cor recebida dentro do atributo color.
        // this.color representa o atributo da própria classe.
        // color representa o parâmetro recebido pelo construtor.
		this.color = color;
	}

	public Color getColor() {
		return color; // nao q modifica a cor tira o set
	}
	// Método que retorna a quantidade de movimentos que a peça realizou.
	// Não altera o contador, apenas consulta seu valor.
	public int getMoveCount() {
		return moveCount;
	}
	// Método responsável por aumentar em 1 o contador de movimentos.
	// O operador ++ acrescenta 1 ao valor atual.
	public void increaseMoveCount() {
		moveCount++;
	}
	// Método responsável por diminuir em 1 o contador de movimentos.
	// O operador -- subtrai 1 do valor atual.
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	//logica de check
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	// assecivel mesmo pacote e subclasse
	protected boolean isThereOpponentPiece(Position position) {
		// vereficar se existe uma peça adversaria na posicao
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		// testar p diferente de null e p.get color diferente da cor que estou
		return p != null && p.getColor() != color;
	}

}

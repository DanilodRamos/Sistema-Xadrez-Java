
package chess; // Classe responsável pelas regras da partida de xadrez.

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {

	// Número do turno da partida.
	private int turn;

	// Jogador que está jogando atualmente.
	private Color currentPlayer;

	// Tabuleiro de xadrez.
	private Board board;

	// Indica se o rei está em xeque.
	private boolean check;

	// cheque mate;
	private boolean checkMate;

	// Lista de todas as peças que estão no tabuleiro.
	private List<Piece> piecesOnTheBoard = new ArrayList<>();

	// Lista das peças que foram capturadas.
	private List<Piece> capturedPieces = new ArrayList<>();

	// Construtor da partida.
	public ChessMatch() {

		board = new Board(8, 8); // Cria um tabuleiro de 8 linhas e 8 colunas.

		turn = 1; // A partida começa no primeiro turno.

		currentPlayer = Color.WHITE; // As brancas começam jogando.

		check = false; // Inicialmente ninguém está em xeque.

		initialSetup(); // Coloca as peças no tabuleiro.
	}

	// Retorna o número do turno.
	public int getTurn() {
		return turn;
	}

	// Retorna o jogador atual.
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	// Informa se existe xeque.
	public boolean getCheck() {
		return check;
	}

	// Informa se existe xequeMate.
	public boolean getCheckMate() {
		return checkMate;
	}

	// Retorna uma matriz contendo as peças do tabuleiro.
	public ChessPiece[][] getPieces() {

		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

		// Percorre todas as linhas e colunas.
		for (int i = 0; i < board.getRows(); i++) {

			for (int j = 0; j < board.getColumns(); j++) {

				// Obtém a peça daquela posição.
				// O cast converte Piece para ChessPiece.
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return mat;
	}

	// Verifica os movimentos possíveis de uma peça.
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {

		Position position = sourcePosition.toPosition();

		// Verifica se existe uma peça válida na origem.
		validateSourcePosition(position);

		// Retorna a matriz de movimentos possíveis da peça.
		return board.piece(position).possibleMovies();
	}

	// Executa uma jogada completa.
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		// Verifica se a posição de origem é válida.
		validateSourcePosition(source);

		// Verifica se a peça pode ir para o destino.
		validateTargetPosition(source, target);

		// Executa o movimento e guarda uma possível peça capturada.
		Piece capturedPiece = makeMove(source, target);

		// Verifica se o jogador deixou o próprio rei em xeque.
		if (testCheck(currentPlayer)) {

			// Se deixou o próprio rei em xeque, desfaz a jogada.
			undoMove(source, target, capturedPiece);

			throw new ChessException("You can't put yourself in check");
		}

		// Verifica se o adversário ficou em xeque.
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;

		}
		// Passa a vez para o próximo jogador.
		else {
			nextTurn();
		}

		// Retorna a peça capturada, se houver.
		return (ChessPiece) capturedPiece;
	}

	// =====================================
	// FAZER MOVIMENTO
	// =====================================

	private Piece makeMove(Position source, Position target) {

		// Remove a peça da posição de origem.
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		// Remove a peça que estiver no destino, se existir.
		Piece capturedPiece = board.removePiece(target);

		// Coloca a peça movimentada na posição de destino.
		board.placePiece(p, target);

		// Verifica se aconteceu uma captura.
		if (capturedPiece != null) {

			// Retira a peça capturada da lista do tabuleiro.
			piecesOnTheBoard.remove(capturedPiece);

			// Adiciona a peça à lista de capturadas.
			capturedPieces.add(capturedPiece);
		}

		return capturedPiece;
	}

	// =====================================
	// DESFAZER MOVIMENTO
	// =====================================

	private void undoMove(Position source, Position target, Piece capturedPiece) {

		// Remove a peça que está na posição de destino (target) do tabuleiro.
		// Ou seja, retira a peça que será capturada durante a jogada.
		ChessPiece p = (ChessPiece) board.removePiece(target);

		// Diminui em 1 a quantidade de movimentos da peça capturada.
		// Isso é necessário porque a peça está sendo retirada do tabuleiro
		// e não deve mais ser considerada como uma peça em jogo.
		p.decreaseMoveCount();

		// CORREÇÃO:
		// Devolve a peça para sua posição original (source).
		// Antes estava sendo colocada novamente em target.
		board.placePiece(p, source);

		// Se uma peça foi capturada, precisamos recuperá-la.
		if (capturedPiece != null) {

			// Devolve a peça capturada para o tabuleiro.
			board.placePiece(capturedPiece, target);

			// Remove a peça da lista de capturadas.
			capturedPieces.remove(capturedPiece);

			// Adiciona novamente à lista de peças do tabuleiro.
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	// =====================================
	// VALIDAR POSIÇÃO DE ORIGEM
	// =====================================

	private void validateSourcePosition(Position position) {

		// Verifica se existe alguma peça na posição escolhida.
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}

		// Verifica se a peça pertence ao jogador atual.
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}

		// Verifica se a peça possui algum movimento possível.
		if (!board.piece(position).isTheAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	// =====================================
	// VALIDAR POSIÇÃO DE DESTINO
	// =====================================

	private void validateTargetPosition(Position source, Position target) {

		// Verifica se o movimento escolhido é permitido.
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position");
		}
	}

	// =====================================
	// TROCAR TURNO
	// =====================================

	private void nextTurn() {

		// Incrementa o número do turno.
		turn++;

		// Alterna entre jogador branco e preto.
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// =====================================
	// IDENTIFICAR O ADVERSÁRIO
	// =====================================

	private Color opponent(Color color) {

		// Se for branco, retorna preto.
		// Se for preto, retorna branco.
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// =====================================
	// LOCALIZAR O REI
	// =====================================

	private ChessPiece king(Color color) {

		// Filtra as peças que pertencem à cor informada.
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		// Percorre a lista procurando uma peça da classe King.
		for (Piece p : list) {

			if (p instanceof King) {

				// Retorna o rei encontrado.
				return (ChessPiece) p;
			}
		}

		// Se não encontrar o rei, lança uma exceção.
		throw new IllegalStateException("There is no " + color + " King on the board");
	}

	// =====================================
	// TESTAR XEQUE
	// =====================================

	private boolean testCheck(Color color) {

		// Localiza o rei da cor recebida.
		// Obtém sua posição no tabuleiro.
		Position kingPosition = king(color).getChessPosition().toPosition();

		// Cria uma lista contendo somente as peças adversárias.
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());

		// Percorre todas as peças do adversário.
		for (Piece p : opponentPieces) {

			// Obtém os movimentos possíveis da peça adversária.
			boolean[][] mat = p.possibleMovies();

			// Verifica se algum movimento alcança a posição do rei.
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {

				// Se alcançar, significa que o rei está em xeque.
				return true;
			}
		}

		// Nenhuma peça adversária ameaça o rei.
		return false;
	}

	// Método responsável por verificar se o jogador está em xeque-mate.
	// Recebe a cor do jogador que está sendo analisado.
	// Retorna true se for xeque-mate e false se não for.
	private boolean testCheckMate(Color color) {

		// Primeiro verifica se o jogador está em xeque.
		// Se NÃO estiver em xeque, não pode ser xeque-mate.
		if (!testCheck(color)) {

			// Retorna false porque não existe xeque-mate.
			return false;
		}

		// Cria uma lista contendo as peças do adversário.
		// piecesOnTheBoard.stream() percorre todas as peças do tabuleiro.
		List<Piece> list = piecesOnTheBoard.stream()

				// filter() filtra somente as peças que pertencem ao adversário.
				// opponent(color) retorna a cor contrária à do jogador analisado.
				.filter(x -> ((ChessPiece) x).getColor() == color)

				// collect() reúne as peças filtradas em uma nova lista.
				// toList() transforma o resultado em uma lista.
				.collect(Collectors.toList());

		// Percorre uma por uma as peças do adversário.
		for (Piece p : list) {

			// Cria uma matriz de posições possíveis para a peça atual.
			// true significa que a peça pode tentar se movimentar naquela posição.
			boolean[][] mat = p.possibleMovies();

			// Percorre todas as linhas do tabuleiro.
			// board.getRows() informa quantas linhas existem.
			for (int i = 0; i < board.getRows(); i++) {

				// Percorre todas as colunas do tabuleiro.
				// board.getColumns() informa quantas colunas existem.
				for (int j = 0; j < board.getColumns(); j++) {

					// Verifica se a posição atual é um movimento possível.
					// Se for true, significa que podemos testar essa jogada.
					if (mat[i][j]) {

						// Obtém a posição atual da peça no tabuleiro.
						// getChessPosition() retorna a posição no padrão do xadrez.
						// toPosition() converte para as coordenadas da matriz.
						Position source = ((ChessPiece) p).getChessPosition().toPosition();

						// Cria a posição de destino da jogada.
						// i representa a linha e j representa a coluna.
						Position target = new Position(i, j);

						// Executa temporariamente o movimento da peça.
						// Guarda a peça capturada, caso exista.
						// Precisamos guardar essa peça para desfazer a jogada depois.
						Piece capturedPiece = makeMove(source, target);

						// Depois da jogada, verifica se o jogador analisado
						// continua em xeque.
						boolean testCheck = testCheck(color);

						// Desfaz o movimento temporário.
						// Retorna a peça para sua posição original.
						// Também devolve ao tabuleiro a peça que foi capturada.
						undoMove(source, target, capturedPiece);

						// Verifica se a jogada conseguiu tirar o jogador do xeque.
						// Se NÃO estiver mais em xeque, existe uma saída.
						if (!testCheck) {

							// Portanto, não é xeque-mate.
							// Basta uma jogada válida para escapar do xeque.
							return false;
						}
					}
				}
			}
		}

		// Se chegou até aqui, significa que:
		// 1. O jogador estava em xeque.
		// 2. Todas as jogadas possíveis foram testadas.
		// 3. Nenhuma jogada conseguiu tirar o jogador do xeque.
		// Portanto, é xeque-mate!
		return true;
	}

	// =====================================
	// COLOCAR PEÇAS NO TABULEIRO
	// =====================================

	private void placeNewPiece(char column, int row, ChessPiece piece) {

		// Converte a posição do xadrez para posição da matriz.
		// Coloca a peça no tabuleiro.
		board.placePiece(piece, new ChessPosition(column, row).toPosition());

		// Adiciona a peça à lista de peças existentes.
		piecesOnTheBoard.add(piece);
	}

	// =====================================
	// CONFIGURAÇÃO INICIAL DA PARTIDA
	// =====================================

	private void initialSetup() {
		//peças  brancas
		 placeNewPiece('a', 1, new Rook(board, Color.WHITE));
	        placeNewPiece('e', 1, new King(board, Color.WHITE));
	        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
	        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
	        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
	        //peças pretas
	        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('e', 8, new King(board, Color.BLACK));
	        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
	        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
	        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));

	}
}

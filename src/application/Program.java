package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {// chamando a classe
		ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPieces());
	}

}

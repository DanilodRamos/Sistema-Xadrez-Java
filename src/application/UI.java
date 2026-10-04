package application;

import chess.ChessPiece;

public class UI {// metodo
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i=0; i<pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);
			}//quebra de linha
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// metodo auxiliar imprimir uma peca
	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
			System.out.print("-");
		} else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}

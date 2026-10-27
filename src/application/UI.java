package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// implementando as cores
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// codigo especiais de cores pra imprimir nos console
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() { // metodo pra limpar a tela
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// metodo pra ler uma posicao do usuario
	public static ChessPosition readChessPosition(Scanner sc) {

		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition.Valid values are from a1 to h8");
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {// monstro o tabuleiro normal
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turn : " + chessMatch.getTurn());// turno tal
		if (!chessMatch.getCheckMate()) {
			System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());// aguardando jogador tal
			if (chessMatch.getCheck()) {
				System.out.println("CHECK!");
			}
		} else {
			System.out.println("CHECKMATE!S2");
			System.out.println("Winner: " + chessMatch.getCurrentPlayer());
		}
	}

	// metodo
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);
			} // quebra de linha
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// metodo
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			} // quebra de linha
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// metodo imprimir uma peca e testar a cor
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);// colorir fundo azul
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	// imprimir e implementar a logica das peça capturadas
	private static void printCapturedPieces(List<ChessPiece> captured) {
		// filtrando na minha lista todo mundo cuja cor e branca lambda
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		// mesma coisa filtrando as petras agora
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());
		// logica pra imprimir
		System.out.println("Captured pieces: ");
		System.out.print("White : ");
		System.out.print(ANSI_WHITE);
		// jeitinho de imprimir um array padrao no java
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Black : ");
		System.out.print(ANSI_YELLOW);
		// jeitinho de imprimir um array padrao no java
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}

}

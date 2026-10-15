package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		// chamando o scanner pra digitar
		Scanner sc = new Scanner(System.in);
		// chamando a classe
		ChessMatch chessMatch = new ChessMatch();

		while (true) {
			try {
				UI.clearScreen();// limpar a tela toda vez q voltar;
				UI.printBoard(chessMatch.getPieces());// imprimindo tabuleiro na tela
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();//limpar a tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves);//imprimir o tabuleiro e colocar as cores onde pode mover
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
			} 
			catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} 
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}

	}

}

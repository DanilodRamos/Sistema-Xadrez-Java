package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		//declarando lista de peças na partida
		List<ChessPiece> captured = new ArrayList<>();

		while (true) {
			try {
				UI.clearScreen();// limpar a tela toda vez q voltar;
				UI.printMatch(chessMatch, captured);// imprimindo tabuleiro na tela
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();//limpar a tela
				UI.printBoard(chessMatch.getPieces(), possibleMoves);//imprimir o tabuleiro e colocar as cores onde pode mover
				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				//sempre q executar movimento e retornar capturada
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				//testo se capturada for diferente de null ela foi capturada dae acrescento na lista
				if(capturedPiece!= null) {
					captured.add(capturedPiece);//adicionando
				}
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

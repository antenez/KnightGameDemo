package knightsdemo;

import java.util.Scanner;

import knightsdemo.interfaces.KnightsDemoGame;

public class MainClassSample {

	public static void main(String[] args) {
		KnightsDemoGame kdg = new KnightsDemoGameImpl("player1", "player2");
		Scanner in = new Scanner(System.in);
		int column;
		int row;
		while (!kdg.isGameOver()) {
			System.out.println("Player " + kdg.getCurrentPlayerName() + " chose move");
			kdg.printValidMoveProposals();

			System.out.println("Enter row :");
			row = in.nextInt();
			System.out.println("Enter column :");
			column = in.nextInt();

			try {
				kdg.playNextMove(column, row);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		in.close();
	}

}

package org.fs.bingo;

import java.util.Scanner;

public class Main {

	public static final boolean DEBUG = false;

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		print("**** Lets Play Housie *****");
		print("");
		print("Note: - Press 'Q'; to quit any time.");

		print(">>Enter the number range(1-n): ");
		int range = scanner.nextInt();

		print(">>Enter Number of players playing the game: ");
		int players = scanner.nextInt();

		print(">>Enter Ticket Size : Default to 3X10");
		final String s1 = scanner.next(); //Use pattern?
		String[] s = s1.split("X"); //TODO: add validation
		int ticketRow = Integer.parseInt(s[0]);
		int ticketCol = Integer.parseInt(s[1]);

		print(">>Enter numbers per row. Default to 5");
		int numPerRow = scanner.nextInt();

		Parameters params = new Parameters.Builder()
						.withRange(range)
						.withPlayers(players)
						.withTicketRow(ticketRow)
						.withTicketCol(ticketCol)
						.withNumPerRow(numPerRow)
						.build();

		if (DEBUG)
			print(params.toString());

		print("***Ticket Created Successfully ****");

		BingoFactory factory = new BingoFactory(params);
		Bingo bingo = factory.create();

		print(">> Press 'N' to generate next number");

		boolean gameOver = false;
		while(!gameOver){
			String input = scanner.next();
			if (input.equalsIgnoreCase("n"))
				gameOver = bingo.play();
		}

	}

	/**
	 * For readability
	 */
	private static void print(String s) {
		System.out.println(s);
	}
}
package org.fs.bingo;

import java.util.Scanner;

public class Main {

	public static final boolean DEBUG = true;
	private static final Scanner scanner = new Scanner(System.in);
	private static final String QUIT_CHAR = "Q";

	public static void main(String[] args){
		printHeader();

		Parameters.Builder builder = new Parameters.Builder();

		String range = collectInput(">>Enter the number range(1-n): ");
		if (!range.isEmpty())
			builder.withRange(Integer.parseInt(range));

		String players = collectInput(">>Enter Number of players playing the game: ");
		if (!players.isEmpty())
			builder.withPlayers(Integer.parseInt(players));

		String dimensions = collectInput(">>Enter Ticket Size : Default to 3X10");
		if (!dimensions.isEmpty()){
			String[] s = dimensions.split("X"); //TODO: add validation
			builder.withTicketRow(Integer.parseInt(s[0]));
			builder.withTicketCol(Integer.parseInt(s[1]));
		}

		String numPerRow = collectInput(">>Enter numbers per row. Default to 5");
		if (!numPerRow.isEmpty())
			builder.withNumPerRow(Integer.parseInt(numPerRow));

		Parameters params = builder.build();

		if (DEBUG)
			print(params.toString());

		BingoFactory factory = new BingoFactory(params);
		Bingo bingo = factory.create();

		print("***Ticket Created Successfully ****");

		print(">> Press 'N' to generate next number");
		boolean gameOver = false;
		while(!gameOver){
			String input = collectInput("");
			if (input.equalsIgnoreCase("n"))
				gameOver = bingo.play();
		}
	}

	private static String collectInput(String message) {
		if (!message.isEmpty())
			print(message);

		String input = scanner.nextLine();
		if (QUIT_CHAR.equalsIgnoreCase(input))
			System.exit(0);

		return input;
	}

	private static void printHeader() {
		print("**** Lets Play Housie *****");
		print("");
		print("Note: - Press 'Q'; to quit any time.");
	}

	/**
	 * For readability
	 */
	private static void print(String s) {
		System.out.println(s);
	}
}
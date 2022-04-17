package org.fs.bingo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Orchestrates a game of bingo, with the following steps
 * -Dealer calls a cue (number)
 * -Players mark and check their tickets
 * -Winners are announced and stored
 * -Game is over when a player has 'Full House'
 *
 *
 * Notes:
 * -Assumptions - ticket is reasonably small N x M matrix.
 * -If larger N x M we may reconsider checkers to do a single iteration.
 * -Opted for readability, instead of performance
 * -is a sparse matrix necessary? We can find winners w/ condensed matrix
 */
public class Bingo {

	private final Dealer dealer;
	private final Collection<Player> players;
	private final Map<WinType, Player> winnerMap;

	public Bingo(Dealer dealer, Collection<Player> players) {
		this.dealer = dealer;
		this.players = players;
		this.winnerMap = new HashMap<>();
	}

	/**
	 * Play a round of bingo, where a dealer selects a number and players mark their tickets
	 * @return true if the game is over, false otherwise
	 */
	public boolean play() {
		//all wins accounted for, return gameover
		if (selectWinTypes().isEmpty())
			return true;

		Cue cue = dealer.deal();

		for (Player player : players) {
			Collection<WinType> availableWins = selectWinTypes();
			Collection<WinType> playerWins = player.play(cue, availableWins);

			//no wins check next player
			if (playerWins.isEmpty())
				continue;

			for (WinType win : playerWins){
				printWinner(player, win);

				winnerMap.put(win, player);

				if (WinType.FULL_HOUSE == win) {
					printSummary();
					return true;
				}
			}
		}

		return false;
	}

	private Collection<WinType> selectWinTypes() {
		Collection<WinType> winners = new ArrayList<>();

		for (WinType win: WinType.values()){
			if (!winnerMap.containsKey(win)){
				winners.add(win);
			}
		}

		return winners;
	}

	private void printSummary() {
		System.out.println("*** Game over ***");
		System.out.println("======================");
		System.out.println("       Summary       ");

		for(Player player : players){
			player.printSummary();
		}

		System.out.println("======================");
	}

	private void printWinner(Player player, WinType win){
		System.out.printf("We have a winner: %s has won '%s' winning combination\n", player.getName(), win);
	}
}

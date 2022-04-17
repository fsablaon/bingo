package org.fs.bingo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Player who checks ticket for winners
 */
public class Player {

	public static final String PLAYER_NAME_PREFIX = "Player#";

	private final String name;
	private final Ticket ticket;
	private final Collection<Checker> checkers;
	private final Collection<WinType> wins;

	public Player(String name, Ticket ticket, Collection<Checker> checkers){
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Player name cannot be null or blank");

		if (ticket == null)
			throw new IllegalArgumentException("Ticket cannot be null");

		if (checkers == null || checkers.isEmpty())
			throw new IllegalArgumentException("Checker cannot be null or empty");

		this.name = name;
		this.ticket = ticket;
		this.checkers = checkers;
		this.wins = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public Collection<WinType> getWins(){
		return Collections.unmodifiableCollection(wins);
	}

	/**
	 * Plays a round of bingo. Marks the ticket with the called number (cue). Then checks ticket for available win types.
	 * @param cue
	 * @param availableWins
	 * @return Collection of wins for the current round. Does **NOT** return previous wins
	 */
	public Collection<WinType> play(Cue cue, Collection<WinType> availableWins) {
		ticket.mark(cue);
		Collection<WinType> roundWins = new ArrayList<>();
		for (Checker c : checkers) {
			if (availableWins.contains(c.getWinType())){
				final Optional<WinType> win = c.win(ticket);
				win.ifPresent(roundWins::add);
				win.ifPresent(wins::add);
			}
		}

		return Collections.unmodifiableCollection(roundWins);
	}

	/**
	 * Prints a players game summary
	 * @return player summary of game
	 */
	public String printSummary() {
		StringBuilder summary = new StringBuilder();
		summary.append(name);
		summary.append(": ");

		if (wins.isEmpty()) {
			summary.append("Nothing");
		} else {
			Collection<String> winStrings = wins.stream().map(WinType::getDisplayName).collect(Collectors.toList());
			summary.append(String.join(" and ", winStrings));
		}

		System.out.println(summary);
		return summary.toString();
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Player.class.getSimpleName() + "[", "]")
				.add("name='" + name + "'")
				.add("ticket=" + ticket)
				.add("checkers=" + checkers)
				.add("wins=" + wins)
				.toString();
	}

}

package org.fs.bingo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Factory to create Bingo
 */
public class BingoFactory {

	private final Parameters params;
	private final Dealer dealer;
	private final TicketGenerator ticketGenerator;

	public BingoFactory(Parameters params) {
		this(params, new BingoDealer(params), new TicketGenerator(params));
	}

	public BingoFactory(Parameters params, Dealer dealer, TicketGenerator ticketGenerator) {
		if (params == null)
			throw new IllegalArgumentException("BingoFactory parameters cannot be null");

		if (dealer == null)
			throw new IllegalArgumentException("BingoFactory's dealer cannot be null");

		if (ticketGenerator == null)
			throw new IllegalArgumentException("BingoFactory's ticket generator cannot be null");

		this.params = params;
		this.dealer = dealer;
		this.ticketGenerator = ticketGenerator;
	}

	public Bingo create() {
		Collection<Player> players = createPlayers();
		return new Bingo(dealer, players);
	}

	private Collection<Checker> createCheckers() {
		return Arrays.asList(new EarlyFiveChecker(), new TopLineChecker(), new FullHouseChecker());
	}

	private Collection<Player> createPlayers() {
		Collection<Player> players = new ArrayList<>();
		for (int i = 1; i <= params.getPlayers(); ++i) {
			Ticket ticket = ticketGenerator.createTicket();

			if (Main.DEBUG)
				System.out.println(ticket);

			players.add(new Player(Player.PLAYER_NAME_PREFIX + i, ticket, createCheckers()));
		}

		//note: can use guava immutable list
		return Collections.unmodifiableCollection(players);
	}

}

package org.fs.bingo;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerTest {

	private Player player;
	private Cue cue = new Cue(10);

	@Test
	public void testNoWin(){
		Ticket ticket = mock(Ticket.class);
		Checker noWinChecker = mock(Checker.class);

		when(noWinChecker.getWinType()).thenReturn(WinType.FULL_HOUSE);
		when(noWinChecker.win(ticket)).thenReturn(Optional.empty());

		player = new Player("Player#1", ticket, Collections.singletonList(noWinChecker));
		Collection<WinType> wins = player.play(cue, Collections.singletonList(WinType.FULL_HOUSE));
		String summary = player.printSummary();

		assertThat(wins, empty());
		assertThat(summary, is("Player#1: Nothing"));

		verify(ticket).mark(cue);
		verify(noWinChecker).win(ticket);
	}

	@Test
	public void testWin(){
		Ticket ticket = mock(Ticket.class);
		Checker winChecker = mock(Checker.class);

		when(winChecker.getWinType()).thenReturn(WinType.FULL_HOUSE);
		when(winChecker.win(ticket)).thenReturn(Optional.of(WinType.FULL_HOUSE));

		player = new Player("Player#1", ticket, Collections.singletonList(winChecker));
		Collection<WinType> wins = player.play(cue, Collections.singletonList(WinType.FULL_HOUSE));
		String summary = player.printSummary();

		assertThat(wins.size(), is(1));
		assertThat(summary, is("Player#1: Full House"));
		assertThat(wins.stream().findFirst().orElse(null), is(WinType.FULL_HOUSE));

		verify(ticket).mark(cue);
		verify(winChecker).win(ticket);
	}

	@Test
	public void testMultipleWin(){
		Ticket ticket = mock(Ticket.class);
		Checker fullHouseChecker = mock(Checker.class);
		Checker firstFiveChecker = mock(Checker.class);

		when(fullHouseChecker.getWinType()).thenReturn(WinType.FULL_HOUSE);
		when(fullHouseChecker.win(ticket)).thenReturn(Optional.of(WinType.FULL_HOUSE));
		when(firstFiveChecker.getWinType()).thenReturn(WinType.EARLY_FIVE);
		when(firstFiveChecker.win(ticket)).thenReturn(Optional.of(WinType.EARLY_FIVE));

		player = new Player("Player#1", ticket, Arrays.asList(fullHouseChecker, firstFiveChecker));
		Collection<WinType> wins = player.play(cue, Arrays.asList(WinType.FULL_HOUSE, WinType.EARLY_FIVE));
		String summary = player.printSummary();

		assertThat(wins.size(), is(2));
		assertThat(wins, Matchers.contains(WinType.FULL_HOUSE, WinType.EARLY_FIVE));

		//Order of wins are arbitrary
		assertThat(summary, containsString("Player#1"));
		assertThat(summary, containsString("Early Five"));
		assertThat(summary, containsString(" and "));
		assertThat(summary, containsString("Full House"));

		verify(ticket).mark(cue);
		verify(fullHouseChecker).win(ticket);
		verify(firstFiveChecker).win(ticket);
	}

}

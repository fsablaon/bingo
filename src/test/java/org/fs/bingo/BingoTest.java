package org.fs.bingo;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BingoTest {

	private Parameters params;
	private Bingo bingo;
	private Dealer mockDealer;
	private TicketGenerator mockTicketGen;

	@Before
	public void setUp() {
		params = new Parameters.Builder()
				.withRange(9)
				.withPlayers(2)
				.withNumPerRow(3)
				.withTicketRow(3)
				.withTicketCol(3)
				.build();

		mockDealer = mock(Dealer.class);
		mockTicketGen = mock(TicketGenerator.class);

		when(mockDealer.deal()).thenReturn(new Cue(1))
				.thenReturn(new Cue(2))
				.thenReturn(new Cue(3))
				.thenReturn(new Cue(4))
				.thenReturn(new Cue(5))
				.thenReturn(new Cue(6))
				.thenReturn(new Cue(7))
				.thenReturn(new Cue(8))
				.thenReturn(new Cue(9));


		when(mockTicketGen.createTicket()).thenReturn(new Ticket(BingoTestUtil.createMatrix()));

		BingoFactory factory = new BingoFactory(params, mockDealer, mockTicketGen);
		bingo = factory.create();
	}

	@Test
	public void testBingo() {

		//play a fixed game
		boolean gameOver = false;
		for (int i = 0; i < 10; i++){
			gameOver = bingo.play();
		}

		assertThat(gameOver, is(true));
	}

}

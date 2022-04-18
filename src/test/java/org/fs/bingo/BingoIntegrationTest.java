package org.fs.bingo;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BingoIntegrationTest {

	private Parameters params;
	private Bingo bingo;

	@Before
	public void setUp() {
		params = new Parameters.Builder()
				.withRange(90)
				.withPlayers(5)
				.withNumPerRow(5)
				.withTicketRow(3)
				.withTicketCol(10)
				.build();

		BingoFactory factory = new BingoFactory(params);
		bingo = factory.create();
	}

	@Test
	public void testBingo() {
		boolean gameOver = false;

		//called all nums in range, we should have one winner
		for (int i = 0; i < 90; i++){
			gameOver = bingo.play();
		}

		assertThat(gameOver, is(true));
	}
}

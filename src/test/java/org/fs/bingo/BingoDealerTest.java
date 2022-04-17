package org.fs.bingo;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.core.Is.is;

public class BingoDealerTest {

	private BingoDealer dealer;
	private Parameters params;

	@Before
	public void setUp() {
		params = new Parameters.Builder()
				.withRange(90)
				.withPlayers(5)
				.withNumPerRow(5)
				.withTicketRow(3)
				.withTicketCol(10)
				.build();

		dealer = new BingoDealer(params);
	}

	@Test
	public void testNoDuplicateNumsCalled() {
		Set<Integer> numsCalled = new HashSet<>();
		for (int i = 0; i < params.getRange(); ++i) {
			int num = dealer.deal().getNum();
			if (numsCalled.contains(num))
				fail("Dealer called duplicate number");
			numsCalled.add(num);
		}

		assertThat(numsCalled.size(), is(90));
	}

	@Test(expected = IllegalStateException.class)
	public void testTooManyNumsCalled() {
		for (int i = 0; i < params.getRange() + 1; ++i) {
			dealer.deal().getNum();
		}
	}

}

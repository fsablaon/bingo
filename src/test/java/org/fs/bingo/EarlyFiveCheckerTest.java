package org.fs.bingo;

import org.junit.Test;

import static org.fs.bingo.BingoTestUtil.createMatrix;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EarlyFiveCheckerTest {

	private final EarlyFiveChecker checker = new EarlyFiveChecker();

	@Test
	public void testEarlyFiveWin() {
		Node[][] matrix = createMatrix();

		//mark 5 nodes
		matrix[0][0].mark();
		matrix[1][1].mark();
		matrix[2][2].mark();
		matrix[0][1].mark();
		matrix[0][2].mark();

		WinType win = checker.win(new Ticket(matrix)).orElse(null);
		assertThat(win, is(WinType.EARLY_FIVE));
	}

	@Test
	public void testNoWin() {
		Node[][] matrix = createMatrix();

		//mark 3 nodes
		matrix[0][0].mark();
		matrix[1][1].mark();
		matrix[2][2].mark();

		WinType win = checker.win(new Ticket(matrix)).orElse(null);
		assertThat(win, nullValue());
	}

}

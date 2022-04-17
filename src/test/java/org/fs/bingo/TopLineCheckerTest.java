package org.fs.bingo;

import org.junit.Test;

import static org.fs.bingo.BingoTestUtil.createMatrix;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TopLineCheckerTest {

	private final TopLineChecker checker = new TopLineChecker();

	@Test
	public void testEmptyMatrix() {
		Node[][] matrix = new Node[3][3];
		Ticket ticket = new Ticket(matrix);

		WinType winType = checker.win(ticket).orElse(null);

		assertThat(winType, nullValue());
	}

	@Test
	public void testTopLineWin() {
		Node[][] matrix = createMatrix();

		matrix[0][0].mark();
		matrix[0][1].mark();
		matrix[0][2].mark();

		Ticket ticket = new Ticket(matrix);

		WinType winType = checker.win(ticket).orElse(null);

		assertThat(winType, is(WinType.TOP_LINE));
	}
}

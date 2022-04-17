package org.fs.bingo;

import org.junit.Test;

import static org.fs.bingo.BingoTestUtil.createMatrix;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class FullHouseCheckerTest {

	private FullHouseChecker checker = new FullHouseChecker();

	@Test
	public void testFullHouseWin() {
		Node[][] matrix = createMatrix();

		//mark all nodes
		for (Node[] row : matrix){
			for (Node n : row){
				n.mark();
			}
		}

		WinType win = checker.win(new Ticket(matrix)).orElse(null);
		assertThat(win, is(WinType.FULL_HOUSE));
	}

	@Test
	public void testFullHouseNoWin() {
		Node[][] matrix = createMatrix();

		//mark 5 nodes
		matrix[0][0].mark();
		matrix[1][1].mark();
		matrix[2][2].mark();
		matrix[0][1].mark();
		matrix[0][2].mark();

		WinType win = checker.win(new Ticket(matrix)).orElse(null);
		assertThat(win, nullValue());
	}
}

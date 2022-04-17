package org.fs.bingo;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TicketTest {

	private Ticket ticket;

	@Before
	public void setUp(){
		Node[][] matrix = BingoTestUtil.createMatrix();

		ticket = new Ticket(matrix);
	}

	@Test
	public void testMark() {
		Node[][] matrix = ticket.getMatrix();
		assertThat(matrix[1][1].isMarked(), is(false));

		//simulate #5 called
		ticket.mark(new Cue(5));

		assertThat(matrix[1][1].isMarked(), is(true));
	}
}

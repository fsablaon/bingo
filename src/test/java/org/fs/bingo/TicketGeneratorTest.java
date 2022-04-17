package org.fs.bingo;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TicketGeneratorTest {

	private final Parameters params;
	private final Ticket ticket;

	public TicketGeneratorTest() {
		params = new Parameters.Builder()
				.withRange(90)
				.withPlayers(5)
				.withNumPerRow(10)
				.withTicketRow(3)
				.withTicketCol(10)
				.build();

		TicketGenerator factory = new TicketGenerator(params);
		ticket = factory.createTicket();
	}

	@Test
	public void testTicketSize(){
		Node[][] matrix = ticket.getMatrix();
		Node[] row = matrix[0];

		//rows
		assertThat(matrix.length, is(params.getTicketRow()));
		//columns
		assertThat(row.length, is(params.getTicketCol()));
	}

	@Test
	public void testNumPerRow(){
		int counter = 0;

		Node[][] matrix = ticket.getMatrix();
		Node[] row = matrix[0];

		for(Node n : row){
			if (n == null)
				continue;

			counter++;
		}

		assertThat(counter, is(params.getNumPerRow()));
	}

	@Test
	public void testNoDups(){
		Node[][] matrix = ticket.getMatrix();
		Set<Integer> intSet = new HashSet<>();

		for (Node[] row : matrix){
			for (Node n : row){
				if (n == null)
					continue;

				final int value = n.getValue();
				if (intSet.contains(value))
					Assert.fail("Detected duplicate num: " + value);
				else
					intSet.add(value);
			}
		}
	}

}

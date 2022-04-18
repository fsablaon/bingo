package org.fs.bingo;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class ParametersTest {

	@Test
	public void testColumnsLessThanNumPerRow(){
		try {
			new Parameters.Builder()
					.withRange(90)
					.withPlayers(2)
					.withNumPerRow(10) //too many nums per row
					.withTicketRow(3)
					.withTicketCol(5) //too small
					.build();
		} catch (IllegalArgumentException ex) {
			String message = ex.getMessage();
			assertThat(message, is("Unable to fit numbers per row. Column size is too small"));
			return;
		}

		fail("Expected IllegalArgumentException...");
	}

	@Test
	public void testRangeTooSmall(){
		try {
			new Parameters.Builder()
					.withRange(5) //range too small
					.withPlayers(2)
					.withNumPerRow(5) //requires 25 nums
					.withTicketRow(5)
					.withTicketCol(5)
					.build();
		} catch (IllegalArgumentException ex) {
			String message = ex.getMessage();
			assertThat(message, is("Unable to fill tickets. Required Range: 25"));
			return;
		}

		fail("Expected IllegalArgumentException...");
	}



}

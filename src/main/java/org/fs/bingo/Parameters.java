package org.fs.bingo;

import java.util.StringJoiner;

/**
 * Parameters for Bingo Game, inputs take from user
 *
 * Notes:
 * Constraints on inputs? assuming int range
 * Can use lombok for builder pattern / getters
 *
 */
public class Parameters {

	private final int range;
	private final int players;
	private final int ticketRow;
	private final int ticketCol;
	private final int numPerRow;

	private Parameters(Builder builder) {
		this.range = builder.range;
		this.players = builder.players;
		this.ticketRow = builder.ticketRow;
		this.ticketCol = builder.ticketCol;
		this.numPerRow = builder.numPerRow;
	}

	public int getRange() {
		return range;
	}

	public int getPlayers() {
		return players;
	}

	public int getTicketRow() {
		return ticketRow;
	}

	public int getTicketCol() {
		return ticketCol;
	}

	public int getNumPerRow() {
		return numPerRow;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Parameters.class.getSimpleName() + "[", "]")
				.add("range=" + range)
				.add("players=" + players)
				.add("ticketRow=" + ticketRow)
				.add("ticketCol=" + ticketCol)
				.add("numPerRow=" + numPerRow)
				.toString();
	}

	public static class Builder {

		private int range = 90;
		private int players = 5;
		private int ticketRow = 3;
		private int ticketCol = 10;
		private int numPerRow = 5;

		public Builder(){}

		public Builder withRange(int range){
			this.range = range;
			return this;
		}

		public Builder withPlayers(int players){
			this.players = players;
			return this;
		}

		public Builder withTicketRow(int ticketRow){
			this.ticketRow = ticketRow;
			return this;
		}

		public Builder withTicketCol(int ticketCol){
			this.ticketCol = ticketCol;
			return this;
		}

		public Builder withNumPerRow(int numPerRow){
			this.numPerRow = numPerRow;
			return this;
		}

		public Parameters build() {
			//TODO: Are range and numPerRow required to allow for early 5 winType. Assuming no.

			if (range < 1 )
				throw new IllegalArgumentException("Range less than 0");

			if (players < 1 )
				throw new IllegalArgumentException("Must have at least 1 player");

			if (ticketRow < 1)
				throw new IllegalArgumentException("Must have at least 1 row");

			if (ticketCol < 1)
				throw new IllegalArgumentException("Must have at least 1 col");

			if (numPerRow < 1)
				throw new IllegalArgumentException("Must have at least 1 number per row");

			if (range < (numPerRow * ticketRow))
				throw new IllegalArgumentException("Unable to fill tickets. Required Range: " + (numPerRow * ticketRow));

			if (numPerRow > ticketCol)
				throw new IllegalArgumentException("Unable to fit numbers per row. Column size is too small");

			return new Parameters(this);
		}
	}
}

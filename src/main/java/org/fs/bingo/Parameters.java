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

		private int range;
		private int players;
		private int ticketRow;
		private int ticketCol;
		private int numPerRow;

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

		public Parameters build(){
			//TODO: validate inputs
			return new Parameters(this);
		}
	}
}

package org.fs.bingo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TicketGenerator {

	private final Random random;
	private final Parameters params;

	public TicketGenerator(Parameters params) {
		if (params == null)
			throw new IllegalArgumentException("Parameters cannot be null");

		this.random = new Random();
		this.params = params;
	}

	public Ticket createTicket(){
		int rowSize = params.getTicketRow();
		int colSize = params.getTicketCol();

		if (rowSize < 1)
			throw new IllegalArgumentException("Row size cannot be less than 1");

		if (colSize < 1)
			throw new IllegalArgumentException("Column size cannot be less than 1");

		//TODO: validate we have enough numbers to allow for early 5

		int numPerRow = params.getNumPerRow();

		Node[][] matrix = new Node[rowSize][colSize];
		Set<Integer> rangeSet = new HashSet<>();

		//init each row
		for (Node[] row : matrix) {
			Set<Integer> indexSet = new HashSet<>();

			for (int i = 0; i < numPerRow; ++i){

				int index = generateIndex();
				int num = generateNum();

				while (rangeSet.contains(num)){
					num = generateNum();
				}
				rangeSet.add(num);

				while (indexSet.contains(index)){
					index = generateIndex();
				}
				indexSet.add(index);

				row[index] = new Node(num);
			}
		}

		return new Ticket(matrix);
	}

	private int generateIndex() {
		return random.nextInt(params.getTicketCol());
	}

	private int generateNum() {
		return random.nextInt(params.getRange()) + 1;
	}

}

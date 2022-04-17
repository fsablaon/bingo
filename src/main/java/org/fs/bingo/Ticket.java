package org.fs.bingo;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Ticket representing bingo card.
 */
public class Ticket {

	//TODO: discuss data structure, currently it's a sparse matrix
	private final Node[][] matrix;

	public Ticket(Node[][] matrix){
		this.matrix = matrix;
	}

	public Node[][] getMatrix() {
		//return copy of matrix
		return Arrays.stream(matrix)
				.map(Node[]::clone) //clone row
				.toArray(Node[][]::new); //capture new matrix
	}

	public void mark(Cue cue) {
		if (cue == null)
			throw new IllegalArgumentException("Cue cannot be null");

		for (Node[] row : matrix){
			for (Node n : row){
				if (n == null)
					continue;

				if (n.getValue() == cue.getNum())
					n.mark();
			}
		}
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Ticket.class.getSimpleName() + "[", "]")
				.add("matrix=" + Arrays.deepToString(matrix))
				.toString();
	}


}

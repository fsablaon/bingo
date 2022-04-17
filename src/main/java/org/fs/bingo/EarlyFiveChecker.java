package org.fs.bingo;

import java.util.Optional;

/**
 * Checks if we have 5 nums in a given ticket
 *
 */
public class EarlyFiveChecker implements Checker {

	@Override
	public Optional<WinType> win(Ticket ticket) {
		Node[][] matrix = ticket.getMatrix();

		int count = 0;

		for (Node[] row : matrix){
			for (Node n : row){
				if (n == null)
					continue;

				if (n.isMarked())
					count++;

				if (count == 5)
					return Optional.of(WinType.EARLY_FIVE);
			}
		}

		return Optional.empty();
	}

	@Override
	public WinType getWinType() {
		return WinType.EARLY_FIVE;
	}
}

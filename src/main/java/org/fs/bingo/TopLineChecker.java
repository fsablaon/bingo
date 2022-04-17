package org.fs.bingo;

import java.util.Optional;

public class TopLineChecker implements Checker {

	@Override
	public Optional<WinType> win(Ticket ticket) {
		if (ticket == null)
			throw new IllegalArgumentException("Ticket in topline checker cannot be null");

		Node[][] matrix = ticket.getMatrix();
		Node[] row = matrix[0]; //select top line

		if (row == null)
			throw new IllegalArgumentException("Top Line cannot be null");

		boolean isWin = false;
		for (Node n : row){
			if (n == null)
				continue;

			if (n.isMarked()) {
				isWin = true;
			} else {
				isWin = false;
				break;
			}
		}

		return isWin ? Optional.of(WinType.TOP_LINE) : Optional.empty();
	}

	@Override
	public WinType getWinType() {
		return WinType.TOP_LINE;
	}
}

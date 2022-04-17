package org.fs.bingo;


import java.util.Optional;

public class FullHouseChecker implements Checker {

	@Override
	public Optional<WinType> win(Ticket ticket) {
		Node[][] matrix = ticket.getMatrix();

		for (Node[] row : matrix){
			for (Node n : row){
				if (n == null)
					continue;

				if (!n.isMarked())
					return Optional.empty();
			}
		}

		return Optional.of(WinType.FULL_HOUSE);
	}

	@Override
	public WinType getWinType() {
		return WinType.FULL_HOUSE;
	}


}

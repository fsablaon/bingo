package org.fs.bingo;

import java.util.Optional;

/**
 * Checks to see if ticket is winner
 */
public interface Checker {

	/**
	 * Reviews ticket to see if we have a winner
	 * @param ticket ticket to review
	 * @return Optional.empty() if we do not have a winner, WinType otherwise
	 */
	Optional<WinType> win(Ticket ticket);

	/**
	 * Specifies the wintype to check for
	 * @return the wintype this checker checks for
	 */
	WinType getWinType();
}

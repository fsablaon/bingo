package org.fs.bingo;

/**
 * Deals a round of Bingo
 */
public interface Dealer {

	/**
	 * Deals a Cue, representing a round of bingo
	 * @return {@link Cue} represents round of bingo
	 */
	Cue deal();
}

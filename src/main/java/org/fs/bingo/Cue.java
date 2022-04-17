package org.fs.bingo;

/**
 * Represents a 'cue' from the dealer
 *
 * Note:
 * Other variations of Bingo include letter
 */
public class Cue {

	private final int num;

	public Cue(int num){
		this.num = num;
	}

	public int getNum() {
		return num;
	}

}

package org.fs.bingo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Selects a random number from the given parameters
 */
public class BingoDealer implements Dealer {

	private final Random random;
	private final Parameters params;
	private final Set<Integer> numsCalled;

	public BingoDealer(Parameters params) {
		if (params == null)
			throw new IllegalArgumentException("BingoDealer Parameter cannot be null");

		this.random = new Random();
		this.params = params;
		this.numsCalled = new HashSet<>();
	}

	@Override
	public Cue deal() {
		if (numsCalled.size() >= params.getRange())
			throw new IllegalStateException("All numbers have been called");

		int num = generateNum();
		while(numsCalled.contains(num)) {
			num = generateNum();
		}

		numsCalled.add(num);
		System.out.println("Next number is: " + num);

		return new Cue(num);
	}

	private int generateNum() {
		return random.nextInt(params.getRange()) + 1;
	}

}

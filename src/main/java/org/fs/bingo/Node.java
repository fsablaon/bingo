package org.fs.bingo;

import java.util.StringJoiner;

/**
 * Represents square in ticket
 */
public class Node {

	private boolean marked;
	private final int value;

	public Node(int value){
		this.value = value;
	}

	public void mark(){
		this.marked = true;
	}

	public boolean isMarked(){
		return marked;
	}

	public Integer getValue(){
		return value;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
				.add("marked=" + marked)
				.add("value=" + value)
				.toString();
	}
}

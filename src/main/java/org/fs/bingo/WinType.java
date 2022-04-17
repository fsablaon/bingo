package org.fs.bingo;

/**
 * Types of wins for bingo
 */
public enum WinType {
	EARLY_FIVE("Early Five"),
	TOP_LINE("Top Line"),
	FULL_HOUSE("Full House");

	private final String displayName;

	WinType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}

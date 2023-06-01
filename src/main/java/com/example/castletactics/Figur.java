package com.example.castletactics;

public abstract class Figur {
	private final boolean isWhite; //blank final

	protected Figur(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public abstract boolean zug();
}

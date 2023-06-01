package com.example.castletactics;

public class Bauer extends Figur {
	Bauer(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
}

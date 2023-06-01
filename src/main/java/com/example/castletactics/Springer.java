package com.example.castletactics;

public class Springer extends Figur {
	Springer(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
}

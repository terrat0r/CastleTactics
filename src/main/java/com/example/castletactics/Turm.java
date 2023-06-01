package com.example.castletactics;

public class Turm extends Figur {
	Turm(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
}

package com.example.castletactics;

public class Läufer extends Figur {
	Läufer(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
}

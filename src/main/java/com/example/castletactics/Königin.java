package com.example.castletactics;

public class Königin extends Figur {
	Königin(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
}

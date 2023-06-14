package com.example.castletactics;

import javafx.scene.shape.Rectangle;

public class Königin extends Figur {
	Königin(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
	public Rectangle getRect(int s, boolean black){
		return super.getRect(s, black ? "file:./src/main/resources/img/chess-queen-black.png" : "file:./src/main/resources/img/chess-queen-white.png");
	}
}

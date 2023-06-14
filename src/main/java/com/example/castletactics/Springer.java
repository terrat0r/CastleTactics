package com.example.castletactics;

import javafx.scene.shape.Rectangle;

public class Springer extends Figur {
	Springer(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
	public Rectangle getRect(int s, boolean black){
		return super.getRect(s, black ? "file:./src/main/resources/img/chess-knight-black.png" : "file:./src/main/resources/img/chess-knight-white.png");
	}
}

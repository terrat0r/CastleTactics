package com.example.castletactics;

import javafx.scene.shape.Rectangle;

public class König extends Figur {
	König(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
	public Rectangle getRect(int s, boolean black){
		return super.getRect(s, black ? "file:./src/main/resources/img/chess-king-black.png" : "file:./src/main/resources/img/chess-king-white.png");
	}
}

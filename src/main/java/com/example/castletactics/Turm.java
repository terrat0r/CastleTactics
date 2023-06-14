package com.example.castletactics;

import javafx.scene.shape.Rectangle;

public class Turm extends Figur {
	Turm(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}
	public Rectangle getRect(int s, boolean black){
		return super.getRect(s, black ? "file:./src/main/resources/img/chess-rook-black.png" : "file:./src/main/resources/img/chess-rook-white.png");
	}
}

package com.example.castletactics;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bauer extends Figur {
	Bauer(){
		super(true);
	}

	@Override
	public boolean zug() {
		return false;
	}

	public Rectangle getRect(int s, boolean black){
		return super.getRect(s, black ? "file:./src/main/resources/img/chess-pawn-black.png" : "file:./src/main/resources/img/chess-pawn-white.png");
	}
}

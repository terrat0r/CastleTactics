package com.example.castletactics;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Figur {
	private final boolean isWhite; //blank final

	protected Figur(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public abstract boolean zug();

	public Rectangle getRect(int s, String path){
		Image image1 = new Image(path,100,150,false,false);
		Rectangle rect = new Rectangle(s,s,s,s);
		ImagePattern imagePattern = new ImagePattern(image1);
		rect.setFill(imagePattern);
		return rect;
	}
}

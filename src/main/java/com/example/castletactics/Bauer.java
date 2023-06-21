package com.example.castletactics;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bauer extends Figur {
	Bauer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-pawn-white.png" : "file:./src/main/resources/img/chess-pawn-black.png", side, col, row, spv);

	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		//TODO: logik einfügen
		return false;
	}

}

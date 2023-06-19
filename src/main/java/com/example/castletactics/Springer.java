package com.example.castletactics;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Springer extends Figur {
	Springer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-king-white.png" : "file:./src/main/resources/img/chess-king-black.png", side, col, row, spv);
	}

	@Override
	public boolean zugErlaubt(Pane pane, int col, int row) {


		//TODO: logik einfügen
		return true;
	}
}

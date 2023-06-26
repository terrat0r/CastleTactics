package com.example.castletactics;

import javafx.scene.layout.GridPane;

public class Springer extends Figur {
	Springer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-knight-white.png" : "file:./src/main/resources/img/chess-knight-black.png", side, col, row, spv);
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {


		//TODO: logik einfügen
		return true;
	}
}

package com.example.castletactics;

import javafx.scene.layout.GridPane;

public class Bauer extends Figur {
	Bauer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-pawn-white.png" : "file:./src/main/resources/img/chess-pawn-black.png", side, col, row, spv);

	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		//TODO: logik einfügen
		return true;
	}

}

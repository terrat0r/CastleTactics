package com.example.castletactics;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Turm extends Figur {
	Turm(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-rook-white.png" : "file:./src/main/resources/img/chess-rook-black.png", side, col, row, spv);
	}

	@Override
	public boolean zugErlaubt(Pane pane, int col, int row) {


		//TODO: logik einfügen
		return true;
	}
}

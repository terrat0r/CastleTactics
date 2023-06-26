package com.example.castletactics;

import javafx.scene.layout.GridPane;

public class Turm extends Figur {
	Turm(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-rook-white.png" : "file:./src/main/resources/img/chess-rook-black.png", side, col, row, spv);
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {


		//TODO: logik einfügen
		int diffcol = Math.abs(col - colDest);
		int diffrow = Math.abs(row - rowDest);
		if (diffcol == 0 && diffrow != 0 || diffrow == 0 && diffcol != 0){

			return true;
		}
	return false;
	}
}

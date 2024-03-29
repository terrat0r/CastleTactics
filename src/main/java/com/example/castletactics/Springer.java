package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Springer extends Figur {
	Springer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv, Zugverwaltung zugverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-knight-white.png" : "file:./src/main/resources/img/chess-knight-black.png", side, col, row, spv, zugverwaltung);
	}


	@Override
	public List<predict> getPossibleMoves() {
		return null;
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		int rowDiff = Math.abs(row - rowDest);
		int colDiff = Math.abs(col - colDest);

		// Checking the knight's move patterns
		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
			if (zugverwaltung.figuren[rowDest][colDest] == null) return true; // Leeres Feld
			else return this.isWhite != zugverwaltung.figuren[rowDest][colDest].isWhite; // Gegner Feld
		}
		// The move is not allowed
		return false;
	}
}

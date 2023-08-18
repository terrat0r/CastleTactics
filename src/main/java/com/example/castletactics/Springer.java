package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Springer extends Figur {
	Springer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-knight-white.png" : "file:./src/main/resources/img/chess-knight-black.png", side, col, row, spv);
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
			// The move is allowed
			return true;
		}
		// The move is not allowed
		return false;
	}
}

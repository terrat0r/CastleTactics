package com.example.castletactics;

import javafx.scene.layout.GridPane;

public class König extends Figur {
	König(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-king-white.png" : "file:./src/main/resources/img/chess-king-black.png", side, col, row, spv);
	}
	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		// Check if the target position is within the chessboard
		if (rowDest < 0 || rowDest > 7 || colDest < 0 || colDest > 7) {
			return false;
		}

		// Check if the difference in row and column coordinates is at most 1
		if (Math.abs(row - rowDest) <= 1 && Math.abs(col - colDest) <= 1) {
			return true;
		}

		// The move is not allowed
		return false;
	}
}

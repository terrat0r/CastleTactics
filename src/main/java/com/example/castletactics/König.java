package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class König extends Figur {
	König(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv, Zugverwaltung zugverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-king-white.png" : "file:./src/main/resources/img/chess-king-black.png", side, col, row, spv, zugverwaltung);
	}

	@Override
	public List<predict> getPossibleMoves() {
		return null;
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		// Check if the target position is within the chessboard
		if (rowDest < 0 || rowDest > 7 || colDest < 0 || colDest > 7) {
			return false;
		}

		//check if there is no own figure
		if (zugverwaltung.figuren[rowDest][colDest] != null && zugverwaltung.figuren[rowDest][colDest].isWhite == this.isWhite) {
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

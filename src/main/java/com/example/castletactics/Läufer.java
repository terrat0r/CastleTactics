package com.example.castletactics;

import javafx.scene.layout.GridPane;

public class Läufer extends Figur {
	Läufer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-bishop-white.png" : "file:./src/main/resources/img/chess-bishop-black.png", side, col, row, spv);
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {

		// Check if the bishop moves diagonally
		int rowDiff = Math.abs(rowDest - row);
		int colDiff = Math.abs(colDest - col);
		if (rowDiff != colDiff) {
			return false;
		}

		// Check if the path of the bishop is blocked
		int rowStep = (rowDest > row) ? 1 : -1;
		int colStep = (colDest > col) ? 1 : -1;
		int currRow = row + rowStep;
		int currCol = col + colStep;

		while (currRow != rowDest && currCol != colDest) {
			if (isOccupied(currRow, currCol)) {
				return false;
			}
			currRow += rowStep;
			currCol += colStep;
		}

		// Check if the target square is empty or occupied by an opponent's piece
		return !isOccupiedBySameColor(rowDest, colDest);
	}

	private boolean isOccupied(int row, int col) {
		return spv.isOccupied(row, col);
	}

	private boolean isOccupiedBySameColor(int row, int col) {
		return spv.isOccupiedBySameColor(isWhite, row, col);
	}
}

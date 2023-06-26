package com.example.castletactics;

import javafx.scene.layout.GridPane;

public class Bauer extends Figur {
	Bauer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-pawn-white.png" : "file:./src/main/resources/img/chess-pawn-black.png", side, col, row, spv);

	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		// Check if the destination square is outside the chessboard
		if (rowDest < 0 || rowDest >= 8 || colDest < 0 || colDest >= 8) {
			return false;
		}
		// Check if the destination square is diagonally adjacent to the current square
		if (Math.abs(col - colDest) == 1 && rowDest == row + 1) {
			// The pawn can move diagonally forward to capture a piece
			return true;
		}
		// Check if the pawn is moving one square forward
		if (col == colDest && rowDest == row + 1) {
			// The pawn moves one square forward
			return true;
		}
		// The pawn can move two squares forward if it is on the starting position
		if (row == 1 && col == colDest && rowDest == row + 2) {
			// The pawn moves two squares forward
			return true;
		}
		return false;
	}


}

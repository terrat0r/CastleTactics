package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Bauer extends Figur {
	Bauer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv, Zugverwaltung zugverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-pawn-white.png" : "file:./src/main/resources/img/chess-pawn-black.png", side, col, row, spv, zugverwaltung);

	}

	@Override
	public List<predict> getPossibleMoves() {
		return null;
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		if (!isWhite) {
			// Check if the destination square is diagonally adjacent to the current square
			if (Math.abs(col - colDest) == 1 && rowDest == row + 1 && zugverwaltung.figuren[rowDest][colDest] != null) {
				// The pawn can move diagonally forward to capture a piece
				return true;
			}

			// Check if the pawn is moving one square forward
			if (col == colDest && rowDest == row + 1 && zugverwaltung.figuren[row+1][col] == null) {
				// The pawn moves one square forward
				return true;
			}
			// The pawn can move two squares forward if it is on the starting position
			// The pawn moves two squares forward
			return !moved && col == colDest && rowDest == row + 2 && zugverwaltung.figuren[row+1][col] == null && zugverwaltung.figuren[row+2][col] == null ;
		} else {
			// Check if the destination square is diagonally adjacent to the current square
			if (Math.abs(col - colDest) == 1 && rowDest == row - 1 && zugverwaltung.figuren[rowDest][colDest] != null) {
				// The pawn can move diagonally forward to capture a piece
				return true;
			}
			// Check if the pawn is moving one square forward
			if (col == colDest && rowDest == row - 1&& zugverwaltung.figuren[row-1][col] == null) {
				// The pawn moves one square forward
				return true;
			}
			// The pawn can move two squares forward if it is on the starting position
			// The pawn moves two squares forward
			return !moved && col == colDest && rowDest == row - 2 && zugverwaltung.figuren[row-1][col] == null && zugverwaltung.figuren[row-2][col] == null;
		}
	}


}

package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Läufer extends Figur {
	Läufer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv, Zugverwaltung zugverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-bishop-white.png" : "file:./src/main/resources/img/chess-bishop-black.png", side, col, row, spv, zugverwaltung);
	}


	@Override
	public List<predict> getPossibleMoves() {
		return null;
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		if (Math.abs(row - rowDest) == Math.abs(col - colDest)) {
			int rowFactor;
			if(row < rowDest) {
				rowFactor = 1;
			}
			else {
				rowFactor = -1;
			}
			int colFactor;
			if(col < colDest) {
				colFactor = 1;
			}
			else {
				colFactor = -1;
			}
			int r = row + rowFactor;
			int c = col + colFactor;

			while (r != rowDest && c != colDest) {
				// Check for obstructions on the way
				if (zugverwaltung.figuren[r][c] != null) {
					return false;
				}
				r += rowFactor;
				c += colFactor;
			}

			// Check for destination
			if (zugverwaltung.figuren[r][c] == null || zugverwaltung.figuren[r][c] != null && zugverwaltung.figuren[r][c].isWhite != this.isWhite) {
				return true;
			}
		}

		// The move is not allowed
		return false;
	}


}

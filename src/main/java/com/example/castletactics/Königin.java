package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Königin extends Figur {
	Königin(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv, Zugverwaltung zugverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-queen-white.png" : "file:./src/main/resources/img/chess-queen-black.png", side, col, row, spv, zugverwaltung);
	}

	@Override
	public List<predict> getPossibleMoves() {
		return null;
	}


	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		if (Math.abs(row - rowDest) == Math.abs(col - colDest)  || row == rowDest || col == colDest) {
			int rowFactor = Integer.compare(rowDest, row);
			int colFactor = Integer.compare(colDest, col);

			// Sich selbst muss man nicht prüfen
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
            return zugverwaltung.figuren[r][c] == null || zugverwaltung.figuren[r][c] != null && zugverwaltung.figuren[r][c].isWhite != this.isWhite;
		}
		// The move is not allowed
		return false;
	}

}

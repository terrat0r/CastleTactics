package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Königin extends Figur {
	Königin(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-queen-white.png" : "file:./src/main/resources/img/chess-queen-black.png", side, col, row, spv);
	}

	@Override
	public List<predict> getPossibleMoves() {
		return null;
	}


	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		if (Math.abs(row - rowDest) == Math.abs(col - colDest)  || row == rowDest || col == colDest) {
			int rowFactor;
			if(row < rowDest) {
				rowFactor = 1;
			}
			else if (row == rowDest)
			{
				rowFactor = 0;
			}
			else {
				rowFactor = -1;
			}
			int colFactor;

			if(col < colDest) {
				colFactor = 1;
			}
			else if (col == colDest)
			{
				colFactor = 0;
			}
			else {
				colFactor = -1;
			}
			// Sich selbst muss man nicht prüfen
			int r = row + rowFactor;
			int c = col + colFactor;
			while (r != rowDest && c != colDest) {
				// Check for obstructions on the way
				if (spv.figuren[r][c] != null) {
					return false;
				}
				r += rowFactor;
				c += colFactor;
			}
			// Check for destination
			if (spv.figuren[r][c] == null || spv.figuren[r][c] != null && spv.figuren[r][c].isWhite != this.isWhite) {
				return true;
			}
		}
		// The move is not allowed
		return false;
	}

}

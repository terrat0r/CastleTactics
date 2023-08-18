package com.example.castletactics;

import javafx.scene.layout.GridPane;

import java.util.List;

public class Läufer extends Figur {
	Läufer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spv){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-bishop-white.png" : "file:./src/main/resources/img/chess-bishop-black.png", side, col, row, spv);
	}


	@Override
	public List<predict> getPossibleMoves() {
		return null;
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		if (Math.abs(row - rowDest) == Math.abs(col - colDest)) {
			int startRow = Math.min(row, rowDest);
			int endRow = Math.max(row, rowDest);
			int startCol = Math.min(col, colDest);
			int endCol = Math.max(col, colDest);
			int r = startRow + 1;
			int c = startCol + 1;
			while (r < endRow && c < endCol) {
				// Check for obstructions on the board
				if (spv.figuren[r][c] != null) {
					return false;
				}
				r++;
				c++;
			}
			return true;
		}

		// The move is not allowed
		return false;
	}


}

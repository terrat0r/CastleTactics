package de.basachsen._3it_22.castletactics.figures;

import de.basachsen._3it_22.castletactics.Spielverwaltung;
import de.basachsen._3it_22.castletactics.jfx.FigurJFX;
import javafx.scene.layout.GridPane;

public class Königin extends FigurJFX {
	public Königin(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spielverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-queen-white.png" : "file:./src/main/resources/img/chess-queen-black.png", side, col, row, spielverwaltung);
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		// Check if the source and destination positions are the same
		if (row == rowDest && col == colDest) {
			return false;
		}

		// Check horizontal movement
		if (row == rowDest) {
			int startCol = Math.min(col, colDest);
			int endCol = Math.max(col, colDest);
			for (int c = startCol + 1; c < endCol; c++) {
				// Check for obstructions
				//TODO check if attribute .zugPrüfen() should be moved to FigurJFX
				if (spielverwaltung.zugPrüfen()) {
					return false;
				}
			}
			return true;
		}

		// Check vertical movement
		if (col == colDest) {
			int startRow = Math.min(row, rowDest);
			int endRow = Math.max(row, rowDest);
			for (int r = startRow + 1; r < endRow; r++) {
				// Check for obstructions
				if (spielverwaltung.zugPrüfen()) {
					return false;
				}
			}
			return true;
		}

		// Check diagonal movement
		if (Math.abs(row - rowDest) == Math.abs(col - colDest)) {
			int startRow = Math.min(row, rowDest);
			int endRow = Math.max(row, rowDest);
			int startCol = Math.min(col, colDest);
			int endCol = Math.max(col, colDest);
			int r = startRow + 1;
			int c = startCol + 1;
			while (r < endRow && c < endCol) {
				// Check for obstructions
				if (spielverwaltung.zugPrüfen()) {
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

package de.basachsen._3it_22.castletactics.figures;

import de.basachsen._3it_22.castletactics.Spielverwaltung;
import de.basachsen._3it_22.castletactics.jfx.FigurJFX;
import javafx.scene.layout.GridPane;

public class Läufer extends FigurJFX {
	public Läufer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spielverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-bishop-white.png" : "file:./src/main/resources/img/chess-bishop-black.png", side, col, row, spielverwaltung);
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		if (Math.abs(row - rowDest) == Math.abs(col - colDest)) {
			int startRow = Math.min(row, rowDest) + 1;
			int endRow = Math.max(row, rowDest);
			int startCol = Math.min(col, colDest + 1);
			int endCol = Math.max(col, colDest);

			/*
			int r = startRow;
			int c = startCol;

			while (r < endRow && c < endCol) {
				// Check for obstructions
				if (getSpielverwaltung().zugPrüfen()) {
					return false;
				}
				r++;
				c++;
			}
			*/

			for(int r=startRow, c=endCol; r < endRow && c < endCol; c++,r++ ){
				// Check for obstructions
				//TODO check if method .zugPrüfen() should be moved to FigurJFX
				if (spielverwaltung.zugPrüfen()) {
					return false;
				}

			}
			return true;
		}

		// The move is not allowed
		return false;
	}

}

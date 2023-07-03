package de.basachsen._3it_22.castletactics.figures;

import de.basachsen._3it_22.castletactics.Spielverwaltung;
import de.basachsen._3it_22.castletactics.jfx.FigurJFX;
import javafx.scene.layout.GridPane;

public class Springer extends FigurJFX {
	public Springer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spielverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-knight-white.png" : "file:./src/main/resources/img/chess-knight-black.png", side, col, row, spielverwaltung);
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		//TODO check if method .zugPrüfen() should be moved to FigurJFX
		int rowDiff = Math.abs(row - rowDest);
		int colDiff = Math.abs(col - colDest);

		// Checking the knight's move patterns
		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
			// The move is allowed
			return true;
		}
		// The move is not allowed
		return false;
	}
}

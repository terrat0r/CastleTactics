package de.basachsen._3it_22.castletactics.figures;

import de.basachsen._3it_22.castletactics.Spielverwaltung;
import de.basachsen._3it_22.castletactics.jfx.FigurJFX;
import javafx.scene.layout.GridPane;

public class Bauer extends FigurJFX {
	public Bauer(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spielverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-pawn-white.png" : "file:./src/main/resources/img/chess-pawn-black.png", side, col, row, spielverwaltung);

	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {
		//TODO check why there is no check on getSpielverwaltung().zugPrüfen()

		FigurJFX[][] tempFigur = spielverwaltung.getFiguren();

		if (!isWhite()) {
			// Check if the destination square is diagonally adjacent to the current square
			if (Math.abs(col - colDest) == 1 && rowDest == row + 1 && tempFigur[rowDest][colDest] != null) {
				// The pawn can move diagonally forward to capture a piece
				return true;
			}

			// Check if the pawn is moving one square forward
			if (col == colDest && rowDest == row + 1 && tempFigur[row+1][col] == null) {
				// The pawn moves one square forward
				return true;
			}
			// The pawn can move two squares forward if it is on the starting position
			// The pawn moves two squares forward
			return hasNotMovedYet() && col == colDest && rowDest == row + 2 && tempFigur[row+1][col] == null && tempFigur[row+2][col] == null ;
		} else {
			// Check if the destination square is diagonally adjacent to the current square
			if (Math.abs(col - colDest) == 1 && rowDest == row - 1 && tempFigur[rowDest][colDest] != null) {
				// The pawn can move diagonally forward to capture a piece
				return true;
			}
			// Check if the pawn is moving one square forward
			if (col == colDest && rowDest == row - 1&& tempFigur[row-1][col] == null) {
				// The pawn moves one square forward
				return true;
			}
			// The pawn can move two squares forward if it is on the starting position
			// The pawn moves two squares forward
			return hasNotMovedYet() && col == colDest && rowDest == row - 2 && tempFigur[row-1][col] == null && tempFigur[row-2][col] == null;
		}
	}


}

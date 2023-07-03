package de.basachsen._3it_22.castletactics.figures;

import de.basachsen._3it_22.castletactics.Spielverwaltung;
import de.basachsen._3it_22.castletactics.jfx.FigurJFX;
import javafx.scene.layout.GridPane;

public class Turm extends FigurJFX {
	public Turm(GridPane pane, boolean isWhite, int side, int col, int row, Spielverwaltung spielverwaltung){
		super(pane, isWhite, isWhite ? "file:./src/main/resources/img/chess-rook-white.png" : "file:./src/main/resources/img/chess-rook-black.png", side, col, row, spielverwaltung);
	}

	@Override
	public boolean zugErlaubt(int row, int col, int rowDest, int colDest) {


		//TODO check if method .zugPrüfen() should be moved to FigurJFX
		int diffcol = Math.abs(col - colDest);
		int diffrow = Math.abs(row - rowDest);
		if (diffcol == 0 && diffrow != 0 || diffrow == 0 && diffcol != 0){

			return true;
		}
	return false;
	}
}

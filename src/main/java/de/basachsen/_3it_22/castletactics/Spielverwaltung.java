package de.basachsen._3it_22.castletactics;

import de.basachsen._3it_22.castletactics.figures.Bauer;
import de.basachsen._3it_22.castletactics.jfx.FigurJFX;
import de.basachsen._3it_22.castletactics.jfx.SpielbrettJFX;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Spielverwaltung extends Application{
	private int round = 0;
	private boolean whitePlays = true;
	private Stage stage;
	private final Tactics tactics;
	public FigurJFX zumSchmeißen;
	public final SpielbrettJFX spielbrettJFX;
	public FigurJFX derSchmeißende;
	public FigurJFX enPassantKandidat = null;
	FigurJFX[][] getFiguren;
	ArrayList<FigurJFX> geschmissen;

	//TODO Überlegung auf Singleton umzustellen, für mehr Punkte
	public Spielverwaltung(){
		getFiguren = new FigurJFX[8][8];
		geschmissen = new ArrayList<>();
		spielbrettJFX = new SpielbrettJFX(this);
		tactics = new Tactics(this);

	}

	public int getRound() {
		return round;
	}

	public boolean isWhitePlaying() {
		return whitePlays;
	}

	public SpielbrettJFX getSpielbrettJFX() {
		return spielbrettJFX;
	}

	public Stage getStage() {
		return stage;
	}

	public FigurJFX getZumSchmeißen() {
		return zumSchmeißen;
	}

	public void setZumSchmeißen(FigurJFX zumSchmeißen) {
		this.zumSchmeißen = zumSchmeißen;
	}

	public FigurJFX getDerSchmeißende() {
		return derSchmeißende;
	}

	public void setDerSchmeißende(FigurJFX derSchmeißende) {
		this.derSchmeißende = derSchmeißende;
	}

	public FigurJFX getEnPassantKandidat() {
		return enPassantKandidat;
	}

	public void setEnPassantKandidat(FigurJFX enPassantKandidat) {
		this.enPassantKandidat = enPassantKandidat;
	}

	public FigurJFX[][] getFiguren() {
		return getFiguren;
	}

	public void setGetFiguren(FigurJFX[][] getFiguren) {
		this.getFiguren = getFiguren;
	}

	public ArrayList<FigurJFX> getGeschmissen() {
		return geschmissen;
	}

	public void start(Stage stage){
		this.stage = stage;
		tactics.start(stage);
	}

	public void start(){ start(this.stage);}

	public void fensterWechseln(String neu){
		switch (neu) {
		case "Hauptmenü":
			start();
			break;
		case "Schach":
			spielbrettJFX.start(stage);
		default:
			break;
		}
	}

	public boolean enPassantPrüfen(Bauer bauer, int destRow, int destCol)
	{
		boolean isEnPassantValid = false;

		boolean validateEnPassent = enPassantKandidat != null
				&& enPassantKandidat.getCol() == destCol
				&& Math.abs(enPassantKandidat.getRow()-destRow) == 1
				&& enPassantKandidat.getRow() == bauer.getRow()
				&& Math.abs(bauer.getCol() - enPassantKandidat.getCol()) == 1;

		if (validateEnPassent)
		{
			enPassantKandidat.schmeißen(spielbrettJFX.getPane());
			isEnPassantValid = true;
			//System.out.println("en Passant!");
		}
		return isEnPassantValid;
	}

	public boolean zugPrüfen(){

		boolean isDrawValid = false;

		boolean validateDraw = derSchmeißende.zugErlaubt(
				derSchmeißende.getRow(),
				derSchmeißende.getCol(),
				zumSchmeißen.getRow(),
				zumSchmeißen.getCol());

		boolean validateDrawBeatOponentColour = derSchmeißende.isWhite() != zumSchmeißen.isWhite()
				&& whitePlays == derSchmeißende.isWhite();

		if(validateDraw && validateDrawBeatOponentColour)
		{
			isDrawValid = true;
		}
		return isDrawValid;
	}

	public void zugende() {
		whitePlays = !whitePlays;
		if (whitePlays)
			round++;
	}

	public void schmeißen(int row, int col) {
		if (getFiguren[row][col] != null) {
			getFiguren[row][col].schmeißen(spielbrettJFX.getPane());
			getFiguren[row][col] = null;
		}
	}

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

}

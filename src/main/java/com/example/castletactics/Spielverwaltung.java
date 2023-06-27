package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Spielverwaltung extends Application{
	int round = 0;
	boolean whitePlays = true;
	public final Spielbrett brett;
	Stage stage;
	final Tactics tactics;
	public Figur zumSchmeißen;
	public Figur derSchmeißende;
	public Figur enPassantKandidat = null;
	Figur[][] figuren;
	ArrayList<Figur> geschmissen;


	public Spielverwaltung(){
		figuren = new Figur[8][8];
		geschmissen = new ArrayList<>();
		brett = new Spielbrett(this);
		tactics = new Tactics(this);

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
			brett.start(stage);
		default:
			break;
		}
	}

	public boolean enPassantPrüfen(Bauer b, int destRow, int destCol)
	{
		if(enPassantKandidat != null && enPassantKandidat.col == destCol && Math.abs(enPassantKandidat.row-destRow) == 1
				&& enPassantKandidat.row == b.row && Math.abs(b.col - enPassantKandidat.col) == 1){
			enPassantKandidat.schmeißen(brett.pane);
			//System.out.println("en Passant!");
			return true;
		}
		return false;
	}
	
	public boolean zugPrüfen(){
		if(derSchmeißende.zugErlaubt(derSchmeißende.row, derSchmeißende.col, zumSchmeißen.row, zumSchmeißen.col) && derSchmeißende.isWhite != zumSchmeißen.isWhite && whitePlays == derSchmeißende.isWhite) {

			return true;
		}
		return false;
	}

	public void zugende() {
		whitePlays = !whitePlays;
		if (whitePlays)
			round++;
	}

	public void schmeißen(int row, int col) {
		if (figuren[row][col] != null) {
			figuren[row][col].schmeißen(brett.pane);
			figuren[row][col] = null;
		}
	}

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

}

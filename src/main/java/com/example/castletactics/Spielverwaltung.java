package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Spielverwaltung extends Application{
	int round = 0;
	boolean whitePlays = true;
	private final Spielbrett brett;
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

	public boolean enPassantPrüfen(Bauer b, int srcRow, int srcCol)
	{
		System.out.println("??");
		if(enPassantKandidat != null && enPassantKandidat.row == srcRow && Math.abs(enPassantKandidat.col-srcCol) == 1
				&& enPassantKandidat.col == b.col && Math.abs(b.row - enPassantKandidat.row) == 1){
			enPassantKandidat.schmeißen(brett.pane);
			System.out.println("enPassant!");
			return true;
		}
		//System.out.println("" + enPassantKandidat.row + enPassantKandidat.col + srcRow + srcCol);
		return false;
	}
	
	public boolean zugPrüfen(){
		if(derSchmeißende.zugErlaubt(derSchmeißende.row, derSchmeißende.col, zumSchmeißen.row, zumSchmeißen.col) && derSchmeißende.isWhite != zumSchmeißen.isWhite && whitePlays == derSchmeißende.isWhite) {
			brett.pane.getChildren().remove(derSchmeißende);
			brett.pane.add(derSchmeißende, zumSchmeißen.col, zumSchmeißen.row);
			geschmissen.add(figuren[zumSchmeißen.row][zumSchmeißen.col]);

			if(zumSchmeißen.row != derSchmeißende.row || zumSchmeißen.col != derSchmeißende.col) {
				figuren[zumSchmeißen.row][zumSchmeißen.col] = figuren[derSchmeißende.row][derSchmeißende.col];
				figuren[derSchmeißende.row][derSchmeißende.col] = null;
			}
			derSchmeißende.row = zumSchmeißen.row;
			derSchmeißende.col = zumSchmeißen.col;
			zumSchmeißen.schmeißen(brett.pane);
			return true;
		}
		return false;
	}

	public void zugende() {
		whitePlays = !whitePlays;
		if (whitePlays)
			round++;
	}

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

}

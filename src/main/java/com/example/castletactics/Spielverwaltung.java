package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;


public class Spielverwaltung extends Application{
	private final Spielbrett brett;
	public Figur[] getSchwarz;
	public Figur[] getWeiss;
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
		if (derSchmeißende.zugErlaubt(derSchmeißende.row, derSchmeißende.col, zumSchmeißen.row, zumSchmeißen.col) &&
				!brett.isOccupiedBySameColor(derSchmeißende.row, derSchmeißende.col, zumSchmeißen.row, zumSchmeißen.col)) {
			brett.pane.getChildren().remove(derSchmeißende);
			brett.pane.add(derSchmeißende, zumSchmeißen.col, zumSchmeißen.row);
			geschmissen.add(figuren[zumSchmeißen.row][zumSchmeißen.col]);
			figuren[zumSchmeißen.row][zumSchmeißen.col] = figuren[derSchmeißende.row][derSchmeißende.col];
			figuren[derSchmeißende.row][derSchmeißende.col] = null;
			derSchmeißende.row = zumSchmeißen.row;
			derSchmeißende.col = zumSchmeißen.col;
			zumSchmeißen.schmeißen(brett.pane);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					System.out.print((figuren[i][j]==null?0:1) + " ");
				}
				System.out.println();
			}
			//System.out.println("ja");
		}
		//System.out.println("nö");
		return true;
	}

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

	public boolean isOccupied(int row, int col) {
		return false;
	}

	public boolean isOccupiedBySameColor(boolean isWhite, int row, int col) {
		return isWhite;
	}

	public void setSpielbrett(Spielbrett spielbrett) {
	}
}

package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Spielverwaltung extends Application{
	private final Spielbrett brett;
	Stage stage;
	final Tactics tactics;
	public Figur zumSchmeißen;
	public Figur derSchmeißende;

	public Spielverwaltung(){
		ArrayList<Figur> schwarz = new ArrayList<>();
		ArrayList<Figur> weiss = new ArrayList<>();
		brett = new Spielbrett(schwarz, weiss, this);
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
	
	public void zugPrüfen(){
		if(derSchmeißende.zugErlaubt(derSchmeißende.row, derSchmeißende.col, zumSchmeißen.row, zumSchmeißen.col)) {
			brett.pane.getChildren().remove(derSchmeißende);
			brett.pane.add(derSchmeißende, zumSchmeißen.col, zumSchmeißen.row);
			derSchmeißende.row = zumSchmeißen.row;
			derSchmeißende.col = zumSchmeißen.col;
			zumSchmeißen.schmeißen(brett.pane);
			//System.out.println("ja");
		}
		//System.out.println("nö");


	}

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

}

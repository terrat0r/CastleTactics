package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Spielverwaltung extends Application{
	private Spielbrett brett;
	Stage stage;
	Tactics tactics;
	public Figur zumSchmeißen;
	public Figur schmeißer;
	private ArrayList<Figur> schwarz = new ArrayList<Figur>();
	private ArrayList<Figur> weiss = new ArrayList<Figur>();

	public Spielverwaltung(){
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

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

}

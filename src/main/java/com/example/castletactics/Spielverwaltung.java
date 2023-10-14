package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;


public class Spielverwaltung extends Application{
	public final Spielbrett brett;
	Stage stage;
	final Tactics tactics;
	public Zugverwaltung zugverwaltung;


	public Spielverwaltung(){
		zugverwaltung = new Zugverwaltung(this); //1st
		brett = new Spielbrett(this); //2nd
		tactics = new Tactics(this); //3rd
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

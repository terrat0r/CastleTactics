package com.example.castletactics;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
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
	
	public void zugPrüfen(){
		if(schmeißer.zugErlaubt(schmeißer.row, schmeißer.col, zumSchmeißen.row, zumSchmeißen.col)) {
			brett.pane.getChildren().remove(schmeißer);
			brett.pane.add(schmeißer, zumSchmeißen.col, zumSchmeißen.row);
			schmeißer.row = zumSchmeißen.row;
			schmeißer.col = zumSchmeißen.col;
			zumSchmeißen.schmeißen(brett.pane);
			//System.out.println("ja");
		}
		else{
			//System.out.println("nö");
		}


	}

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

}

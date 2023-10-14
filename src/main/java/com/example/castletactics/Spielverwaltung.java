package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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


	//TODO: send Move

	public boolean receiveMove(String move) {
		Pattern pattern = Pattern.compile("^(\\w*).*?\\[(\\d)[,;|] ?(\\d)] (?:nach|to) \\[(\\d)[,;|] ?(\\d)]"); //z.B. Bauer von [1,2] nach [2, 3]
		Matcher matcher = pattern.matcher(move);
		if (matcher.find()) { // Match gefunden
			String typ = matcher.group(1);
			int row = Integer.parseInt(matcher.group(2));
			int col = Integer.parseInt(matcher.group(3));
			int rowDest = Integer.parseInt(matcher.group(4));
			int colDest = Integer.parseInt(matcher.group(5));
			zugverwaltung.figuren[row][col].move(rowDest, colDest);
			return true;
		}
		return false;
	}

}

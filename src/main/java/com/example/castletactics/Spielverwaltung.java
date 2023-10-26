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

	public void serverStarten (){
		zugverwaltung.netzwerk = new Netzwerk();
		zugverwaltung.netzwerkWeiß = true;
	}

	public void clientStarten(String strIP){
		int[] IP = new int[4];
		Pattern pattern = Pattern.compile("(\\d{0,3}).(\\d{0,3}).(\\d{0,3}).(\\d{0,3})"); //z.B. "Bauer von [1,2] nach [2, 3]"
		Matcher matcher = pattern.matcher(strIP);
		if (matcher.find()) { // Match gefunden
			IP[0] = Integer.parseInt(matcher.group(1));
			IP[1] = Integer.parseInt(matcher.group(2));
			IP[2] = Integer.parseInt(matcher.group(3));
			IP[3] = Integer.parseInt(matcher.group(4));
		}
		zugverwaltung.netzwerk = new Netzwerk(IP);
		zugverwaltung.netzwerkWeiß = false;
	}

	public static void main(String[] args) {
		launch();
		//new Spielverwaltung();
	}

	//TODO: Anbinden
	public void sendMove(String typ, int row, int col, int rowDest, int colDest) {
		String s = typ + " von [" + row + "," + col + "] nach [" + rowDest + "," + colDest + "]";
		zugverwaltung.netzwerk.senden(s);
		System.out.println("Senden: " + s);
	}

	public boolean receiveMove(String move) {
		System.out.println("Erhalten: " + move);
		Pattern pattern = Pattern.compile("^(\\w*).*?\\[(\\d)[,;|] ?(\\d)] (?:nach|to) \\[(\\d)[,;|] ?(\\d)]"); //z.B. "Bauer von [1,2] nach [2, 3]"
		Matcher matcher = pattern.matcher(move);
		if (matcher.find()) { // Match gefunden
			String typ = matcher.group(1);
			int row = Integer.parseInt(matcher.group(2));
			int col = Integer.parseInt(matcher.group(3));
			int rowDest = Integer.parseInt(matcher.group(4));
			int colDest = Integer.parseInt(matcher.group(5));
			zugverwaltung.move(row, col, rowDest, colDest);
			return true;
		}
		return false;
	}

}

package com.example.castletactics;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Tactics extends Application {
    final Scene scene;
    final Button lokalSpielenBTN;

    public Tactics(Spielverwaltung spv)
    {
        VBox root = new VBox(); // Vertikales Layout-Container
        scene = new Scene(root);

        lokalSpielenBTN = new Button("Spielbrett öffnen");
        lokalSpielenBTN.setOnAction(l-> spv.fensterWechseln("Schach"));
        TextField spielbrettIPTF = new TextField();
        spielbrettIPTF.setPromptText("Hier die IP-Adresse");
        //spielbrettIPTF.setOnAction(l->);
        Button lanJoinBTN = new Button("Lan Join");
        Button lanHostBTN = new Button("LAN Host");
        lanJoinBTN.setOnAction(l->{spv.clientStarten(spielbrettIPTF.getText());spv.fensterWechseln("Schach");});
        lanHostBTN.setOnAction(l->{spv.serverStarten();spv.fensterWechseln("Schach");});
        root.getChildren().addAll(lokalSpielenBTN,spielbrettIPTF,lanJoinBTN,lanHostBTN); // Elemente werden der VBox hinzugefügt

        scene.getStylesheets().add("/style.css");
    }
    @Override
    public void start(Stage stage) {

        //Scene wird erstellt
        stage.setScene(scene);
        stage.setTitle("Castle Tactics");
        stage.setResizable(true);
        //stage.setFullScreen(true);
        stage.show();
        //stage.setHeight(100);
        //stage.setWidth(200);
        stage.show();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.2;
        double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.1;
        stage.setX(x);
        stage.setY(y);
    }

}
package com.example.castletactics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Tactics extends Application {
    Group root;
    Scene scene;
    Button spielbrettBTN;

    public Tactics(Spielverwaltung spv)
    {
        root = new Group();
        scene = new Scene(root);

        spielbrettBTN = new Button("Spielbrett öffnen");
        spielbrettBTN.setOnAction(l-> spv.fensterWechseln("Schach"));
        root.getChildren().add(spielbrettBTN);

        scene.getStylesheets().add("/style.css");
    }
    @Override
    public void start(Stage stage) {

        //Scene wird erstellt
        stage.setScene(scene);
        stage.setTitle("Castle Tactics");
        stage.setResizable(false);
        //stage.setFullScreen(true);
        stage.show();
    }

}
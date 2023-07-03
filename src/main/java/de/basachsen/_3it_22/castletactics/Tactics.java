package de.basachsen._3it_22.castletactics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Tactics extends Application {
    final Group root;
    final Scene scene;
    final Button spielbrettBTN;

    public Tactics(Spielverwaltung spielverwaltung) {
        root = new Group();
        scene = new Scene(root);

        spielbrettBTN = new Button("SpielbrettJFX öffnen");
        spielbrettBTN.setOnAction(l-> spielverwaltung.fensterWechseln("Schach"));
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
package com.example.castletactics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Tactics extends Application {
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root,500,500);
        //css

        Button spielbrettBTN = new Button("Spielbrett öffnen");
        spielbrettBTN.setOnAction(l-> new Spielbrett().start(stage));
        root.getChildren().add(spielbrettBTN);

        scene.getStylesheets().add("/style.css");
        //Scene wird erstellt
        stage.setScene(scene);
        stage.setTitle("Castle Tactics");
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
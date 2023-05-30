package com.example.castletactics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Tactics extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();
        Scene scene = new Scene(root,500,500);
        //css
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
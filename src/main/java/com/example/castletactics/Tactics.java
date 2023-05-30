package com.example.castletactics;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Tactics extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.castletactics;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Tactics extends Application {
    final Scene scene;
    final Button lokalSpielenBTN;

    public Tactics(Spielverwaltung spv)
    {
        StackPane Pane = new StackPane();
        VBox root = new VBox(); // Vertikales Layout-Container
        root.setAlignment(Pos.CENTER);


        lokalSpielenBTN = new Button("Spielbrett öffnen");
        lokalSpielenBTN.setOnAction(l-> spv.fensterWechseln("Schach"));
        TextField spielbrettIPTF = new TextField();
        spielbrettIPTF.setPromptText("Hier die IP-Adresse");
        spielbrettIPTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}(\\.\\d{0,3}){0,3}")) {
                spielbrettIPTF.setText(oldValue);
            } else {
                String[] blocks = newValue.split("\\.");
                boolean valid = true;
                for (String block : blocks) {
                    try {
                        int value = Integer.parseInt(block);
                        if (value < 0 || value > 255) {
                            valid = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) {
                    spielbrettIPTF.setText(oldValue);
                }
            }
        });



        //spielbrettIPTF.setOnAction(l->);
        Button lanJoinBTN = new Button("Lan Join");
        Button lanHostBTN = new Button("LAN Host");
        lanJoinBTN.setOnAction(l->{spv.clientStarten(spielbrettIPTF.getText());spv.fensterWechseln("Schach");});
        lanHostBTN.setOnAction(l->{
            ProgressIndicator pi = new ProgressIndicator();
            VBox box = new VBox(pi);
            box.setAlignment(Pos.CENTER);
            // Grey Background
            root.setDisable(true);
            Pane.getChildren().add(box);
            //spv.serverStarten();
            //spv.fensterWechseln("Schach");
        });
        root.getChildren().addAll(lokalSpielenBTN,spielbrettIPTF,lanJoinBTN,lanHostBTN); // Elemente werden der VBox hinzugefügt
        Pane.getChildren().add(root);
        //Styling nodes
        lokalSpielenBTN.setStyle("-fx-background-color: A37754FF; -fx-text-fill: white;");
        lanJoinBTN.setStyle("-fx-background-color: A37754FF; -fx-text-fill: white;");
        lanHostBTN.setStyle("-fx-background-color: A37754FF; -fx-text-fill: white;");
        spielbrettIPTF.setStyle("-fx-font: normal bold 20px 'serif'");
        root.setStyle("-fx-background-color: F3EBD7FF");
        scene = new Scene(Pane);



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
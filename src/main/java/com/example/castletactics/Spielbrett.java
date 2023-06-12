package com.example.castletactics;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Spielbrett extends Tactics {
    public void start(Stage primaryStage) {
        // Create a GridPane
        GridPane pane = new GridPane();
        Button schließenBTN = new Button("Schließen");
        schließenBTN.setOnAction(l-> new Tactics().start(primaryStage));

        // Create 64 rectangles and add to pane
        int count = 0;
        double s = 50; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(s, s, s, s);
                if (count % 2 == 1)
                    r.setFill(Color.WHITE);
                pane.add(r, j, i);
                count++;
            }
        }

        pane.add(schließenBTN, 0, 8, 2, 1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene); //  scene in  primaryStage gesetzt
        primaryStage.show();

    }

}

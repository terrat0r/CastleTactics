package com.example.castletactics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Spielbrett extends Application {
    final GridPane pane;
    final Button schliessenBTN;
    final Scene scene;
    // --Commented out by Inspection (21.06.23, 16:44):private Spielverwaltung spv;

    Spielbrett(ArrayList<Figur> schwarz, ArrayList<Figur> weiss, Spielverwaltung spv){
        pane = new GridPane();
        schliessenBTN = new Button("Schließen");
        schliessenBTN.setOnAction(l-> spv.fensterWechseln("Hauptmenü"));

        // Create 64 rectangles and add to pane
        int count = 0;
        int s = 115; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(s, s, s, s);
                r.setOnDragOver(event -> {
                    /* data is dragged over the target */
                    /* accept it only if it is not dragged from the same node
                     * and if it has a string data */
                    if (event.getGestureSource() != r &&
                            event.getDragboard().hasImage()) {
                        /* allow for moving */
                        event.acceptTransferModes(TransferMode.ANY);
                    }

                    event.consume();
                });
                r.setOnDragDropped(event -> {
                    /* data is dragged over the target */
                    /* accept it only if it is not dragged from the same node
                     * and if it has a string data */

                    boolean success = false;
                    if (event.getGestureSource() != r &&
                            event.getDragboard().hasImage()) {
                        /* allow for moving */
                        event.acceptTransferModes(TransferMode.ANY);

                        success = true;
                    }
                    event.setDropCompleted(success);

                    event.consume();
                });
                r.setFill(Color.rgb(243,235,215));
                if (count % 2 == 0)
                    r.setFill(Color.rgb(163,119,84));
                pane.add(r, j, i);
                count++;
            }
        }

        //Figuren
        schwarz.add(new Bauer(pane, false, s, 0, 1, spv));
        schwarz.add(new Bauer(pane, false, s, 1, 1, spv));
        schwarz.add(new Bauer(pane, false, s, 2, 1, spv));
        schwarz.add(new König(pane, false, s, 4, 3, spv));
        schwarz.add(new Läufer(pane, false, s, 7,7, spv));
        schwarz.add(new Turm(pane, false, s, 6,7, spv));
        weiss.add(new Turm(pane, true, s, 5, 7, spv));

        //Button Hauptmenü
        pane.add(schliessenBTN, 0, 8, 2, 1);
        scene = new Scene(pane);

    }

    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene);//scene in  primaryStage gesetzt
        primaryStage.show();

    }

}

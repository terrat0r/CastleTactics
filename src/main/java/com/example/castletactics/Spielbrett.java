package com.example.castletactics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    TextArea textArea;
    private Spielverwaltung spv;
    // --Commented out by Inspection (21.06.23, 16:44):private Spielverwaltung spv;

    Spielbrett(Spielverwaltung spv){
        pane = new GridPane();
        schliessenBTN = new Button("Schließen");
        schliessenBTN.setOnAction(l-> spv.fensterWechseln("Hauptmenü"));


        // Create 64 rectangles and add to pane
        int count = 0;
        int s = 75; // side of rectangle
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
        spv.figuren[1][0] = new Bauer(pane, false, s, 0, 1, spv);
        spv.figuren[1][1] = new Bauer(pane, false, s, 1, 1, spv);
        spv.figuren[1][2] = new Bauer(pane, false, s, 2, 1, spv);
        spv.figuren[1][3] = new Bauer(pane, false, s, 3, 1, spv);
        spv.figuren[1][4] = new Bauer(pane, false, s, 4, 1, spv);
        spv.figuren[1][5] = new Bauer(pane, false, s, 5, 1, spv);
        spv.figuren[1][6] = new Bauer(pane, false, s, 6, 1, spv);
        spv.figuren[1][7] = new Bauer(pane, false, s, 7, 1, spv);
        spv.figuren[0][0] = new Turm(pane, false, s, 0, 0, spv);
        spv.figuren[0][7] = new Turm(pane, false, s, 7, 0, spv);
        spv.figuren[0][1] = new Springer(pane, false, s, 1, 0, spv);
        spv.figuren[0][6] = new Springer(pane, false, s, 6, 0, spv);
        spv.figuren[0][2] = new Läufer(pane, false, s, 2, 0, spv);
        spv.figuren[0][5] = new Läufer(pane, false, s, 5, 0, spv);
        spv.figuren[0][3] = new Königin(pane, false, s, 3, 0, spv);
        spv.figuren[0][4] = new König(pane, false, s, 4, 0, spv);


        spv.figuren[6][0] = (new Bauer(pane, true, s, 0, 6, spv));
        spv.figuren[6][1] = (new Bauer(pane, true, s, 1, 6, spv));
        spv.figuren[6][2] = (new Bauer(pane, true, s, 2, 6, spv));
        spv.figuren[6][3] = (new Bauer(pane, true, s, 3, 6, spv));
        spv.figuren[6][4] = (new Bauer(pane, true, s, 4, 6, spv));
        spv.figuren[6][5] = (new Bauer(pane, true, s, 5, 6, spv));
        spv.figuren[6][6] = (new Bauer(pane, true, s, 6, 6, spv));
        spv.figuren[6][7] = (new Bauer(pane, true, s, 7, 6, spv));
        spv.figuren[7][0] = (new Turm(pane, true, s, 0, 7, spv));
        spv.figuren[7][7] = (new Turm(pane, true, s, 7, 7, spv));
        spv.figuren[7][1] = (new Springer(pane, true, s, 1, 7, spv));
        spv.figuren[7][6] = (new Springer(pane, true, s, 6, 7, spv));
        spv.figuren[7][2] = (new Läufer(pane, true, s, 2, 7, spv));
        spv.figuren[7][5] = (new Läufer(pane, true, s, 5, 7, spv));
        spv.figuren[7][3] = (new Königin(pane, true, s, 3, 7, spv));
        spv.figuren[7][4] = (new König(pane, true, s, 4, 7, spv));

        //Button Hauptmenü
        textArea = new TextArea();
        pane.add(textArea,8,0,1,8);
        pane.add(schliessenBTN, 0, 8, 2, 1);
        textArea.setEditable(false);
        scene = new Scene(pane);
        schliessenBTN.requestFocus();


        this.spv = spv;
    }

    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene);//scene in  primaryStage gesetzt
        primaryStage.show();

        //primaryStage.setWidth(pane.getWidth() + 150);
        //primaryStage.setHeight(pane.getHeight() + 100);

    }
}

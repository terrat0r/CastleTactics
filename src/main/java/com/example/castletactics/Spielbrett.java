package com.example.castletactics;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Spielbrett extends Tactics {
        private Rectangle draggedPiece;
    public void start(Stage primaryStage) {
        // Create a GridPane
        GridPane pane = new GridPane();
        Button schliessenBTN = new Button("Schließen");
        schliessenBTN.setOnAction(l-> new Tactics().start(primaryStage));

        // Create 64 rectangles and add to pane
        int count = 0;
        int s = 115; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(s, s, s, s);
                r.setOnDragOver(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
                        /* data is dragged over the target */
                        /* accept it only if it is not dragged from the same node
                         * and if it has a string data */
                        if (event.getGestureSource() != r &&
                                event.getDragboard().hasImage()) {
                            /* allow for moving */
                            event.acceptTransferModes(TransferMode.ANY);
                        }

                        event.consume();
                    }
                });
                r.setOnDragDropped(new EventHandler<DragEvent>() {
                    public void handle(DragEvent event) {
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
                    }
                });
                r.setFill(Color.rgb(243,235,215));
                if (count % 2 == 0)
                    r.setFill(Color.rgb(163,119,84));
                pane.add(r, j, i);
                count++;
            }
        }
        //Figuren

        //black pawn
        Bauer bauers1 = new Bauer(pane, false, s, 0, 1);
        Bauer bauers2 = new Bauer(pane, false, s, 1, 1);
        Bauer bauers3 = new Bauer(pane, false, s, 2, 1);
        Bauer bauers4 = new Bauer(pane, false, s, 3, 1);
        Bauer bauers5 = new Bauer(pane, false, s, 4, 1);
        Bauer bauers6 = new Bauer(pane, false, s, 5, 1);
        Bauer bauers7 = new Bauer(pane, false, s, 6, 1);
        Bauer bauers8 = new Bauer(pane, false, s, 7, 1);
        //white pawn
        Bauer bauerw1 = new Bauer(pane, true, s, 0,6);
        Bauer bauerw2 = new Bauer(pane, true, s, 1,6);
        Bauer bauerw3 = new Bauer(pane, true, s, 2,6);
        Bauer bauerw4 = new Bauer(pane, true, s, 3,6);
        Bauer bauerw5 = new Bauer(pane, true, s, 4,6);
        Bauer bauerw6 = new Bauer(pane, true, s, 5,6);
        Bauer bauerw7 = new Bauer(pane, true, s, 6,6);
        Bauer bauerw8 = new Bauer(pane, true, s, 7,6);
        //black King
        König koenigs = new König(pane, false, s, 4, 0);
        //white King
        König koenigsw = new König(pane, true, s, 4, 7);
        //black Queen
        Königin queens = new Königin(pane, false, s, 3, 0);
        //white Queen
        Königin queenw = new Königin(pane, true, s, 3, 7);
        //black bishops
        Läufer läufers1 = new Läufer(pane, false, s, 2,0);
        Läufer läufers2 = new Läufer(pane, false, s, 5,0);
        //white bishops
        Läufer läuferw1 = new Läufer(pane, true, s, 2,7);
        Läufer läuferw2 = new Läufer(pane, true, s, 5,7);
        //black knight
        Springer springers1 = new Springer(pane, false, s, 1,0);
        Springer springers2 = new Springer(pane, false, s, 6,0);
        //white knight
        Springer springerw1 = new Springer(pane, true, s, 1,7);
        Springer springerw2 = new Springer(pane, true, s, 6,7);
        //black rook
        Turm turms1 = new Turm(pane, false, s, 0,0);
        Turm turms2 = new Turm(pane, false, s, 7,0);
        //white rook
        Turm turmw1 = new Turm(pane, true, s, 0,7);
        Turm turmw2 = new Turm(pane, true, s, 7,7);

        //Button Hauptmenü
        pane.add(schliessenBTN, 0, 8, 2, 1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene);//scene in  primaryStage gesetzt
        primaryStage.show();

    }

}

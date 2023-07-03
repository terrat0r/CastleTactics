package de.basachsen._3it_22.castletactics.jfx;

import de.basachsen._3it_22.castletactics.*;
import de.basachsen._3it_22.castletactics.figures.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SpielbrettJFX extends Application {
    private final GridPane pane;
    private final Button schliessenBTN;
    private final Scene scene;
    private TextArea textArea;
    private Spielverwaltung spielverwaltung;
    // --Commented out by Inspection (21.06.23, 16:44):private Spielverwaltung spielverwaltung;

    public GridPane getPane() {
        return pane;
    }

    public Scene getScene() {
        return scene;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public SpielbrettJFX(Spielverwaltung spielverwaltung){
        pane = new GridPane();
        schliessenBTN = new Button("Schließen");
        schliessenBTN.setOnAction(l-> spielverwaltung.fensterWechseln("Hauptmenü"));


        // Create 64 rectangles and add to pane
        int count = 0;
        int s = 75; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(s, s, s, s);
                //TODO Lamba Funktion und Stream API, Erklärung warum wir es verwenden
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



        FigurJFX[][] figur = spielverwaltung.getFiguren();

        //Figuren
        figur[1][0] = new Bauer(pane, false, s, 0, 1, spielverwaltung);
        figur[1][1] = new Bauer(pane, false, s, 1, 1, spielverwaltung);
        figur[1][2] = new Bauer(pane, false, s, 2, 1, spielverwaltung);
        figur[1][3] = new Bauer(pane, false, s, 3, 1, spielverwaltung);
        figur[1][4] = new Bauer(pane, false, s, 4, 1, spielverwaltung);
        figur[1][5] = new Bauer(pane, false, s, 5, 1, spielverwaltung);
        figur[1][6] = new Bauer(pane, false, s, 6, 1, spielverwaltung);
        figur[1][7] = new Bauer(pane, false, s, 7, 1, spielverwaltung);
        figur[0][0] = new Turm(pane, false, s, 0, 0, spielverwaltung);
        figur[0][7] = new Turm(pane, false, s, 7, 0, spielverwaltung);
        figur[0][1] = new Springer(pane, false, s, 1, 0, spielverwaltung);
        figur[0][6] = new Springer(pane, false, s, 6, 0, spielverwaltung);
        figur[0][2] = new Läufer(pane, false, s, 2, 0, spielverwaltung);
        figur[0][5] = new Läufer(pane, false, s, 5, 0, spielverwaltung);
        figur[0][3] = new Königin(pane, false, s, 3, 0, spielverwaltung);
        figur[0][4] = new König(pane, false, s, 4, 0, spielverwaltung);


        figur[6][0] = (new Bauer(pane, true, s, 0, 6, spielverwaltung));
        figur[6][1] = (new Bauer(pane, true, s, 1, 6, spielverwaltung));
        figur[6][2] = (new Bauer(pane, true, s, 2, 6, spielverwaltung));
        figur[6][3] = (new Bauer(pane, true, s, 3, 6, spielverwaltung));
        figur[6][4] = (new Bauer(pane, true, s, 4, 6, spielverwaltung));
        figur[6][5] = (new Bauer(pane, true, s, 5, 6, spielverwaltung));
        figur[6][6] = (new Bauer(pane, true, s, 6, 6, spielverwaltung));
        figur[6][7] = (new Bauer(pane, true, s, 7, 6, spielverwaltung));
        figur[7][0] = (new Turm(pane, true, s, 0, 7, spielverwaltung));
        figur[7][7] = (new Turm(pane, true, s, 7, 7, spielverwaltung));
        figur[7][1] = (new Springer(pane, true, s, 1, 7, spielverwaltung));
        figur[7][6] = (new Springer(pane, true, s, 6, 7, spielverwaltung));
        figur[7][2] = (new Läufer(pane, true, s, 2, 7, spielverwaltung));
        figur[7][5] = (new Läufer(pane, true, s, 5, 7, spielverwaltung));
        figur[7][3] = (new Königin(pane, true, s, 3, 7, spielverwaltung));
        figur[7][4] = (new König(pane, true, s, 4, 7, spielverwaltung));

        //Button Hauptmenü
        textArea = new TextArea();
        pane.add(textArea,8,0,1,8);
        pane.add(schliessenBTN, 0, 8, 2, 1);
        scene = new Scene(pane);


        this.spielverwaltung = spielverwaltung;
    }

    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene);//scene in  primaryStage gesetzt
        primaryStage.show();

    }
}

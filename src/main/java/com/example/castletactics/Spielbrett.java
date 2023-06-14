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
        double s = 115; // side of rectangle
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                Rectangle r = new Rectangle(s, s, s, s);
                r.setFill(Color.rgb(243,235,215));
                if (count % 2 == 1)
                    r.setFill(Color.rgb(163,119,84));
                pane.add(r, j, i);
                count++;
            }
        }
        //Figuren
        Bauer bauers1 = new Bauer();
        Bauer bauers2 = new Bauer();

        Rectangle bauers1Rect = bauers1.getRect((int)s, true);
        Rectangle bauers2Rect = bauers2.getRect((int)s, true);

        bauers1Rect.setOnDragDetected(event -> {
            Dragboard dragboard = bauers1Rect.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("Bauers1");
            dragboard.setContent(content);
            event.consume();
        });

        bauers2Rect.setOnDragDetected(event -> {
            Dragboard dragboard = bauers2Rect.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("Bauers2");
            dragboard.setContent(content);
            event.consume();
        });

        pane.add(bauers1Rect, 0, 1);
        pane.add(bauers2Rect, 1, 1);

        pane.setOnDragOver(event -> {
            if (event.getGestureSource() != pane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        pane.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;
            if (dragboard.hasString()) {
                String source = dragboard.getString();
                Rectangle draggedPiece;
                switch (source) {
                    case "Bauers1":
                        draggedPiece = bauers1Rect;
                        break;
                    case "Bauers2":
                        draggedPiece = bauers2Rect;
                        break;
                    default:
                        draggedPiece = null;
                        break;
                }

                if (draggedPiece != null) {
                    int columnIndex = (int) Math.floor(event.getX() / bauers1Rect.getWidth());
                    int rowIndex = (int) Math.floor(event.getY() / bauers1Rect.getHeight());

                    pane.getChildren().remove(draggedPiece);
                    pane.add(draggedPiece, columnIndex, rowIndex);
                    success = true;
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });

        //Schwarze Bauern
        /*Bauer bauers1 = new Bauer();
        pane.add(bauers1.getRect((int) s, true),0,1);
        Bauer bauers2 = new Bauer();
        pane.add(bauers2.getRect((int) s, true),1,1);*/
        Bauer bauers3 = new Bauer();
        pane.add(bauers3.getRect((int) s, true),2,1);
        Bauer bauers4 = new Bauer();
        pane.add(bauers4.getRect((int) s, true),3,1);
        Bauer bauers5 = new Bauer();
        pane.add(bauers5.getRect((int) s, true),4,1);
        Bauer bauers6 = new Bauer();
        pane.add(bauers6.getRect((int) s, true),5,1);
        Bauer bauers7 = new Bauer();
        pane.add(bauers7.getRect((int) s, true),6,1);
        Bauer bauers8 = new Bauer();
        pane.add(bauers8.getRect((int) s, true),7,1);
        //Weisse Bauern
        Bauer bauerw1 = new Bauer();
        pane.add(bauerw1.getRect((int) s, false),0,6);
        Bauer bauerw2 = new Bauer();
        pane.add(bauerw2.getRect((int) s, false),1,6);
        Bauer bauerw3 = new Bauer();
        pane.add(bauerw3.getRect((int) s, false),2,6);
        Bauer bauerw4 = new Bauer();
        pane.add(bauerw4.getRect((int) s, false),3,6);
        Bauer bauerw5 = new Bauer();
        pane.add(bauerw5.getRect((int) s, false),4,6);
        Bauer bauerw6 = new Bauer();
        pane.add(bauerw6.getRect((int) s, false),5,6);
        Bauer bauerw7 = new Bauer();
        pane.add(bauerw7.getRect((int) s, false),6,6);
        Bauer bauerw8 = new Bauer();
        pane.add(bauerw8.getRect((int) s, false),7,6);
        //Schwarzer Koenig
        König koenigs = new König();
        pane.add(koenigs.getRect((int) s, true),4,0);
        //Weisser Koenig
        König koenigw = new König();
        pane.add(koenigw.getRect((int) s, false),4,7);
        //Schwarze Queen
        Königin koenigins = new Königin();
        pane.add(koenigins.getRect((int) s, true),3,0);
        //Weisser Queen
        Königin koeniginw = new Königin();
        pane.add(koeniginw.getRect((int) s, false),3,7);
        //SChwarze Laufer
        Läufer laufers1 = new Läufer();
        pane.add(laufers1.getRect((int) s, true),2,0);
        Läufer laufers2 = new Läufer();
        pane.add(laufers2.getRect((int) s, true),5,0);
        //Weisse Laufer
        Läufer lauferw1 = new Läufer();
        pane.add(lauferw1.getRect((int) s, false),2,7);
        Läufer lauferw2 = new Läufer();
        pane.add(lauferw2.getRect((int) s, false),5,7);
        //Schwarze Springer
        Springer springers1 = new Springer();
        pane.add(springers1.getRect((int) s, true),1,0);
        Springer springers2 = new Springer();
        pane.add(springers2.getRect((int) s, true),6,0);
        //Weisse Springer
        Springer springerw1 = new Springer();
        pane.add(springerw1.getRect((int) s, false),1,7);
        Springer springerw2 = new Springer();
        pane.add(springerw2.getRect((int) s, false),6,7);
        //Schwarze Tuerme
        Turm turms1 = new Turm();
        pane.add(turms1.getRect((int) s, true),0,0);
        Turm turms2 = new Turm();
        pane.add(turms2.getRect((int) s, true),7,0);
        //Weisse Tuerme
        Turm turmw1 = new Turm();
        pane.add(turmw1.getRect((int) s, false),0,7);
        Turm turmw2 = new Turm();
        pane.add(turmw2.getRect((int) s, false),7,7);

        //Button Hauptmenü
        pane.add(schliessenBTN, 0, 8, 2, 1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene);//scene in  primaryStage gesetzt
        primaryStage.show();

    }

}

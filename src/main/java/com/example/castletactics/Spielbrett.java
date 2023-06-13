package com.example.castletactics;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Spielbrett extends Tactics {
    public void start(Stage primaryStage) {
        // Create a GridPane
        GridPane pane = new GridPane();
        Button schliessenBTN = new Button("Schließen");
        schliessenBTN.setOnAction(l-> new Tactics().start(primaryStage));

        // Create 64 rectangles and add to pane
        int count = 0;
        double s = 125; // side of rectangle
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
        //Schwarze Bauern
        Bauer bauers1 = new Bauer();
        pane.add(bauers1.getRect((int) s, true),0,1);
        Bauer bauers2 = new Bauer();
        pane.add(bauers2.getRect((int) s, true),1,1);
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

        //Button Hauptmenü
        pane.add(schliessenBTN, 0, 8, 2, 1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene);//scene in  primaryStage gesetzt
        primaryStage.show();

    }

}

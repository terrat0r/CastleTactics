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
        double s = 100; // side of rectangle
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
        //Figuren
        Image image1 = new Image("file:./src/main/resources/img/chess-bishop-white.png");
        System.out.println(image1.toString());
        Rectangle rekt = new Rectangle(s,s,s,s);
        ImagePattern imagePattern = new ImagePattern(image1);
        rekt.setFill(imagePattern);
        Bauer bauer1 = new Bauer();
        pane.add(bauer1.getRect((int) s, true),1,0);

        pane.add(schliessenBTN, 0, 8, 2, 1);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Castle Tactics"); // Name des pane gesetzt
        primaryStage.setScene(scene);//scene in  primaryStage gesetzt
        primaryStage.show();

    }

}

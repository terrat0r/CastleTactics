module de.basachsen._3it_22.castletactics {
    requires javafx.controls;


    opens de.basachsen._3it_22.castletactics to javafx.fxml;
    exports de.basachsen._3it_22.castletactics;
    exports de.basachsen._3it_22.castletactics.jfx;
    opens de.basachsen._3it_22.castletactics.jfx to javafx.fxml;
    exports de.basachsen._3it_22.castletactics.figures;
    opens de.basachsen._3it_22.castletactics.figures to javafx.fxml;
}
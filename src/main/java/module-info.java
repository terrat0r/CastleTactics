module com.example.castletactics {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.castletactics to javafx.fxml;
    exports com.example.castletactics;
}
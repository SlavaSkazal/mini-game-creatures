module com.example.heads_hands {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.heads_hands to javafx.fxml;
    exports com.example.heads_hands;
}
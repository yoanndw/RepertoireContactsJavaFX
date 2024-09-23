module com.example.ex2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.ex2 to javafx.fxml;
    exports com.example.ex2;
    exports com.example.ex2.model;
    opens com.example.ex2.model to javafx.fxml;
    exports com.example.ex2.controller;
    opens com.example.ex2.controller to javafx.fxml;
}
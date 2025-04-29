module com.example.exercise34_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.exercise34_1 to javafx.fxml;
    exports com.example.exercise34_1;
}
module com.example.knk_gr1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.knk_gr1 to javafx.fxml;
    exports com.example.knk_gr1;
    exports com.example.database;
    exports com.example.models;
    exports com.example.repository;
}
module com.example.knk_gr1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.knk_gr1 to javafx.fxml;
    exports com.example.knk_gr1;
}
module com.example.knk_gr1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.web;


    exports com.example.knk_gr1;

//    opens com.example.knk_gr1 to javafx.fxml;
//    exports com.example.knk_gr1;
//    exports com.example.database;
//    exports com.example.models;
//    exports com.example.repository;

//    opens java2 to javafx.fxml;
//    exports java2;

    opens Controllers to javafx.fxml;
    exports Controllers;

    opens models to javafx.fxml;
    exports models;

    opens repository to javafx.fxml;
    exports repository;

    opens services to javafx.fxml;
    exports services;

    opens utils to javafx.fxml;
    exports utils;

    opens Application to javafx.fxml;
    exports Application;


}
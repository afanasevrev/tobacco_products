module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires log4j;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
}
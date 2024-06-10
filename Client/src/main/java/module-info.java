module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.web;
    requires log4j;
    requires spring.core;
    requires static lombok;
    opens com.example.client to javafx.fxml;
    exports com.example.client;
    exports com.example.client.controller;
    exports com.example.client.orders;
    opens com.example.client.controller to javafx.fxml;
    exports com.example.client.variables;
    opens com.example.client.variables to javafx.fxml;
}
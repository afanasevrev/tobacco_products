package com.example.Server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Контроллер для взаимодействия с приложением JavaFX
 */
@RestController
public class AppController {
    @GetMapping("/application")
    private String getApp() {
        return "Приложение по продаже табачной продукции";
    }
}

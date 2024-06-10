package com.example.Server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("/application")
    private String getApp() {
        return "Приложение по табачной продукции";
    }
}

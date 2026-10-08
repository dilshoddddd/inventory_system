package com.warehouse.inventory_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Аннотация, которая говорит Spring'у: "Этот класс будет обрабатывать веб-запросы"
@RestController
public class TestController {

    // Аннотация, которая говорит: "Если кто-то зайдет на адрес /test, выполни этот метод"
    @GetMapping("/test")
    public String testConnection() {
        return "Hallo Dilshod!";
    }
}
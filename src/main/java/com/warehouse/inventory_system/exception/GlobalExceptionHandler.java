package com.warehouse.inventory_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Эта аннотация говорит Spring: "Слушай все ошибки во всех контроллерах"
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Говорим: "Если кто-то выбросит ProductNotFoundException, выполни этот метод"
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(ProductNotFoundException ex) {

        // Создаем красивый JSON для ответа
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Not Found");
        errorResponse.put("message", ex.getMessage());

        // Возвращаем статус 404 (Not Found) и наш JSON
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
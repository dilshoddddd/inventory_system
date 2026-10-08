package com.warehouse.inventory_system.exception;

// RuntimeException означает, что эта ошибка может произойти во время работы программы
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
package com.warehouse.inventory_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

    @Id
    @NotBlank(message = "ID cannot be empty")
    private String id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Quantity needed")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity; // Замени int на Integer, чтобы он мог проверять на null

    public Product() {
    }

    public Product(String id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Integer getQuantity() { return quantity; }

    // Setters for updating the product
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
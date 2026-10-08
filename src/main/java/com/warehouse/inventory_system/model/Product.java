package com.warehouse.inventory_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate; // <-- Не забудь этот импорт!

@Entity
public class Product {

    @Id
    @NotBlank(message = "ID cannot be empty")
    private String id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "Quantity needed")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    private LocalDate expirationDate;

    public Product() {
    }

    public Product(String id, String name, String description, Integer quantity, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Integer getQuantity() { return quantity; }
    public LocalDate getExpirationDate() { return expirationDate; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
}
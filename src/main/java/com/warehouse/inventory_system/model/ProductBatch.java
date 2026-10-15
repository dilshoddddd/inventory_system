package com.warehouse.inventory_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class ProductBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference // Verhindert Endlosschleifen bei der JSON-Ausgabe im Swagger
    private Product product;

    @NotNull(message = "Quantity needed")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    private LocalDate expirationDate;

    public ProductBatch() {}

    public ProductBatch(Product product, Integer quantity, LocalDate expirationDate) {
        this.product = product;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
}
package com.warehouse.inventory_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @NotBlank(message = "ID cannot be empty")
    private String id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    private String description;

    // cascade = ALL: Löschen wir das Produkt, werden alle seine Chargen gelöscht.
    // orphanRemoval = true: Leere Chargen, die wir aus der Liste entfernen, werden aus der DB gelöscht.
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductBatch> batches = new ArrayList<>();

    public Product() {}

    public Product(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // NEW: Hilfsmethode, um die Gesamtmenge aller Chargen zu berechnen
    public int getTotalQuantity() {
        return batches.stream().mapToInt(ProductBatch::getQuantity).sum();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<ProductBatch> getBatches() { return batches; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setBatches(List<ProductBatch> batches) { this.batches = batches; }

    // Hilfsmethode zum Hinzufügen einer Charge
    public void addBatch(ProductBatch batch) {
        batches.add(batch);
        batch.setProduct(this);
    }
}
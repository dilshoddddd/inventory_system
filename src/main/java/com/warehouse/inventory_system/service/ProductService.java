package com.warehouse.inventory_system.service;

import com.warehouse.inventory_system.model.*;
import com.warehouse.inventory_system.repository.ProductRepository;
import com.warehouse.inventory_system.exception.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found!"));
    }

    public void updateProduct(String id, Product updatedData) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Cannot update. Product not found!"));

        existingProduct.setName(updatedData.getName());
        existingProduct.setDescription(updatedData.getDescription());
        // Quantity & Expiration Date ändern wir hier nicht mehr – sie gehören zu den Chargen!
        productRepository.save(existingProduct);
    }

    // new charge
    public void addBatch(String productId, Integer quantity, LocalDate expirationDate) {
        Product product = getProductById(productId);
        ProductBatch newBatch = new ProductBatch(product, quantity, expirationDate);
        product.addBatch(newBatch);
        productRepository.save(product);
    }

    // Delete a product by its ID
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    // Selling
    public void sellProduct(String id, Integer amountToSell) {
        Product product = getProductById(id);

        if (product.getTotalQuantity() < amountToSell) {
            throw new NotEnoughStockException("Only " + product.getTotalQuantity() + " items left in stock!");
        }

        List<ProductBatch> batches = product.getBatches();
        // Sortiere Chargen nach MHD (frühestes zuerst, null am Ende)
        batches.sort(java.util.Comparator.comparing(ProductBatch::getExpirationDate,
                java.util.Comparator.nullsLast(java.util.Comparator.naturalOrder())));

        int remainingToSell = amountToSell;

        for (ProductBatch batch : batches) {
            if (remainingToSell <= 0) break;

            if (batch.getQuantity() <= remainingToSell) {
                // Diese Charge wird komplett aufgebraucht
                remainingToSell -= batch.getQuantity();
                batch.setQuantity(0);
            } else {
                // Diese Charge wird nur teilweise aufgebraucht
                batch.setQuantity(batch.getQuantity() - remainingToSell);
                remainingToSell = 0;
            }
        }

        // Alle Chargen entfernen, deren Menge auf 0 gefallen ist (orphanRemoval löscht sie aus der DB)
        batches.removeIf(batch -> batch.getQuantity() == 0);

        productRepository.save(product);
    }

}
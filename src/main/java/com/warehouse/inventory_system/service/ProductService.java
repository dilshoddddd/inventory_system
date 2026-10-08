package com.warehouse.inventory_system.service;

import com.warehouse.inventory_system.model.Product;
import com.warehouse.inventory_system.repository.ProductRepository;
import com.warehouse.inventory_system.exception.*;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new ProductNotFoundException("Cannot update. Product with ID " + id + " not found!"));

        existingProduct.setName(updatedData.getName());
        existingProduct.setQuantity(updatedData.getQuantity());
        productRepository.save(existingProduct);
    }

    // NEW: Delete a product by its ID
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

}
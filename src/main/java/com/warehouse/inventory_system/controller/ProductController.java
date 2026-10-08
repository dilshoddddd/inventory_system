package com.warehouse.inventory_system.controller;

import com.warehouse.inventory_system.model.Product;
import com.warehouse.inventory_system.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public String addProduct(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        // Changed to English
        return "Product added successfully!";
    }

    // NEW: @PathVariable takes the {id} from the URL and puts it into the String id variable
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    // NEW: HTTP DELETE method
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }

    // NEW: HTTP PUT method for updating
    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        productService.updateProduct(id, product);
        return "Product updated successfully!";
    }

    @PostMapping("/products/{id}/sell")
    public String sellProduct(@PathVariable String id, @RequestParam Integer amount) {
        productService.sellProduct(id, amount);
        return amount + " items sold successfully!";
    }
}
package com.warehouse.inventory_system.controller;

import com.warehouse.inventory_system.model.Product;
import com.warehouse.inventory_system.service.ProductService;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebController {

    private final ProductService productService;

    public WebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(@NonNull Model model) {
        model.addAttribute("products", productService.getAllProducts());
        // Добавляем пустой объект Product для формы добавления
        model.addAttribute("newProduct", new Product());
        return "index";
    }

    @PostMapping("/web/products/add")
    public String addProductFromWeb(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            productService.addProduct(product);
            redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding product: " + e.getMessage());
        }
        return "redirect:/"; // Перенаправляем обратно на главную
    }

    @PostMapping("/web/products/sell")
    public String sellProductFromWeb(@RequestParam String id, @RequestParam Integer amount, RedirectAttributes redirectAttributes) {
        try {
            productService.sellProduct(id, amount);
            redirectAttributes.addFlashAttribute("successMessage", amount + " items of product " + id + " sold successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error selling product: " + e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/web/products/delete")
    public String deleteProductFromWeb(@RequestParam String id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product " + id + " deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product: " + e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/web/products/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.getProductById(id);
            model.addAttribute("product", product);
            return "edit-product"; // Имя нового HTML файла (создадим на следующем шаге)
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found: " + e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/web/products/edit/{id}")
    public String updateProductFromWeb(@PathVariable String id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            productService.updateProduct(id, product);
            redirectAttributes.addFlashAttribute("successMessage", "Product " + id + " updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating product: " + e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/web/products/batches/add")
    public String addBatchFromWeb(@RequestParam String productId,
                                  @RequestParam Integer quantity,
                                  @RequestParam(required = false) java.time.LocalDate expirationDate,
                                  RedirectAttributes redirectAttributes) {
        try {
            productService.addBatch(productId, quantity, expirationDate);
            redirectAttributes.addFlashAttribute("successMessage", "Batch added successfully to product " + productId);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding batch: " + e.getMessage());
        }
        return "redirect:/";
    }
}
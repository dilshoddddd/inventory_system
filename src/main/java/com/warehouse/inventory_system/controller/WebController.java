package com.warehouse.inventory_system.controller;

import com.warehouse.inventory_system.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final ProductService productService;

    public WebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Берем все товары из базы
        model.addAttribute("products", productService.getAllProducts());

        return "index";
    }
}
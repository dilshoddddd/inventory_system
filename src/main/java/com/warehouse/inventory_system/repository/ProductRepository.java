package com.warehouse.inventory_system.repository;

import com.warehouse.inventory_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<Что сохраняем, Тип его ID>
public interface ProductRepository extends JpaRepository<Product, String> {
    // Здесь ПУСТО! Spring сам сгенерирует все методы: save(), findAll(), deleteById() и т.д.
}
# 📦 Warehouse Inventory Management API

A robust RESTful API for managing warehouse inventory, built with **Java 21** and **Spring Boot**.
This project demonstrates enterprise-level backend architecture including layered design (Controller-Service-Repository), data validation, global exception handling, and API documentation.

## 🚀 Technologies Used
* **Java 21** (LTS)
* **Spring Boot 3** (Web, Data JPA, Validation)
* **H2 Database** (File-based storage for persistence)
* **Hibernate / JPA** (ORM)
* **Swagger / OpenAPI** (API Documentation)
* **Maven** (Build Tool)

## 🏗️ Architecture & Features
* **N-Tier Architecture:** Clean separation of concerns (Controllers, Services, Repositories).
* **Full CRUD Operations:** Create, Read, Update, and Delete products.
* **Data Validation:** Prevents invalid data (e.g., negative quantities, empty names) from entering the database.
* **Global Exception Handling:** Custom `ProductNotFoundException` intercepted by `@RestControllerAdvice` to return standardized JSON error responses (HTTP 404).
* **Persistent Storage:** Data survives application restarts using a file-based H2 database.

## 🔌 API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/products` | Get a list of all products |
| `GET` | `/products/{id}` | Get a specific product by ID |
| `POST` | `/products` | Add a new product to the inventory |
| `PUT` | `/products/{id}` | Update an existing product (name/quantity) |
| `DELETE` | `/products/{id}` | Remove a product from the inventory |

## 🛠️ How to Run Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/dilshoddddd/inventory_system.git
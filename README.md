# Medicine Management System API

A comprehensive RESTful API built with Java and Spring Boot for managing pharmacy inventory, categories, and medicine stock. The system implements robust input validation, custom exception handling, and business-specific queries to track medical supplies efficiently.

## Tech Stack
* Java 21
* Spring Boot 3 (Web, Data JPA, Validation)
* PostgreSQL
* MapStruct (for object mapping)
* Lombok (to reduce boilerplate)
* Swagger/OpenAPI (for API documentation)

## Key Features
* Inventory Management: Full CRUD operations for medicines and categories with a One-to-Many relational mapping.
* Business Logic Endpoints: Custom queries to fetch expired medicines, low-stock items, and search by scientific/commercial names.
* Global Exception Handling: Centralized `@RestControllerAdvice` to manage EntityNotFound, Validation errors, and unexpected server errors with structured JSON responses.
* Data Validation: Strict input validation using `jakarta.validation` (e.g., future expiry dates, positive pricing, non-negative stock).
* DTO Pattern: Secure data transfer utilizing Request and Response DTOs powered by MapStruct.

## Getting Started

### Prerequisites
* Java 21+ installed
* PostgreSQL installed and running locally
* Maven (or use the provided mvnw wrapper)

### Installation & Running

1. Database Setup:
   Create a local PostgreSQL database named `medicine_db`.
   Ensure your username and password in `src/main/resources/application.properties` match your local credentials.

2. Run the Spring Boot application:
   ./mvnw spring-boot:run

3. Access Swagger UI:
   Once the application is running, open your browser and navigate to:
   http://localhost:8080/swagger-ui.html

## Key API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/v1/medicines/low-stock | Retrieve medicines falling below a certain stock threshold |
| GET | /api/v1/medicines/expired | Retrieve all expired medications |
| PATCH | /api/v1/medicines/{id}/stock | Quickly update the stock quantity of a specific medicine |
| GET | /api/v1/categories/{id}/medicines | Fetch all medicines belonging to a specific category |

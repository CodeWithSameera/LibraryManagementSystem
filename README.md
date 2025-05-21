
# Library Management System API

## Overview

This project is a RESTful API for managing a library system, built using **Spring Boot**. It supports book and borrower management with operations like borrowing and returning books.

---

## How to Use

### Running the Project

The application supports multiple environments with proper configuration and profiles:

- **Development (`dev` profile)** uses an in-memory H2 database for quick setup and fast development cycles.
- **Production (`prod` profile)** uses PostgreSQL, providing a robust, scalable, and enterprise-grade database solution.

Run the project with the desired profile using:


# Development environment (H2)
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Production environment (PostgreSQL)
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod


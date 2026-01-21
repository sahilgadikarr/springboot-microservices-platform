# User–Order Microservices Platform

This repository contains a **Spring Boot–based microservices application** demonstrating a user–order domain with **RESTful inter-service communication**, **service discovery**, and a **centralized API Gateway**.  
The project was built to understand **microservices architecture concepts** using Spring Cloud components.

---

## Architecture Overview

The system consists of the following components:

### 1. Eureka Server (Service Discovery)
- **Port:** 8761  
- **Purpose:** Central service registry for dynamic discovery  
- **Dashboard:** http://localhost:8761  

---

### 2. API Gateway
- **Port:** 8080  
- **Purpose:** Single entry point for client requests, routing, and load balancing  

**Configured Routes:**
- `/users/**` → User Service  
- `/orders/**` → Order Service  

---

### 3. User Service
- **Port:** 8081  
- **Purpose:** Manages user-related data  
- **Database:** H2 (in-memory)

**Endpoints:**
- `GET /users` – Retrieve all users  
- `GET /users/{id}` – Retrieve user by ID  
- `GET /users/name/{name}` – Retrieve user by name  

> This service is consumed by the Order Service using **OpenFeign** and **WebClient**.

---

### 4. Order Service
- **Port:** 8082  
- **Purpose:** Manages orders and demonstrates service-to-service communication  
- **Database:** H2 (in-memory)

**Endpoints:**
- `GET /orders` – Retrieve all orders  
- `GET /orders/{id}/with-user` – Fetch order with user details (via OpenFeign)  
- `GET /orders/{id}/{name}/with-user-name` – Fetch order with user name details (via WebClient)

---

## Technology Stack

- Java 17  
- Spring Boot  
- Spring Cloud  
- Eureka Server  
- API Gateway  
- OpenFeign  
- WebClient  
- H2 Database  
- Maven  

---

## Getting Started

### Prerequisites
- Java 17  
- Maven  

### Build
```bash
mvn clean install

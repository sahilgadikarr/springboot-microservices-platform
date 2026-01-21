# User Order Microservice Demo

I have written this example for showcasing abilities of SpringBoot and 
REST style of communication. A comprehensive example demonstrating 
microservices architecture using Spring Boot, Spring Cloud, and 
Netflix OSS components. This project showcases service discovery, 
API gateway, and inter-service communication.

### 1. Eureka Server (Service Discovery)

- Port: 8761 
- Purpose: Service registry and discovery
- Dashboard: http://localhost:8761

### 2. API Gateway

- Port: 8080
- Purpose: Single entry point, request routing, load balancing
- Routes: 
  1. /users/** → User Service
  2. /orders/** → Order Service
  
### 3. User Service

- Port: 8081
- Purpose: Manages user data
- Database: H2 in-memory
- Endpoints: 
  1. GET /users - Get all users
  2. GET /users/{id} - Get user by ID [**would be called in Order Service via OpenFeign**]
  3. GET /users/name/{name} - Get user by Name [**would be called in Order Service via WebClient**]
  
### 4. Order Service

- Port: 8082
- Purpose: Manages orders, demonstrates service-to-service communication
- Database: H2 in-memory
- Endpoints: 
  1. GET /orders - Get all orders
  2. GET /orders/{id}/with-user - Get order with user details (calls User Service via **OpenFeign**)
  3. GET /orders/{id}/{name}/with-user-name - Get order with user name details (calls User Service via **WebClient**)

## Getting Started

1. Clone the whole code base [Requires **Java 17**]
2. Build the whole parent structure using ```mvn clean install```
3. Start Services in order:
   1. Start **Eureka Server** - Wait for startup, then verify at http://localhost:8761
   2. Start **User Service** - Wait for startup, then verify at http://localhost:8081/users
   3. Start **Order Service** - Wait for startup, then verify at http://localhost:8082/orders
   4. Start **API Gateway** 

## Verify Endpoints

### Direct Service Calls
### User Service:
#### 1. Get all users
```
curl http://localhost:8081/users
```
#### 2. Get specific user
```
curl http://localhost:8081/users/1
```
#### 3. Get specific user by name
```
curl http://localhost:8081/users/name/JohnDoe
```
### Order Service:
#### 1. Get all orders
```
curl http://localhost:8082/orders
```
#### 2. Get order with user details (demonstrates service communication)
```
curl http://localhost:8082/orders/1/with-user
curl http://localhost:8082/orders/1/JohnDoe/with-user-name
```

### Via API Gateway:
#### 1. Get users via gateway
```
curl http://localhost:8080/users
```
#### 2. Get order with user details via gateway
```
curl http://localhost:8080/orders/1/with-user
curl http://localhost:8080/orders/1/JohnDoe/with-user-name
```
Sample Response - Order with User:
```json
{
    "order": {
    "id": 1,
    "userId": 1,
    "product": "Laptop",
    "amount": 1200.0
},
    "user": {
    "id": 1,
    "name": "John Doe",
    "email": "john@example.com"
    }
}
```
## Key Learning Concepts

### Service Discovery
- Services automatically register with **Eureka**.
- Eliminates the need for hardcoded service URLs.
- Enables automatic **load balancing** across multiple instances of a service.

### Inter-Service Communication
- **Order Service** calls **User Service** using **Feign Client**, demonstrating seamless microservices collaboration.

### API Gateway Benefits
- Provides a **single entry point** for all client requests.

### Health Monitoring
- **Spring Boot Actuator** endpoints provide insights into service health.
- The **Eureka dashboard** offers a centralized view of registered service statuses.

## Monitoring & Management

### Health Endpoints
- **Eureka Server**: [http://localhost:8761](http://localhost:8761)
- **User Service**: [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)
- **Order Service**: [http://localhost:8082/actuator/health](http://localhost:8082/actuator/health)
- **Gateway**: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

### 🗃Database Consoles (H2)
- **User Service**: [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
- **Order Service**: [http://localhost:8082/h2-console](http://localhost:8082/h2-console)

**Connection Details:**
- JDBC URL:
    - `jdbc:h2:mem:userdb` (User Service)
    - `jdbc:h2:mem:orderdb` (Order Service)
- Username: `sa`
- Password: `password`

**Note**: I have taken the latest versions of Spring artifacts that deemed fit to me at the date when I am checking in.
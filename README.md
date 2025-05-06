# Spring Cloud Microservices Project

A robust microservices architecture built with Spring Boot and Spring Cloud, demonstrating modern cloud-native application development practices.

## Services Overview

### Service Registry (Eureka Server)

- Port: 8761
- Function: Service discovery and registration
- All microservices register themselves with this Eureka server
- Dashboard available at: http://localhost:8761

### Config Server

- Port: 9296
- Function: Centralized configuration management
- Sources configurations from Git repository: https://github.com/peguin818/spring-micro-config
- Enables dynamic configuration updates across services

### Product Service

- Port: 8080
- Core Features:
  - Product management (CRUD operations)
  - Inventory tracking
  - Real-time stock updates
- Endpoints:
  - POST `/api/v1/product`: Create new product
  - GET `/api/v1/product/{id}`: Get product by ID
  - PUT `/api/v1/product/reduceQuantity/{id}`: Reduce product quantity

### Order Service

- Port: 8082
- Core Features:
  - Order processing
  - Integration with Product Service via Feign Client
  - Multiple payment mode support (COD, MOMO, VNPAY, APPLE_PAY, GOOGLE_PAY)
- Endpoints:
  - POST `/api/v1/order/placeOrder`: Create new order

## Technical Stack

- Java 17
- Spring Boot 3.4.5
- Spring Cloud 2024.0.1
- MySQL Database
- Maven for dependency management
- Lombok for boilerplate reduction
- Spring Cloud OpenFeign for service communication
- Micrometer and Zipkin for distributed tracing

## Dependencies

### Common Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Cloud Netflix Eureka Client
- Spring Cloud Config Client
- MySQL Connector
- Lombok
- Spring Boot Actuator
- Micrometer Tracing with Brave
- Zipkin Reporter

### Additional Service-Specific Dependencies

- Config Server: Spring Cloud Config Server
- Order Service: Spring Cloud OpenFeign

## Setup & Installation

1. **Prerequisites**

   - JDK 17
   - MySQL 8.0+
   - Maven 3.9.9+

2. **Database Setup**

   ```sql
   CREATE DATABASE productdb;
   CREATE DATABASE orderdb;
   ```

3. **Service Startup Order**

   1. Start Service Registry (Eureka Server)
   2. Start Config Server
   3. Start Product Service
   4. Start Order Service

4. **Environment Variables**
   - `DB_HOST`: MySQL host (default: localhost)
   - `EUREKA_SERVER_ADDRESS`: Eureka server URL (default: http://localhost:8761/eureka)

## Project Structure

```
Project/
├── serviceregistry/    # Eureka Server
├── configserver/       # Centralized Configuration
├── productservice/     # Product Management
└── orderservice/      # Order Processing
```

## Features

- **Service Discovery**: Automatic service registration and discovery
- **Centralized Configuration**: Git-based configuration management
- **Distributed Tracing**: Request tracking across services
- **Error Handling**: Global exception handling with custom error responses
- **Database Integration**: JPA-based data persistence
- **Inter-Service Communication**: Feign Client implementation
- **API Versioning**: Versioned API endpoints (/api/v1/\*)

## Monitoring & Observability

- Actuator endpoints enabled for health monitoring
- Distributed tracing with Micrometer and Zipkin
- Sampling rate configured at 0.1 (10% of requests)

## Security Considerations

- Basic security configurations provided
- Database credentials should be externalized in production
- API endpoints secured with appropriate authentication (TODO)

## Future Enhancements

- [ ] Add API Gateway
- [ ] Implement Circuit Breaker
- [ ] Add Authentication & Authorization
- [ ] Implement Caching
- [ ] Add Docker Containerization
- [ ] Setup Kubernetes Deployment
- [ ] Implement Rate Limiting

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the Apache License 2.0

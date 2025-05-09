# Ticketing Gateway System

## 📦 Project Overview
The Ticketing Gateway System is a Spring Boot microservices application for managing IT support tickets. It includes modules for ticket creation, approval, assignment, resolution, and notification via email and ActiveMQ messaging.

### ✅ Features:
- Role-based login (USER, MANAGER, ADMIN)
- Ticket lifecycle management (Create, Approve, Resolve, Reopen, Close)
- Email notifications at key stages
- Dynamic PDF generation for ticket resolutions
- Scheduled tasks for auto-close of unresolved tickets
- Inter-microservice communication using ActiveMQ

---

## 🛠️ Architecture and Microservices
- **Gateway Service:** Handles user authentication and UI interactions.
- **Ticket Microservice:** Manages ticket creation, updates, and history tracking.
- **Notification Microservice:** Sends emails and manages messaging via ActiveMQ.

```
+-------------------+        +--------------------+        +--------------------+
| Gateway Service   | --->  | Ticket Microservice| --->  | Notification Service|
+-------------------+        +--------------------+        +--------------------+
```

---

## 🚀 Setup and Installation

### Prerequisites:
- Java 17 or higher
- Apache ActiveMQ
- MySQL Database
- Maven
- IDE (e.g., IntelliJ, Eclipse)

### Installation Steps:
1. Clone the repository:  
   ```bash
   git clone https://github.com/your-repo/TicketingGatewaySystem.git
   cd TicketingGatewaySystem
   ```

2. Configure the database in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ticket_system
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

3. Build the project:  
   ```bash
   mvn clean install
   ```

4. Run each service:  
   - **Gateway Service:**  
     ```bash
     cd TicketingGatewaySystem/GatewayService
     mvn spring-boot:run
     ```
   - **Ticket Microservice:**  
     ```bash
     cd TicketingGatewaySystem/TicketService
     mvn spring-boot:run
     ```
   - **Notification Service:**  
     ```bash
     cd TicketingGatewaySystem/NotificationService
     mvn spring-boot:run
     ```

---

## 📅 Database Configuration
- Import the `ticket_system.sql` file to initialize the database.
- Ensure that ActiveMQ is running at `localhost:61616`.

---

## 📍 Endpoints and Features
- **Login:** `/login`
- **Create Ticket:** `/tickets/create`
- **Approve Ticket:** `/tickets/approve/{id}`
- **Resolve Ticket:** `/tickets/resolve/{id}`
- **Reopen Ticket:** `/tickets/reopen/{id}`

---

## 📷 Screenshots
- Login Page (placeholder)
- Dashboard (placeholder)
- Ticket Creation Form (placeholder)

---

## 🛠️ Future Extensions
- Real-time WebSocket notifications
- Docker containerization
- Enhanced reporting and analytics

---

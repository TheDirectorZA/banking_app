# Catalyst Core API

Catalyst Core API is a backend system built with Spring Boot for managing core financial operations such as accounts, transactions, and users. It is designed as a learning and portfolio project to demonstrate backend engineering, system design, and API development skills.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- Spring Security (configured, JWT auth upcoming)
- PostgreSQL
- Maven
- Flyway
- Docker (planned)

---

## 📌 Features

### Core Features
- ✅ User management (create, read, update, delete)
- ✅ Account creation and management
- ✅ Deposit and withdrawal operations
- ✅ Transfers between accounts
- ✅ Transaction history per account

### Engineering
- ✅ Input validation (`@Valid` on all request bodies)
- ✅ Centralized exception handling (`GlobalExceptionHandler`)
- ✅ Standardized error responses (status, timestamp, message)
- ✅ Flyway database migrations
- ⏳ Logging and monitoring
- ⏳ JWT-based authentication
- ⏳ Role-based access (Admin / User)

---

## 🏗️ Project Structure

```
src/main/java/za/thedirectorza/banking_api
│
├── controller        # REST controllers (UserController, AccountController, TransactionController)
├── service           # Business logic
├── repository        # Spring Data JPA interfaces
├── model             # JPA entities (User, Account, Transaction)
├── dto               # Request/Response records
├── config            # SecurityConfig
└── exception         # Custom exceptions + GlobalExceptionHandler

src/main/resources
├── application.yaml
└── db/migration
    └── V1__init_schema.sql
```

---

## ⚙️ Getting Started

### Prerequisites
- Java 21
- Maven
- PostgreSQL

### Setup

1. Clone the repository
```bash
git clone https://github.com/your-username/catalyst-core-api.git
```

2. Create the database
```sql
CREATE DATABASE catalyst;
```

3. Configure database credentials in `src/main/resources/application.yaml`
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/catalyst
    username: postgres
    password: yourpassword
```

4. Run the application (Flyway will apply migrations automatically)
```bash
./mvnw spring-boot:run
```

---

## 📡 API Reference

### Users `/users`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/users` | Create a user |
| GET | `/users` | List all users |
| GET | `/users/{id}` | Get user by ID |
| PUT | `/users/{id}` | Update user |
| DELETE | `/users/{id}` | Delete user |

### Accounts `/accounts`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/accounts` | Create an account |
| GET | `/accounts` | List all accounts |
| GET | `/accounts/{id}` | Get account by ID |
| GET | `/accounts/user/{userId}` | Get accounts by user |

### Transactions `/transactions`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/transactions/accounts/{id}/deposit` | Deposit funds |
| POST | `/transactions/accounts/{id}/withdraw` | Withdraw funds |
| POST | `/transactions/accounts/{id}/transfer` | Transfer to another account |
| GET | `/transactions/accounts/{id}` | Get transaction history |

### Example Requests

**Create a user**
```json
POST /users
{
  "name": "Jane Doe",
  "email": "jane@example.com"
}
```

**Create an account**
```json
POST /accounts
{
  "userId": 1,
  "initialBalance": 1000.00
}
```

**Deposit**
```json
POST /transactions/accounts/1/deposit
{
  "amount": 500.00
}
```

**Transfer**
```json
POST /transactions/accounts/1/transfer
{
  "toAccountId": 2,
  "amount": 200.00
}
```

---

## 🧠 Learning Goals

This project is built to improve understanding of:

* Backend architecture design
* REST API development
* Database relationships and migrations
* Security in web applications
* Scalable system design

---

## 📈 Future Enhancements

* JWT-based authentication and role-based access control
* Microservices decomposition
* Docker containerization
* CI/CD pipeline with GitHub Actions
* Cloud deployment (AWS/Azure)
* Observability (logging, metrics, tracing)

---

## 👤 Author

Built by Butinyana David Motsoeneng

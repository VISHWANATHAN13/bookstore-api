# 📚 Bookstore API

A **Spring Boot** REST API for managing books with secure **JWT authentication**, validation, custom error handling, and clean response structures.

---

## 🚀 Features

✔ User login with JWT token generation
✔ Protected routes with JWT validation
✔ CRUD operations for books
✔ Request validation with custom errors
✔ Global exception handling with meaningful errors
✔ Uses a standard API response format

---

## 🧠 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Security**
* **JWT (JSON Web Tokens)**
* **Spring Data JPA**
* **PostgreSQL / MySQL (configurable)**
* **Maven**

---

## 📁 Project Structure

```
src/main/java
├── controller        # REST endpoints
├── service           # Business logic
├── repository        # Database access
├── entity            # JPA entities
├── dto               # Request/Response DTOs
├── exception         # Custom exceptions & handlers
├── security          # JWT & Security filters/config
├── common            # ApiResponse & APIError types
```

---

## 🛠 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/VISHWANATHAN13/bookstore-api.git
cd bookstore-api
```

### 2. Configure the database

Update your `application.properties` like:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your-secret-key
```

You can use **PostgreSQL** or **MySQL**, no code change required.

---

### 3. Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

API will start on `http://localhost:8080`

---

## 🔑 Authentication Flow

1. **POST** `/login` with email & password
2. Receive JWT token if credentials are valid
3. Use token in `Authorization: Bearer <token>` header for protected APIs

---

## 📌 Example Endpoints

| Method | Endpoint      | Description               |
| ------ | ------------- | ------------------------- |
| POST   | `/login`      | Login & get JWT           |
| POST   | `/books`      | Create a book (protected) |
| GET    | `/books`      | List all books            |
| GET    | `/books/{id}` | Get a book by ID          |
| PUT    | `/books/{id}` | Update a book             |
| DELETE | `/books/{id}` | Delete a book             |

---

## 🧾 Validation & Error Handling

Request validation is done using custom validators.
When validation fails, the API returns:

```json
{
  "status": 400,
  "message": "bad request",
  "data": [
    {
      "target": "name",
      "message": "must not be empty"
    }
  ]
}
```

This uses a custom `BadRequestException` class and global exception handler.

---

## 📦 API Response Format

All API endpoints use a unified response format:

```json
{
  "status": <http_status>,
  "message": "<message text>",
  "data": <actual data or errors>
}
```

This makes responses predictable for clients.

---

## 🧪 Testing (optional)

Add unit and integration tests using JUnit and Mockito for services and controllers.

---

## 📈 Next Steps

* Add **Swagger/OpenAPI docs**
* Add **role-based access (admin vs user)**
* Add **automated tests**
* Deploy with **Docker / CI pipeline**

---

## ⭐ Contributing

Contributions are welcome!
Create an issue or PR if you want to help improve the project.

---

## 📜 License

This repository is **open source** and free to use.

---


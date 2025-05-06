# rootcode_assignment
This is spring boot project for rootcode interview

# 📚 Online Library System

This is a Spring Boot-based Online Library Management System that allows users to:
- Register and authenticate
- Search for books
- Borrow and return books
- View borrowing history

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Security with JWT
- JPA (Hibernate)
- H2 / MySQL (in-memory or persistent DB)
- Lombok
- Maven
- Swagger for API documentation

---

## 📂 Project Structure

src/
├── main/
│ ├── java/com/assignment/assignment/
│ │ ├── controller
│ │ ├── entity
│ │ ├── repository
│ │ ├── service
│ │ ├── DTO
│ │ ├── mapper
│ │ └── config
│ └── resources/
│ └── application.properties
└── test/
└── java/


---

##  Entity Relationship Diagram (ERD)

![Entity Relationship Diagram]("/ER_Diagram.jpg")

> This diagram shows the relationships between `User`, `Book`, and `BorrowRecord` entities.

- **User** ↔ **BorrowRecord**: One-to-Many
- **Book** ↔ **BorrowRecord**: One-to-Many

---

##  System Architecture Diagram

![System Architecture Diagram]("/Architecture_Diagram.jpg")

> This system follows a layered architecture:
- Controller Layer
- Service Layer
- Repository Layer
- Security Layer (JWT Auth)
- Persistence Layer (JPA + DB)

---

## 🛠️ How to Run

1. **Clone the repository**
   ```
   https://github.com/it21302862/rootcode_assignment


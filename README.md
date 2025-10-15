# LibroNova

## Overview
LibroNova is a desktop library management system developed in **Java SE 17** with a simple GUI using **JOptionPane**. It centralizes management of books, members, users, and loans while ensuring data integrity and business rule validations. The system follows a **layered architecture** and uses **JDBC with MySQL** for persistence.

---

## Features Implemented

- **Book Management**
  - Create, update, delete books.
  - ISBN uniqueness validation.
  - Filter books by author or category.
  - Track total and available copies.
- **Member Management**
  - Create, update, delete members.
  - Unique document ID validation.
  - Track member status (ACTIVE/INACTIVE).
- **User Management & Authentication**
  - Login with roles (`ADMIN`, `ASISTENTE`).
  - CRUD operations on users.
  - Role and state defaults.
- **Loan Management**
  - Create and return loans.
  - Automatic penalty calculation for late returns.
  - Stock updates on loan and return with **JDBC transactions**.
- **Data Persistence**
  - MySQL database.
  - JDBC DAO implementations.
- **Configuration**
  - `config.properties` for database connection and loan settings.
- **Logging**
  - Java `java.util.logging` for activities and errors.
- **Validation & Exceptions**
  - ISBN, stock, document, state, and return validations.
- **Unit Testing**
  - JUnit 5 for core services (implemented).

---

## Project Structure

com.libronova

├── config

│ └── ConfigUtil.java

├── dao

│ ├── BookDAO.java

│ ├── MemberDAO.java

│ ├── UserDAO.java

│ └── LendingDAO.java

├── dao/imp

│ ├── BookDAOImp.java

│ ├── MemberDAOImp.java

│ ├── UserDAOImp.java

│ └── LendingDAOImp.java

├── model

│ ├── Book.java

│ ├── Member.java

│ ├── User.java

│ └── Lending.java

├── service

│ ├── BookService.java

│ ├── MemberService.java

│ ├── UserService.java

│ └── LendingService.java

├── service/imp

│ ├── BookServiceImp.java

│ ├── MemberServiceImp.java

│ ├── UserServiceImp.java

│ └── LendingServiceImp.java

└── LibroNova.java (main application)


---

## Database Schema

**Tables:**
- `roles` (ADMIN, ASISTENTE)
- `usuarios` (users)
- `socios` (members)
- `libros` (books)
- `prestamos` (loans)

**Initial Data:**
- Admin and assistant users.
- Sample books and members.

---
## How to Run

1. Install **Java 17**, **Maven**, and **MySQL**.
2. Create database using `libronova.sql`.
3. Update `config.properties` with your MySQL credentials.
4. Open the project in **NetBeans**.
5. Build with Maven:  
```bash
   mvn clean install
```
6. Run the main class:
```bash
  com.libronova.LibroNova
```

## 📝 Author

**Luis Horacio Romero Corona, Clan Cienaga, darkusxdtk@gmail.com, 1002162428**

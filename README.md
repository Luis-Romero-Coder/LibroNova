# LibroNova

**LibroNova** is a desktop library management system built in **Java SE 17** with a simple graphical interface using **JOptionPane**. It manages books, members, users, and loans, applying a layered architecture (Controller → Service → DAO → Model) with JDBC for persistence.

---

## 🏗 Project Structure

com.libronova
│
├── controller // Controllers handling user interactions via JOptionPane
├── dao // DAO interfaces for database operations
├── dao.imp // DAO implementations using JDBC
├── model // Data models: Book, Member, User, Role, Lending
├── service // Service interfaces for business logic
├── service.imp // Service implementations (in progress)
└── util // Utilities: DBConnection, ConfigUtil, Logging
resources
└── config.properties // Database and system configuration


---

## ✅ Completed Modules

### 1. Models
- **Book**: Book details, stock management, price, state  
- **Member**: Library members with document, name, phone, address, state  
- **User**: Application users with role, state, and authentication  
- **Role**: User roles (ADMIN, ASSISTANT)  
- **Lending**: Loans with loan date, return date, expiration, penalties, and state  

### 2. DAO / DAO Implementations
- `BookDAO` / `BookDAOImp`  
- `UserDAO` / `UserDAOImp`  
- `MemberDAO` / `MemberDAOImp`  
- `LendingDAO` / `LendingDAOImp` ✅ (completed and handling LocalDate → java.sql.Date conversion)  

### 3. Controllers (Partial)
- `BookController` ✅  
- `UserController` ✅  
- `MemberController` ✅ (renamed to match new model)  
- `LendingController` ✅  

### 4. Utilities
- `DBConnection` (JDBC) ✅  
- `ConfigUtil` (`config.properties`) ✅  
- Logging with `java.util.logging` ✅  

---

## ⚠️ Pending Modules / Features

- **Services (`ServiceImp`)**:
  - `BookServiceImp`: validation of ISBN uniqueness, stock management  
  - `UserServiceImp`: authentication, default roles, uniqueness checks  
  - `MemberServiceImp`: state validation, unique document  
  - `LendingServiceImp`: transactional loan and return operations, penalty calculation  

- **Full GUI / Menu**:  
  - Main menu for Catalog, Members, Users, Loans, Export  
  - Table-like listings in JOptionPane  
  - Success / error confirmations  

- **Export / Logging**:  
  - Export books and overdue loans to CSV  
  - Use configuration from `config.properties` for loan days and penalties  
  - Log all CRUD operations  

- **Custom Exceptions & Validations**:  
  - Business exceptions for duplicate ISBN, inactive members, insufficient stock, etc.  

- **JUnit5 Tests**:  
  - Validate penalties, stock, and unique ISBN  
  - Ensure service layer correctness  

---

## 💾 Database

- **Database Name**: `libronova`  
- **Tables**: `roles`, `usuarios`, `socios`, `libros`, `prestamos`  
- **Initial Data**:  
  - Roles: ADMIN, ASSISTANT  
  - Users: admin, asistente1  
  - Members: Carlos Ruiz, María López  
  - Books: *El Principito*, *Cien Años de Soledad*  

---

## ⚙️ Requirements

- **Java SE 17**  
- **Maven** for build and dependency management  
- **MySQL 8+** (or compatible)  
- Optional: IDE like **NetBeans**  

---

## 📌 Notes

This project is a work in progress. Core models, DAOs, and controllers are functional, but **service layer, full GUI, export features, and tests** are still pending.  

The system is designed for modularity, separation of layers, and future extension with full CRUD operations and business logic enforcement.

---

## 📝 Author

**Luis Horacio Romero Corona, Clan Cienaga, darkusxdtk@gmail.com, 1002162428**

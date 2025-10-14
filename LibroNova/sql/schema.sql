/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Coder
 * Created: 14/10/2025
 */


-- ===============================
-- 1️Crear la base de datos
-- ===============================
DROP DATABASE IF EXISTS libronova;
CREATE DATABASE libronova CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE libronova;

-- ===============================
-- 2️Tabla: roles
-- ===============================
CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO roles (nombre) VALUES ('ADMIN'), ('ASISTENTE');

-- ===============================
-- 3️Tabla: usuarios
-- ===============================
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    estado ENUM('ACTIVO','INACTIVO') DEFAULT 'ACTIVO',
    role_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- ===============================
-- 4️Tabla: socios
-- ===============================
CREATE TABLE socios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(150),
    estado ENUM('ACTIVO','INACTIVO') DEFAULT 'ACTIVO',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- 5️Tabla: libros
-- ===============================
CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    ejemplares_totales INT NOT NULL,
    ejemplares_disponibles INT NOT NULL,
    precio_referencia DECIMAL(10,2),
    is_activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ===============================
-- 6️Tabla: prestamos
-- ===============================
CREATE TABLE prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    socio_id INT NOT NULL,
    libro_id INT NOT NULL,
    usuario_id INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    fecha_limite DATE NOT NULL,
    multa DECIMAL(10,2) DEFAULT 0,
    estado ENUM('PRESTADO', 'DEVUELTO', 'VENCIDO') DEFAULT 'PRESTADO',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (socio_id) REFERENCES socios(id),
    FOREIGN KEY (libro_id) REFERENCES libros(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- ===============================
-- 7️Datos iniciales
-- ===============================

-- Roles
INSERT INTO roles (nombre) VALUES ('ADMIN'), ('ASISTENTE');

-- Usuarios
INSERT INTO usuarios (username, password, nombre, email, role_id)
VALUES 
('admin', 'admin123', 'Administrador', 'admin@libronova.com', 1),
('asistente1', 'asis123', 'Asistente Principal', 'asistente@libronova.com', 2);

-- Socios
INSERT INTO socios (cedula, nombre, telefono, direccion)
VALUES 
('1001234567', 'Carlos Ruiz', '3124567890', 'Calle 10 #5-20'),
('1009876543', 'María López', '3119876543', 'Carrera 7 #12-45');

-- Libros
INSERT INTO libros (isbn, titulo, autor, categoria, ejemplares_totales, ejemplares_disponibles, precio_referencia)
VALUES
('978-9581234567', 'El Principito', 'Antoine de Saint-Exupéry', 'Infantil', 5, 5, 35000),
('978-9587654321', 'Cien Años de Soledad', 'Gabriel García Márquez', 'Realismo Mágico', 3, 3, 55000);

-- ===============================
-- 8️Verificación rápida
-- ===============================
SELECT 'Base de datos LibroNova creada correctamente' AS mensaje;

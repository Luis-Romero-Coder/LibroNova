/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.model;

import java.time.LocalDateTime;

/**
 *
 * @author Coder
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String state; 
    private Role role;
    private LocalDateTime createdAt;
    

    public User() {
        this.state = "ACTIVO";
        this.createdAt = LocalDateTime.now();
    }

    public User(String username, String password, String name, String email, Role role) {
        this();
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }


    // Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 4 caracteres.");
        }
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.name = name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
        this.email = email.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (!state.equalsIgnoreCase("ACTIVO") && !state.equalsIgnoreCase("INACTIVO")) {
            throw new IllegalArgumentException("Estado inválido. Debe ser ACTIVO o INACTIVO.");
        }
        this.state = state.toUpperCase();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo.");
        }
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %s",
                state, name, username, role != null ? role.getName() : "SIN ROL");
    }
}

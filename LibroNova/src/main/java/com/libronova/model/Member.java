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
public class Member {
    private int id;
    private String document;
    private String name;
    private String phone;
    private String address;
    private String state;
    private LocalDateTime createdAt;



    public Member() {
        this.state = "ACTIVO";
        this.createdAt = LocalDateTime.now();
    }

    public Member(String document, String name, String phone, String address) {
        this();
        this.document = document;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        if (document == null || document.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía.");
        }
        this.document = document.trim();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && phone.length() < 7) {
            throw new IllegalArgumentException("El teléfono no es válido.");
        }
        this.phone = phone != null ? phone.trim() : null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address != null ? address.trim() : null;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public boolean state() {
        return "ACTIVO".equalsIgnoreCase(state);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)", 
                state, name, document, phone != null ? phone : "Sin teléfono");
    }
}

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
public class Book {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private int totalCopies;
    private int copiesAviable;
    private double referencePrice;
    private boolean state;
    private LocalDateTime createdAt;
    
    public Book() {
        this.state = true;
        this.createdAt = LocalDateTime.now();
    }

    public Book(int id, String isbn, String title, String author, String category, int totalCopies, int copiesAviable, double referencePrice, boolean state, LocalDateTime createdAt) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.copiesAviable = copiesAviable;
        this.referencePrice = referencePrice;
        this.state = state;
        this.createdAt = createdAt;
    }

    
    
    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(isbn == null || isbn.trim().isEmpty()){
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        }
        this.isbn = isbn.trim();
        
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        if(title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("El Titulo no puede estar vacío.");
        }
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
        this.author = author.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category != null ? category.trim():null;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        if (totalCopies < 0) {
            throw new IllegalArgumentException("El número de ejemplares totales no puede ser negativo.");
        }
        this.totalCopies = totalCopies;
    }

    public int getCopiesAviable() {
        return copiesAviable;
    }

    public void setCopiesAviable(int copiesAviable) {
        if (copiesAviable < 0) {
            throw new IllegalArgumentException("El número de ejemplares disponibles no puede ser negativo.");
        }
        this.copiesAviable = copiesAviable;
    }

    public double getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(double referencePrice) {
        if (referencePrice < 0) {
            throw new IllegalArgumentException("El precio de referencia no puede ser negativo.");
        }
        this.referencePrice = referencePrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public boolean state(){
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format(
            "[%s] %s - %s (%s) | Stock: %d/%d | $%.2f",
            state ? "ACTIVO" : "INACTIVO",
            title, author, category,
            copiesAviable, totalCopies,
            referencePrice
        );
    }
    
    
    
}

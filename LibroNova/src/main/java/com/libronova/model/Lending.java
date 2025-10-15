/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Coder
 */
public class Lending {
    private int id;
    private int bookId;
    private int memberId;
    private int userId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate expirationDate;
    private double penalty;
    private String state; 



    public Lending() {
        this.state = "ACTIVO";
        this.loanDate = LocalDate.now();
    }

    public Lending(int bookId, int memberId, int userId, LocalDate expirationDate) {
        this();
        this.bookId = bookId;
        this.memberId = memberId;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }


    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int libroId) {
        this.bookId = libroId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (!state.equalsIgnoreCase("ACTIVO") &&
            !state.equalsIgnoreCase("DEVUELTO") &&
            !state.equalsIgnoreCase("VENCIDO")) {
            throw new IllegalArgumentException("Estado de préstamo inválido.");
        }
        this.state = state.toUpperCase();
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate) && !"DEVUELTO".equalsIgnoreCase(state);
    }

    public long calculateDaysDelay() {
        if (returnDate == null || returnDate.isBefore(expirationDate)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(expirationDate, returnDate);
    }

    public double calcularMulta(double penaltyPerDay) {
        long days = calculateDaysDelay();
        this.penalty = days * penaltyPerDay;
        return penalty;
    }

    @Override
    public String toString() {
        return String.format("[%s] Libro ID: %d | Socio ID: %d | Vence: %s | Devuelto: %s | Multa: $%.2f",
                state, bookId, memberId,
                expirationDate, 
                returnDate != null ? returnDate : "Pendiente",
                penalty);
    }
}

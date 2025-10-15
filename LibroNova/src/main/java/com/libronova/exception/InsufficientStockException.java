/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.exception;

/**
 *
 * @author Coder
 */
public class InsufficientStockException extends AppException {
    public InsufficientStockException(String title) {
        super("No hay ejemplares disponibles para el libro: " + title);
    }
}

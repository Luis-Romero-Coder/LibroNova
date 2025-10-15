/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.exception;

/**
 *
 * @author Coder
 */
public class ISBNExistingException extends AppException {
    public ISBNExistingException(String isbn) {
        super("El ISBN '" + isbn + "' ya está registrado en el sistema.");
    }
}

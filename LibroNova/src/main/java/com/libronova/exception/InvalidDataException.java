/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.exception;

/**
 *
 * @author Coder
 */
public class InvalidDataException extends AppException {
    public InvalidDataException(String detail) {
        super("Datos inválidos: " + detail);
    }
}

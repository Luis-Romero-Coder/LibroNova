/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.exception;

/**
 *
 * @author Coder
 */
public class InactiveMemberException extends AppException {
    public InactiveMemberException(String name) {
        super("El socio '" + name + "' está inactivo y no puede realizar préstamos.");
    }
}

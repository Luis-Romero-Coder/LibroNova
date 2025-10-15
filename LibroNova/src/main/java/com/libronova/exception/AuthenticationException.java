/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.exception;

/**
 *
 * @author Coder
 */
public class AuthenticationException extends AppException {
    public AuthenticationException() {
        super("Credenciales inválidas. Verifique su usuario o contraseña.");
    }
}
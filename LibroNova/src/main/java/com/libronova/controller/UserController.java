/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.controller;

import com.libronova.model.Role;
import com.libronova.model.User;
import com.libronova.service.UserService;
import com.libronova.service.imp.UserServiceImp;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author Coder
 */
public class UserController  {

    private final UserService userService;

    public UserController() {
        this.userService = new UserServiceImp();
    }

    public void menuUsers() {
        String[] options = {"Iniciar sesión", "Registrar usuario", "Listar usuarios", "Salir"};
        int option;

        do {
            option = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione una opción",
                    "Gestión de Usuarios - LibroNova",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (option) {
                case 0 -> login();
                case 1 -> registerUser();
                case 2 -> listUsers();
                case 3 -> JOptionPane.showMessageDialog(null, "Saliendo del módulo de usuarios...");
                default -> {
                }
            }
        } while (option != 3 && option != JOptionPane.CLOSED_OPTION);
    }

    private void login() {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
        if (username == null) return;

        String password = JOptionPane.showInputDialog("Ingrese la contraseña:");
        if (password == null) return;

        try {
            Optional<User> userOpt = userService.login(username, password);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                JOptionPane.showMessageDialog(null,
                        "Bienvenido, " + user.getName() + "\nRol: " + user.getRole().getName(),
                        "Inicio de sesión exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Credenciales incorrectas o usuario inactivo.",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar sesión: " + e.getMessage());
        }
    }

    private void registerUser() {
        try {
            String username = JOptionPane.showInputDialog("Nombre de usuario:");
            if (username == null || username.isBlank()) return;

            String password = JOptionPane.showInputDialog("Contraseña:");
            if (password == null || password.isBlank()) return;

            String name = JOptionPane.showInputDialog("Nombre completo:");
            if (name == null || name.isBlank()) return;

            String email = JOptionPane.showInputDialog("Correo electrónico:");
            if (email == null) email = "";

            // El decorador en UserServiceImp asigna role=ASISTENTE, estado=ACTIVO
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);

            boolean created = userService.create(user);
            if (created) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void listUsers() {
        try {
            List<User> users = userService.listAll();
            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay usuarios registrados.");
                return;
            }

            StringBuilder sb = new StringBuilder("📋 LISTADO DE USUARIOS\n\n");
            sb.append(String.format("%-5s %-15s %-10s %-10s %-20s\n", "ID", "Usuario", "Rol", "Estado", "Fecha creación"));
            sb.append("-------------------------------------------------------------\n");

            for (User u : users) {
                sb.append(String.format("%-5d %-15s %-10s %-10s %-20s\n",
                        u.getId(),
                        u.getUsername(),
                        u.getRole().getName(),
                        u.getState(),
                        u.getCreatedAt()));
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios: " + e.getMessage());
        }
    }
}

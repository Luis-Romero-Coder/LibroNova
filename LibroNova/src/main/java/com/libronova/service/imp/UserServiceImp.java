/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.service.imp;

import com.libronova.dao.UserDAO;
import com.libronova.dao.imp.UserDAOImp;
import com.libronova.model.Role;
import com.libronova.model.User;
import com.libronova.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 *
 * @author Coder
 */
public class UserServiceImp implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImp.class.getName());
    private final UserDAO userDAO;

    public UserServiceImp() {
        this.userDAO = new UserDAOImp();
    }

    @Override
    public boolean create(User user) throws Exception {
        
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

    
        if (user.getRole() == null) {
            user.setRole(new Role(0, "ASISTENTE"));
        }
        if (user.getState() == null) {
            user.setState("ACTIVO");
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(LocalDateTime.now());
        }

        // Validar duplicidad
        Optional<User> existing = userDAO.searchByUser(user.getUsername());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre de usuario.");
        }

        LOGGER.info(() -> "Decorador aplicado: role=ASISTENTE, estado=ACTIVO, createdAt=" + user.getCreatedAt());
        return userDAO.create(user);
    }

    @Override
    public boolean update(User user) throws Exception {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(int id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public Optional<User> login(String username, String password) throws Exception {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Credenciales inválidas.");
        }
        return userDAO.login(username, password);
    }

    @Override
    public Optional<User> findByUsername(String username) throws Exception {
        return userDAO.searchByUser(username);
    }

    @Override
    public List<User> listAll() throws Exception {
        return userDAO.listAll();
    }
}

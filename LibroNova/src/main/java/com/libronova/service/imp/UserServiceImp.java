/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.service.imp;

import com.libronova.dao.UserDAO;
import com.libronova.dao.imp.UserDAOImp;
import com.libronova.model.User;
import com.libronova.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Coder
 */
public class UserServiceImp implements UserService {

    private UserDAO userDAO;

    public UserServiceImp() {
        this.userDAO = new UserDAOImp();
    }

    @Override
    public boolean create(User user) throws Exception {
        if (userDAO.searchByUser(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists.");
        }
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
    public Optional<User> searchByUsername(String username) throws Exception {
        return userDAO.searchByUser(username);
    }

    @Override
    public Optional<User> login(String username, String password) throws Exception {
        return userDAO.login(username, password);
    }

    @Override
    public List<User> listAll() throws Exception {
        return userDAO.listAll();
    }
}
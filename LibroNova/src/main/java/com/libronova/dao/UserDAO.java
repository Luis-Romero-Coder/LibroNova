/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.libronova.dao;

import com.libronova.model.User;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Coder
 */
public interface UserDAO {
    boolean create(User user) throws Exception;

    boolean update(User user) throws Exception;

    boolean delete(int id) throws Exception;

    Optional<User> searchByUser(String username) throws Exception;

    Optional<User> login(String username, String password) throws Exception;

    List<User> listAll() throws Exception;
}

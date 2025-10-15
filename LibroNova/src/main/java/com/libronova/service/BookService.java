/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.libronova.service;

import com.libronova.model.Book;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Coder
 */
public interface BookService {

    boolean create(Book book) throws Exception;

    boolean update(Book book) throws Exception;

    boolean delete(int id) throws Exception;

    Optional<Book> searchByIsbn(String isbn) throws Exception;

    List<Book> listAll() throws Exception;
}

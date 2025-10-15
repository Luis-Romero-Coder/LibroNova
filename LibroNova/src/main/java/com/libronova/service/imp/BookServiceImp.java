/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.service.imp;

import com.libronova.dao.BookDAO;
import com.libronova.dao.imp.BookDAOImp;
import com.libronova.model.Book;
import com.libronova.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
/**
 *
 * @author Coder
 */
public class BookServiceImp implements BookService {

    private static final Logger LOGGER = Logger.getLogger(BookServiceImp.class.getName());
    private final BookDAO bookDAO = new BookDAOImp();

    @Override
    public boolean create(Book book) throws Exception {
        if (bookDAO.searchByIsbn(book.getIsbn()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un libro con ese ISBN.");
        }
        return bookDAO.create(book);
    }

    @Override
    public boolean update(Book book) throws Exception {
        return bookDAO.update(book);
    }

    @Override
    public boolean delete(int id) throws Exception {
        return bookDAO.delete(id);
    }

    @Override
    public Optional<Book> searchByIsbn(String isbn) throws Exception {
        return bookDAO.searchByIsbn(isbn);
    }

    @Override
    public List<Book> listAll() throws Exception {
        return bookDAO.listAll();
    }
}

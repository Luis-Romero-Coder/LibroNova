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
/**
 *
 * @author Coder
 */
public class BookServiceImp implements BookService {

    private BookDAO bookDAO;

    public BookServiceImp() {
        this.bookDAO = new BookDAOImp();
    }

    @Override
    public boolean create(Book book) throws Exception {
        // Validar ISBN único
        if (bookDAO.searchByIsbn(book.getIsbn()).isPresent()) {
            throw new Exception("ISBN already exists.");
        }
        return bookDAO.create(book);
    }

    @Override
    public boolean update(Book book) throws Exception {
        Optional<Book> existing = bookDAO.searchByIsbn(book.getIsbn());
        if (existing.isPresent() && existing.get().getId() != book.getId()) {
            throw new Exception("ISBN already exists for another book.");
        }
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
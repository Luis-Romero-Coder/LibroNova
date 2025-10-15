/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.service.imp;

import com.libronova.dao.LendingDAO;
import com.libronova.dao.imp.LendingDAOImp;
import com.libronova.dao.BookDAO;
import com.libronova.dao.imp.BookDAOImp;
import com.libronova.model.Lending;
import com.libronova.model.Book;
import com.libronova.service.LendingService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.libronova.util.DBConnection;
/**
 *
 * @author Coder
 */
public class LendingServiceImp implements LendingService {

    private LendingDAO lendingDAO;
    private BookDAO bookDAO;

    public LendingServiceImp() {
        this.lendingDAO = new LendingDAOImp();
        this.bookDAO = new BookDAOImp();
    }

    @Override
    public boolean create(Lending lending) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            Book book = bookDAO.searchById(lending.getBookId())
                    .orElseThrow(() -> new Exception("Book not found"));

            if (!book.state() || book.getCopiesAviable() <= 0) {
                throw new Exception("Book not available");
            }

            // Insert lending
            boolean inserted = lendingDAO.create(conn, lending);

            // Update book stock
            book.setCopiesAviable(book.getCopiesAviable() - 1);
            bookDAO.update(conn, book);

            conn.commit();
            return inserted;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean returnBook(Lending lending) throws Exception {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            Lending existing = lendingDAO.searchById(conn, lending.getId())
                    .orElseThrow(() -> new Exception("Lending not found"));

            if ("DEVUELTO".equalsIgnoreCase(existing.getState())) {
                throw new Exception("Book already returned");
            }

            // Update return date and state
            existing.setReturnDate(lending.getReturnDate());
            existing.setState("DEVUELTO");

            // Calculate penalty
            double penalty = existing.calcularMulta(ConfigUtil.getPenaltyPerDay());
            existing.setPenalty(penalty);

            lendingDAO.update(conn, existing);

            // Restore book stock
            Book book = bookDAO.searchById(existing.getBookId())
                    .orElseThrow(() -> new Exception("Book not found"));
            book.setCopiesAviable(book.getCopiesAviable() + 1);
            bookDAO.update(conn, book);

            conn.commit();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        return lendingDAO.delete(id);
    }

    @Override
    public Optional<Lending> searchById(int id) throws Exception {
        return lendingDAO.searchById(id);
    }

    @Override
    public List<Lending> listAll() throws Exception {
        return lendingDAO.listAll();
    }
}
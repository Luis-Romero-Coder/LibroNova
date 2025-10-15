/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.dao.imp;

import com.libronova.dao.BookDAO;
import com.libronova.model.Book;
import com.libronova.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coder
 */
public class BookDAOImp implements BookDAO {

    private static final Logger LOGGER = Logger.getLogger(BookDAOImp.class.getName());

    @Override
    public boolean create(Book book) throws Exception {
        String sql = "INSERT INTO libros (isbn, titulo, autor, categoria, ejemplares_totales, ejemplares_disponibles, precio_referencia, is_activo, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getTotalCopies());
            ps.setInt(6, book.getCopiesAviable());
            ps.setDouble(7, book.getReferencePrice());
            ps.setBoolean(8, book.state());
            ps.setTimestamp(9, Timestamp.valueOf(book.getCreatedAt()));

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[POST] /books - Libro agregado: " + book.getTitle());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al crear libro", e);
            throw e;
        }
    }

    @Override
    public boolean update(Book book) throws Exception {
        String sql = "UPDATE libros SET titulo=?, autor=?, categoria=?, ejemplares_totales=?, ejemplares_disponibles=?, precio_referencia=?, is_activo=? WHERE isbn=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getTotalCopies());
            ps.setInt(5, book.getCopiesAviable());
            ps.setDouble(6, book.getReferencePrice());
            ps.setBoolean(7, book.state());
            ps.setString(8, book.getIsbn());

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[PATCH] /books/" + book.getIsbn());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar libro", e);
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM libros WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[DELETE] /books/" + id);
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar libro", e);
            throw e;
        }
    }

    @Override
    public Optional<Book> searchByIsbn(String isbn) throws Exception {
        String sql = "SELECT * FROM libros WHERE isbn=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar libro", e);
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public List<Book> listAll() throws Exception {
        String sql = "SELECT * FROM libros";
        List<Book> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
            LOGGER.info("[GET] /books");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar libros", e);
            throw e;
        }
        return list;
    }

    private Book mapRow(ResultSet rs) throws SQLException {
        Book b = new Book();
        b.setId(rs.getInt("id"));
        b.setIsbn(rs.getString("isbn"));
        b.setTitle(rs.getString("titulo"));
        b.setAuthor(rs.getString("autor"));
        b.setCategory(rs.getString("categoria"));
        b.setTotalCopies(rs.getInt("ejemplares_totales"));
        b.setCopiesAviable(rs.getInt("ejemplares_disponibles"));
        b.setReferencePrice(rs.getDouble("precio_referencia"));
        b.setState(rs.getBoolean("is_activo"));
        b.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return b;
    }
}

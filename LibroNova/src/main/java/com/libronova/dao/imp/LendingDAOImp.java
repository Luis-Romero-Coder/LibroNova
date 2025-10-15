/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.dao.imp;

import com.libronova.dao.LendingDAO;
import com.libronova.model.Lending;
import com.libronova.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Coder
 */
public class LendingDAOImp implements LendingDAO {

    private static final Logger LOGGER = Logger.getLogger(LendingDAOImp.class.getName());

    @Override
    public boolean create(Lending l) throws Exception {
        String sql = "INSERT INTO prestamos (socio_id, libro_id, usuario_id, fecha_prestamo, fecha_limite, estado, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, l.getMemberId());
            ps.setInt(2, l.getBookId());
            ps.setInt(3, l.getUserId());
            ps.setDate(4, java.sql.Date.valueOf(l.getLoanDate()));
            ps.setDate(5, java.sql.Date.valueOf(l.getExpirationDate()));
            ps.setString(6, l.getState());
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[POST] /prestamos - Préstamo registrado: LibroID=" + l.getBookId());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al crear préstamo", e);
            throw e;
        }
    }

    @Override
    public boolean update(Lending l) throws Exception {
        String sql = "UPDATE prestamos SET fecha_devolucion=?, estado=?, multa=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, l.getReturnDate() != null ? java.sql.Date.valueOf(l.getReturnDate()) : null);
            ps.setString(2, l.getState());
            ps.setDouble(3, l.getPenalty());
            ps.setInt(4, l.getId());

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[PATCH] /prestamos/" + l.getId());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar préstamo", e);
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM prestamos WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[DELETE] /prestamos/" + id);
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar préstamo", e);
            throw e;
        }
    }

    @Override
    public Optional<Lending> searchById(int id) throws Exception {
        String sql = "SELECT * FROM prestamos WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar préstamo", e);
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public List<Lending> listAll() throws Exception {
        String sql = "SELECT * FROM prestamos";
        List<Lending> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
            LOGGER.info("[GET] /prestamos");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar préstamos", e);
            throw e;
        }
        return list;
    }

    @Override
    public List<Lending> listOverdue() throws Exception {
        String sql = "SELECT * FROM prestamos WHERE estado='PRESTADO' AND fecha_limite < CURDATE()";
        List<Lending> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
            LOGGER.info("[GET] /prestamos/vencidos");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar préstamos vencidos", e);
            throw e;
        }
        return list;
    }

    private Lending mapRow(ResultSet rs) throws SQLException {
        Lending l = new Lending();
        l.setId(rs.getInt("id"));
        l.setMemberId(rs.getInt("socio_id"));
        l.setBookId(rs.getInt("libro_id"));
        l.setUserId(rs.getInt("usuario_id"));
        l.setLoanDate(rs.getDate("fecha_prestamo").toLocalDate());
        java.sql.Date returnDate = rs.getDate("fecha_devolucion");
        if (returnDate != null) l.setReturnDate(returnDate.toLocalDate());
        l.setExpirationDate(rs.getDate("fecha_limite").toLocalDate());
        l.setPenalty(rs.getDouble("multa"));
        l.setState(rs.getString("estado"));
        return l;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.dao.imp;

import com.libronova.dao.UserDAO;
import com.libronova.model.Role;
import com.libronova.model.User;
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
public class UserDAOImp implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImp.class.getName());

    @Override
    public boolean create(User user) throws Exception {
        String sql = """
            INSERT INTO usuarios (username, password, nombre, email, estado, role_id, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getState());
            ps.setInt(6, user.getRole().getId());
            ps.setTimestamp(7, Timestamp.valueOf(user.getCreatedAt()));

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[POST] /usuarios - Usuario agregado: " + user.getUsername());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al crear usuario", e);
            throw e;
        }
    }

    @Override
    public boolean update(User user) throws Exception {
        String sql = """
            UPDATE usuarios
            SET password = ?, nombre = ?, email = ?, estado = ?, role_id = ?
            WHERE username = ?
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getState());
            ps.setInt(5, user.getRole().getId());
            ps.setString(6, user.getUsername());

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[PATCH] /usuarios/" + user.getUsername());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar usuario", e);
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[DELETE] /usuarios/" + id);
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar usuario", e);
            throw e;
        }
    }

    @Override
    public Optional<User> searchByUser(String username) throws Exception {
        String sql = """
            SELECT u.*, r.nombre AS rol_nombre
            FROM usuarios u
            JOIN roles r ON u.role_id = r.id
            WHERE u.username = ?
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar usuario", e);
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> login(String username, String password) throws Exception {
        String sql = """
            SELECT u.*, r.nombre AS rol_nombre
            FROM usuarios u
            JOIN roles r ON u.role_id = r.id
            WHERE u.username = ? AND u.password = ? AND u.estado = 'ACTIVO'
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LOGGER.info(() -> "[POST] /login - Usuario autenticado: " + username);
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al iniciar sesión", e);
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public List<User> listAll() throws Exception {
        String sql = """
            SELECT u.*, r.nombre AS rol_nombre
            FROM usuarios u
            JOIN roles r ON u.role_id = r.id
        """;
        List<User> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
            LOGGER.info("[GET] /usuarios");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar usuarios", e);
            throw e;
        }
        return list;
    }

    // ============================
    // 🔹 Mapear ResultSet → User
    // ============================
    private User mapRow(ResultSet rs) throws SQLException {
        User u = new User();
        Role role = new Role();

        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setName(rs.getString("nombre"));
        u.setEmail(rs.getString("email"));
        u.setState(rs.getString("estado"));
        u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        role.setId(rs.getInt("role_id"));
        role.setName(rs.getString("rol_nombre"));

        u.setRole(role);
        return u;
    }
}
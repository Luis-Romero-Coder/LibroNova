/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.dao.imp;

import com.libronova.dao.MemberDAO;
import com.libronova.model.Member;
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
public class MemberDAOImp implements MemberDAO {

    private static final Logger LOGGER = Logger.getLogger(MemberDAOImp.class.getName());

    @Override
    public boolean create(Member member) throws Exception {
        String sql = "INSERT INTO socios (cedula, nombre, telefono, direccion, estado, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getDocument());
            ps.setString(2, member.getName());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getAddress());
            ps.setString(5, member.getState());
            ps.setTimestamp(6, Timestamp.valueOf(member.getCreatedAt()));

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[POST] /members - Nuevo miembro agregado: " + member.getName());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al crear miembro", e);
            throw e;
        }
    }

    @Override
    public boolean update(Member member) throws Exception {
        String sql = "UPDATE socios SET nombre=?, telefono=?, direccion=?, estado=? WHERE cedula=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getPhone());
            ps.setString(3, member.getAddress());
            ps.setString(4, member.getState());
            ps.setString(5, member.getDocument());

            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[PATCH] /members/" + member.getDocument());
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar miembro", e);
            throw e;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM socios WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            LOGGER.info(() -> "[DELETE] /members/" + id);
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar miembro", e);
            throw e;
        }
    }

    @Override
    public Optional<Member> searchByDocument(String document) throws Exception {
        String sql = "SELECT * FROM socios WHERE cedula = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, document);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar miembro", e);
            throw e;
        }
        return Optional.empty();
    }

    @Override
    public List<Member> listAll() throws Exception {
        String sql = "SELECT * FROM socios";
        List<Member> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
            LOGGER.info("[GET] /members");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar miembros", e);
            throw e;
        }
        return list;
    }

    private Member mapRow(ResultSet rs) throws SQLException {
        Member m = new Member();
        m.setId(rs.getInt("id"));
        m.setDocument(rs.getString("cedula"));
        m.setName(rs.getString("nombre"));
        m.setPhone(rs.getString("telefono"));
        m.setAddress(rs.getString("direccion"));
        m.setState(rs.getString("estado"));
        m.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return m;
    }
}

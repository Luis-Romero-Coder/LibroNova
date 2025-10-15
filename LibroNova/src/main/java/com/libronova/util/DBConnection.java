/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coder
 */
public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    public static Connection getConnection() {
        try {
            String url = ConfigUtil.get("db.url");
            String user = ConfigUtil.get("db.user");
            String password = ConfigUtil.get("db.password");

            Connection conn = DriverManager.getConnection(url, user, password);
            logger.info("Conexión establecida con la base de datos: " + url);
            return conn;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al conectar con la base de datos", e);
            return null;
        }
    }
}

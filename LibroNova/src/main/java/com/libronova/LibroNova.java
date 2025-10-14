/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.libronova;

import com.libronova.util.ConfigUtil;
import com.libronova.util.DBConnection;
import com.libronova.util.LoggerUtil;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Coder
 */
public class LibroNova {
    private static final Logger logger = Logger.getLogger(LibroNova.class.getName());

    public static void main(String[] args) {
        try {
            
            LoggerUtil.init();

            logger.info("Iniciando aplicación LibroNova...");

          
            ConfigUtil.loadProperties();

            
            try (Connection conn = DBConnection.getConnection()) {
                if (conn != null) {
                    logger.info("Conexión a la base de datos establecida correctamente.");
                } else {
                    logger.warning("No se pudo establecer la conexión a la base de datos.");
                }
            }

          
            JOptionPane.showMessageDialog(null, 
                "Bienvenido a LibroNova 📚\nConexión a la base de datos exitosa.", 
                "LibroNova", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al iniciar la aplicación", e);
            JOptionPane.showMessageDialog(null, 
                "Error al iniciar la aplicación:\n" + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

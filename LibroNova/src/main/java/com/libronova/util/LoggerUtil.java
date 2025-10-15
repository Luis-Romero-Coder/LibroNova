/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.util;

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author Coder
 */
public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());
    
    public static void init() {
        try (InputStream config = LoggerUtil.class.getClassLoader().getResourceAsStream("logging.properties")) {
            if (config != null) {
                LogManager.getLogManager().readConfiguration(config);
                logger.info("Sistema de logs inicializado correctamente.");
            } else {
                System.err.println("No se encontró logging.properties. Usando configuración por defecto.");
            }
        } catch (Exception e) {
            System.err.println("Error al inicializar el logger: " + e.getMessage());
        }
    }
}

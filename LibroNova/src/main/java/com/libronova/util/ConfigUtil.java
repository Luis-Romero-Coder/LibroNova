/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Coder
 */
public class ConfigUtil {
    private static final Logger logger = Logger.getLogger(ConfigUtil.class.getName());
    private static final Properties props = new Properties();

    public static void loadProperties() {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("No se encontró el archivo config.properties en resources/");
            }
            props.load(input);
            logger.info("Archivo config.properties cargado correctamente.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al cargar config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(props.getProperty(key));
        } catch (Exception e) {
            logger.warning("Error al convertir propiedad: " + key + " a entero. Usando valor por defecto.");
            return defaultValue;
        }
    }

    public static double getDouble(String key, double defaultValue) {
        try {
            return Double.parseDouble(props.getProperty(key));
        } catch (Exception e) {
            logger.warning("Error al convertir propiedad: " + key + " a número. Usando valor por defecto.");
            return defaultValue;
        }
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.controller;

import com.libronova.model.Lending;
import com.libronova.service.LendingService;
import com.libronova.service.imp.LendingServiceImp;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Coder
 */
public class LendingController {

    private static final Logger LOGGER = Logger.getLogger(LendingController.class.getName());
    private final LendingService lendingService = new LendingServiceImp();

    public void create() {
        try {
            int bookId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro:"));
            int memberId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del socio:"));
            int userId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su ID de usuario:"));
            LocalDate expiration = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha límite (yyyy-MM-dd):"));

            Lending l = new Lending(bookId, memberId, userId, expiration);

            boolean exito = lendingService.create(l);
            if (exito) JOptionPane.showMessageDialog(null, "✅ Préstamo registrado correctamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al crear préstamo", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void listAll() {
        try {
            List<Lending> list = lendingService.listAll();
            StringBuilder sb = new StringBuilder();
            for (Lending l : list) sb.append(l).append("\n");
            JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No hay préstamos.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al listar préstamos", e);
        }
    }

    public void listOverdue() {
        try {
            List<Lending> list = lendingService.listOverdue();
            StringBuilder sb = new StringBuilder();
            for (Lending l : list) sb.append(l).append("\n");
            JOptionPane.showMessageDialog(null, sb.length() > 0 ? sb.toString() : "No hay préstamos vencidos.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al listar préstamos vencidos", e);
        }
    }
}
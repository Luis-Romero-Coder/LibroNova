/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.controller;

import com.libronova.model.Member;
import com.libronova.service.MemberService;
import com.libronova.service.imp.MemberServiceImp;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Coder
 */
public class MemberController {

    private static final Logger LOGGER = Logger.getLogger(MemberController.class.getName());
    private final MemberService memberService = new MemberServiceImp();

    public void create() {
        try {
            String document = JOptionPane.showInputDialog("Ingrese el documento del miembro:");
            String name = JOptionPane.showInputDialog("Ingrese el nombre:");
            String phone = JOptionPane.showInputDialog("Ingrese el teléfono:");
            String address = JOptionPane.showInputDialog("Ingrese la dirección:");

            Member member = new Member(document, name, phone, address);
            boolean exito = memberService.create(member);

            if (exito)
                JOptionPane.showMessageDialog(null, "✅ Miembro registrado correctamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al registrar miembro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void update() {
        try {
            String document = JOptionPane.showInputDialog("Ingrese el documento del miembro a actualizar:");
            Optional<Member> opt = memberService.searchByDocument(document);

            if (opt.isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ No se encontró un miembro con ese documento.");
                return;
            }

            Member m = opt.get();
            String name = JOptionPane.showInputDialog("Nuevo nombre:", m.getName());
            String phone = JOptionPane.showInputDialog("Nuevo teléfono:", m.getPhone());
            String address = JOptionPane.showInputDialog("Nueva dirección:", m.getAddress());
            String state = JOptionPane.showInputDialog("Nuevo estado (ACTIVO/INACTIVO):", m.getState());

            m.setName(name);
            m.setPhone(phone);
            m.setAddress(address);
            m.setState(state);

            boolean actualizado = memberService.update(m);
            if (actualizado)
                JOptionPane.showMessageDialog(null, "✅ Miembro actualizado correctamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar miembro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void delete() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del miembro a eliminar:"));
            boolean eliminado = memberService.delete(id);

            if (eliminado)
                JOptionPane.showMessageDialog(null, "✅ Miembro eliminado correctamente.");
            else
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró el miembro con ID " + id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar miembro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void searchByDocument() {
        try {
            String document = JOptionPane.showInputDialog("Ingrese el documento del miembro:");
            Optional<Member> opt = memberService.searchByDocument(document);

            if (opt.isPresent()) {
                Member m = opt.get();
                JOptionPane.showMessageDialog(null,
                        "📘 Miembro encontrado:\n" +
                                "ID: " + m.getId() + "\n" +
                                "Nombre: " + m.getName() + "\n" +
                                "Teléfono: " + m.getPhone() + "\n" +
                                "Dirección: " + m.getAddress() + "\n" +
                                "Estado: " + m.getState());
            } else {
                JOptionPane.showMessageDialog(null, "❌ No existe un miembro con ese documento.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al buscar miembro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void listAll() {
        try {
            List<Member> list = memberService.listAll();
            StringBuilder sb = new StringBuilder("📋 Lista de miembros:\n\n");

            for (Member m : list) {
                sb.append(String.format("[%d] %s - %s (%s)\n",
                        m.getId(), m.getName(), m.getDocument(), m.getState()));
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al listar miembros", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }
}
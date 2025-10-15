/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.controller;

import com.libronova.model.Book;
import com.libronova.service.BookService;
import com.libronova.service.imp.BookServiceImp;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Coder
 */
public class BookController {

    private static final Logger LOGGER = Logger.getLogger(BookController.class.getName());
    private final BookService bookService = new BookServiceImp();

    public void create() {
        try {
            String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro:");
            String title = JOptionPane.showInputDialog("Ingrese el título:");
            String author = JOptionPane.showInputDialog("Ingrese el autor:");
            String category = JOptionPane.showInputDialog("Ingrese la categoría:");
            int total = Integer.parseInt(JOptionPane.showInputDialog("Ejemplares totales:"));
            int disponibles = Integer.parseInt(JOptionPane.showInputDialog("Ejemplares disponibles:"));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio de referencia:"));

            Book book = new Book();
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setTotalCopies(total);
            book.setCopiesAviable(disponibles);
            book.setReferencePrice(precio);

            boolean exito = bookService.create(book);

            if (exito)
                JOptionPane.showMessageDialog(null, "✅ Libro registrado correctamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al registrar libro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void update() {
        try {
            String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro a actualizar:");
            Optional<Book> opt = bookService.searchByIsbn(isbn);

            if (opt.isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ No se encontró un libro con ese ISBN.");
                return;
            }

            Book b = opt.get();
            String title = JOptionPane.showInputDialog("Nuevo título:", b.getTitle());
            String author = JOptionPane.showInputDialog("Nuevo autor:", b.getAuthor());
            String category = JOptionPane.showInputDialog("Nueva categoría:", b.getCategory());
            int total = Integer.parseInt(JOptionPane.showInputDialog("Ejemplares totales:", b.getTotalCopies()));
            int disponibles = Integer.parseInt(JOptionPane.showInputDialog("Ejemplares disponibles:", b.getCopiesAviable()));
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio de referencia:", b.getReferencePrice()));
            boolean activo = JOptionPane.showConfirmDialog(null, "¿El libro está activo?", "Estado", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            b.setTitle(title);
            b.setAuthor(author);
            b.setCategory(category);
            b.setTotalCopies(total);
            b.setCopiesAviable(disponibles);
            b.setReferencePrice(precio);
            b.setState(activo);

            boolean actualizado = bookService.update(b);
            if (actualizado)
                JOptionPane.showMessageDialog(null, "✅ Libro actualizado correctamente.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar libro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void delete() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro a eliminar:"));
            boolean eliminado = bookService.delete(id);

            if (eliminado)
                JOptionPane.showMessageDialog(null, "✅ Libro eliminado correctamente.");
            else
                JOptionPane.showMessageDialog(null, "⚠️ No se encontró el libro con ID " + id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar libro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void searchByIsbn() {
        try {
            String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro:");
            Optional<Book> opt = bookService.searchByIsbn(isbn);

            if (opt.isPresent()) {
                Book b = opt.get();
                JOptionPane.showMessageDialog(null,
                        "📘 Libro encontrado:\n" +
                                "Título: " + b.getTitle() + "\n" +
                                "Autor: " + b.getAuthor() + "\n" +
                                "Categoría: " + b.getCategory() + "\n" +
                                "Stock: " + b.getCopiesAviable() + "/" + b.getTotalCopies() + "\n" +
                                "Precio: $" + b.getReferencePrice() + "\n" +
                                "Estado: " + (b.state() ? "ACTIVO" : "INACTIVO"));
            } else {
                JOptionPane.showMessageDialog(null, "❌ No existe un libro con ese ISBN.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al buscar libro", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }

    public void listAll() {
        try {
            List<Book> list = bookService.listAll();
            StringBuilder sb = new StringBuilder("📚 Lista de libros:\n\n");

            for (Book b : list) {
                sb.append(String.format("[%d] %s - %s (%s)\n",
                        b.getId(), b.getTitle(), b.getAuthor(),
                        b.state() ? "ACTIVO" : "INACTIVO"));
            }

            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al listar libros", e);
            JOptionPane.showMessageDialog(null, "❌ Error: " + e.getMessage());
        }
    }
}

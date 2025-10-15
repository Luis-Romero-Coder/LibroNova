/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.libronova.service;

import com.libronova.model.Lending;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author Coder
 */
public interface LendingService {
    boolean create(Lending lending) throws Exception;

    boolean update(Lending lending) throws Exception;

    boolean delete(int id) throws Exception;

    Optional<Lending> searchById(int id) throws Exception;

    List<Lending> listAll() throws Exception;

    List<Lending> listOverdue() throws Exception;

    double calculatePenalty(Lending lending) throws Exception;
}

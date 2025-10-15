/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.service.imp;

import com.libronova.dao.LendingDAO;
import com.libronova.dao.imp.LendingDAOImp;
import com.libronova.model.Lending;
import com.libronova.service.LendingService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
/**
 *
 * @author Coder
 */
public class LendingServiceImp implements LendingService {

    private static final Logger LOGGER = Logger.getLogger(LendingServiceImp.class.getName());
    private final LendingDAO lendingDAO = new LendingDAOImp();
    private static final double PENALTY_PER_DAY = 1500; // Puedes leerlo desde ConfigUtil

    @Override
    public boolean create(Lending lending) throws Exception {
        return lendingDAO.create(lending);
    }

    @Override
    public boolean update(Lending lending) throws Exception {
        return lendingDAO.update(lending);
    }

    @Override
    public boolean delete(int id) throws Exception {
        return lendingDAO.delete(id);
    }

    @Override
    public Optional<Lending> searchById(int id) throws Exception {
        return lendingDAO.searchById(id);
    }

    @Override
    public List<Lending> listAll() throws Exception {
        return lendingDAO.listAll();
    }

    @Override
    public List<Lending> listOverdue() throws Exception {
        return lendingDAO.listOverdue();
    }

    @Override
    public double calculatePenalty(Lending lending) throws Exception {
        return lending.calcularMulta(PENALTY_PER_DAY);
    }
}

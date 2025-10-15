/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.libronova.dao;

import com.libronova.model.Member;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author Coder
 */
public interface MemberDAO {
    
    boolean create(Member member) throws Exception;

    boolean update(Member member) throws Exception;

    boolean delete(int id) throws Exception;

    Optional<Member> searchByDocument(String document) throws Exception;

    List<Member> listAll() throws Exception;
}


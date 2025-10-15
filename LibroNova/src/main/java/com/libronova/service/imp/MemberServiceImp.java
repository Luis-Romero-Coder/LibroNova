/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.libronova.service.imp;

import com.libronova.dao.MemberDAO;
import com.libronova.dao.imp.MemberDAOImp;
import com.libronova.model.Member;
import com.libronova.service.MemberService;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author Coder
 */
public class MemberServiceImp implements MemberService {

    private MemberDAO memberDAO;

    public MemberServiceImp() {
        this.memberDAO = new MemberDAOImp();
    }

    @Override
    public boolean create(Member member) throws Exception {
        if (memberDAO.searchByDocument(member.getDocument()).isPresent()) {
            throw new Exception("Member document already exists.");
        }
        return memberDAO.create(member);
    }

    @Override
    public boolean update(Member member) throws Exception {
        Optional<Member> existing = memberDAO.searchByDocument(member.getDocument());
        if (existing.isPresent() && existing.get().getId() != member.getId()) {
            throw new Exception("Document already exists for another member.");
        }
        return memberDAO.update(member);
    }

    @Override
    public boolean delete(int id) throws Exception {
        return memberDAO.delete(id);
    }

    @Override
    public Optional<Member> searchByDocument(String document) throws Exception {
        return memberDAO.searchByDocument(document);
    }

    @Override
    public List<Member> listAll() throws Exception {
        return memberDAO.listAll();
    }
}
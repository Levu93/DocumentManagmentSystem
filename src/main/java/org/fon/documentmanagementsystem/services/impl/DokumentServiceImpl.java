/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services.impl;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Dokument;
import org.fon.documentmanagementsystem.repositories.DokumentRepository;
import org.fon.documentmanagementsystem.services.DokumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vukasin
 */
@Service
public class DokumentServiceImpl implements DokumentService {
    
    @Autowired
    DokumentRepository dokumentRepository;
    
    @Override
    public Dokument findOne(long id) {
        return dokumentRepository.findOne(id);
    }

    @Override
    public List<Dokument> findAll() {
        return (List<Dokument>) dokumentRepository.findAll();
    }

    @Override
    public void save(Dokument aktivnost) {
        dokumentRepository.save(aktivnost);
    }
}

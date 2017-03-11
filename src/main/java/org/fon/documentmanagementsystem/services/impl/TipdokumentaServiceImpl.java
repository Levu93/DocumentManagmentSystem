/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Tipdokumenta;
import org.fon.documentmanagementsystem.repositories.TipdokumentaRepository;
import org.fon.documentmanagementsystem.services.TipdokumentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vukasin
 */
@Service
public class TipdokumentaServiceImpl implements TipdokumentaService{

    @Autowired
    TipdokumentaRepository tipDocumentaRepository;
    
    @Override
    public List<Tipdokumenta> findAll() {
        List<Tipdokumenta> target;
        target = new ArrayList<>();
        tipDocumentaRepository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public int vratiId() {
        List<Tipdokumenta> target;
        target = new ArrayList<>();
        tipDocumentaRepository.findAll().forEach(target::add);
        return target.size();
    }

    @Override
    public Tipdokumenta sacuvajTipDokumenta(Tipdokumenta t) {
        return tipDocumentaRepository.save(t);
    }

    @Override
    public Tipdokumenta findOne(long id) {
        return tipDocumentaRepository.findOne(id);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Proces;
import org.fon.documentmanagementsystem.repositories.ProcesRepository;
import org.fon.documentmanagementsystem.services.ProcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nevenac
 */
@Service
public class ProcesServiceImpl implements ProcesService {

    @Autowired
    ProcesRepository procesRepository;

    @Override
    public List<Proces> findAll() {
        List<Proces> target;
        target = new ArrayList<>();
        procesRepository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public int vratiId() {
        List<Proces> target;
        target = new ArrayList<>();
        procesRepository.findAll().forEach(target::add);
        return target.size();
    }

    @Override
    public Proces save(Proces p) {
        return procesRepository.save(p);
    }

    @Override
    public List<Proces> findByNivo(int level) {
        List<Proces> target = new ArrayList<>();
        procesRepository.findByNivo(level).forEach(target::add);
        return target;
    }

}

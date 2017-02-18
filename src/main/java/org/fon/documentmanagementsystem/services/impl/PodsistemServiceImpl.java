/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.repositories.PodsistemRepository;
import org.fon.documentmanagementsystem.services.PodsistemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vukasin
 */
@Service
public class PodsistemServiceImpl implements PodsistemService {

    @Autowired
    private PodsistemRepository podsistemRepository;

    @Override
    public List<Podsistem> findAll() {
        List<Podsistem> target;
        target = new ArrayList<>();
        podsistemRepository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public int vratiId() {
        List<Podsistem> target;
        target = new ArrayList<>();
        podsistemRepository.findAll().forEach(target::add);
        return target.size();

    }

    @Override
    public void sacuvajPodsistem(Podsistem p) {
        Podsistem p1 = podsistemRepository.findOne(p.getId());
        if (p1 == null) {
            podsistemRepository.save(p);
        }
    }

}

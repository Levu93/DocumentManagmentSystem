/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services.impl;

import org.fon.documentmanagementsystem.domain.Rola;
import org.fon.documentmanagementsystem.repositories.RolaRepository;
import org.fon.documentmanagementsystem.services.RolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nevenac
 */
@Service
public class RolaServiceImpl implements RolaService{

    @Autowired
    RolaRepository rolaRepository;
    
    @Override
    public Rola findOne(long id) {
        return rolaRepository.findOne(id);
    }
    
    
}

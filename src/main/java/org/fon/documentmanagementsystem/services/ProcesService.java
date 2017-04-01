/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Proces;

/**
 *
 * @author nevenac
 */
public interface ProcesService {

    List<Proces> findAll();

    int vratiId();

    Proces save(Proces p);
    
    List<Proces> findByNivo(int level);
    
    Proces findOne(long id);

    void delete(Proces akt);
}

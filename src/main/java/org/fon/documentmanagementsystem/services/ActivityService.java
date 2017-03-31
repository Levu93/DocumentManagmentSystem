/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;


/**
 *
 * @author nevenac
 */
public interface ActivityService {
    
    Aktivnost findOne(long id);
    
    List<Aktivnost> findAll();

    void save(Aktivnost aktivnost);

    void delete(Aktivnost akt);
    
}

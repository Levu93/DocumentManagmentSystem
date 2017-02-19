/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Podsistem;
/**
 *
 * @author Vukasin
 */
public interface PodsistemService {
    
    List<Podsistem> findAll();
    
    int vratiId();
    
    Podsistem sacuvajPodsistem(Podsistem p);

    Podsistem findOne(long id);
}

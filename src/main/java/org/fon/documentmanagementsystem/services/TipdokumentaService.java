/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Tipdokumenta;

/**
 *
 * @author Vukasin
 */
public interface TipdokumentaService {
    
    List<Tipdokumenta> findAll();
    
    int vratiId();
    
    Tipdokumenta sacuvajTipDokumenta(Tipdokumenta t);

    public Tipdokumenta findOne(long id);
}

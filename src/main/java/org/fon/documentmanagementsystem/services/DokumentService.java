/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Dokument;

/**
 *
 * @author Vukasin
 */
public interface DokumentService {
    
    Dokument findOne(long id);
    
    List<Dokument> findAll();

    void save(Dokument dokument);

    public void delete(Dokument document);
}

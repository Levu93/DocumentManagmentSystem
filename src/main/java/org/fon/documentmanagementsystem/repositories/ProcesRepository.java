/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.repositories;

import java.io.Serializable;
import org.fon.documentmanagementsystem.domain.Proces;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nevenac
 */
@Repository
public interface ProcesRepository extends CrudRepository<Proces, Long>{
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.repositories;

import org.fon.documentmanagementsystem.domain.Podsistem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vukasin
 */

@Repository
public interface PodsistemRepository extends CrudRepository<Podsistem, Long>{
    
}

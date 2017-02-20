/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services;

import org.fon.documentmanagementsystem.domain.Rola;

/**
 *
 * @author nevenac
 */
public interface RolaService {
    
    Rola findOne(long id);
    
}

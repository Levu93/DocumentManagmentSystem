/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services;

import java.util.List;
import org.fon.documentmanagementsystem.domain.User;

/**
 *
 * @author Vukasin
 */
public interface UserService {
    
    User login(String username, String password);
    
    User findOne(String username);
    
    List<User>  findAll();

    User save(User admin);
}

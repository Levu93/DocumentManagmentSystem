/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.repositories.UserRepository;
import org.fon.documentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vukasin
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findOne(String username) {
        return  userRepository.findOne(username);
    }

    @Override
    public List<User> findAll() {
        List<User> target;
        target = new ArrayList<>();
        userRepository.findAll().forEach(target::add);
        return target;
    }
    
    
}

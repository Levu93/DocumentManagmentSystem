/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.services.impl;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.repositories.ActivityRepository;
import org.fon.documentmanagementsystem.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nevenac
 */
@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    ActivityRepository activityrepository;
    
    @Override
    public Aktivnost findOne(long id) {
        return activityrepository.findOne(id);
    }

    @Override
    public List<Aktivnost> findAll() {
        return (List<Aktivnost>) activityrepository.findAll();
    }

    @Override
    public void save(Aktivnost aktivnost) {
        activityrepository.save(aktivnost);
    }
    
}

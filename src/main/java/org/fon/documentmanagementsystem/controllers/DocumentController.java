/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nevenac
 */
@Controller
@RequestMapping("/document")
public class DocumentController {
    
    @Autowired
    ActivityService activityService;
    
    @RequestMapping(path = "/add_new/{id}", method = RequestMethod.GET)
    public ModelAndView addDocument(@PathVariable("id") long id) {
        
        Aktivnost target = activityService.findOne(id);
        
        ModelAndView mv = new ModelAndView("document_add");
        mv.addObject("activity", target);
        return mv;
    }
    
}

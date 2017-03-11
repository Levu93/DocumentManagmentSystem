/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.domain.Tipdokumenta;
import org.fon.documentmanagementsystem.services.ActivityService;
import org.fon.documentmanagementsystem.services.TipdokumentaService;
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
    
    @Autowired
    TipdokumentaService tipDokumentaService;
    
    @RequestMapping(path = "/add_new/{id}", method = RequestMethod.GET)
    public ModelAndView addDocument(@PathVariable("id") long id) {
        
        Aktivnost target = activityService.findOne(id);
        
        List<Tipdokumenta> documenttypes = tipDokumentaService.findAll();
        
        ModelAndView mv = new ModelAndView("document_add");
        mv.addObject("documenttypes", documenttypes);
        mv.addObject("activity", target);
        return mv;
    }
    
}

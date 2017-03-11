/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.domain.Proces;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.dto.UserDto;
import org.fon.documentmanagementsystem.services.ProcesService;
import org.fon.documentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nevenac
 */
@Controller
@RequestMapping("/process/")
public class ProcesController {

    @Autowired
    ProcesService procesService;
    
    @Autowired
    UserService userService;

    @RequestMapping(path = "/overview", method = RequestMethod.GET)
    public ModelAndView showAllProcesses() {
        
        ModelAndView mv = new ModelAndView("process_overview");
        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        List<Proces> zeljeni = new ArrayList<>();
        for (Proces proces : sviProcesi) {
            if (proces.getNivo() == 1) {
                zeljeni.add(proces);
            }
        }
        //ovde sam vise da ubacimo da ih trazi po nivoima, nego sve pa da izbacujemo, al nisam uspela to da uradim
        mv.addObject("processes", zeljeni);
        return mv;
    }

    @RequestMapping(path = "/add_new", method = RequestMethod.GET)
    public ModelAndView addProcessGetPage() {
        ModelAndView mv = new ModelAndView("process_add");
        return mv;
    }

    @RequestMapping(path = "/add_new", method = RequestMethod.POST)
    public ModelAndView addNewProcess(String procesname, String processign, String procesdescription) {
        
        Proces p = new Proces();
        int id1 = procesService.vratiId() + 1;
        long x = id1;
        p.setId(x);
        p.setNaziv(procesname);
        p.setOznaka(processign);
        p.setOpis(procesdescription);
        
        //ako ima laksi nacin, izmeni
        UserDto userdetail =(UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(userdetail.getUsername());
        
        p.setIdPodsistema(user.getIdPodsistema());
        
        p.setNivo(0);
        
        procesService.save(p);
        
        ModelAndView mv = new ModelAndView("process_overview");
        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        mv.addObject("processes", sviProcesi);
        return mv;
    }
    
    @RequestMapping(path = "/overviewusers", method = RequestMethod.GET)
    public ModelAndView getProccessOverviewForUsers(){
        ModelAndView mv = new ModelAndView("processesforusers");
        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        List<Proces> zeljeni = new ArrayList<>();
        for (Proces proces : sviProcesi) {
            if (proces.getNivo() == 1) {
                zeljeni.add(proces);
            }
        }
        //ovde sam vise da ubacimo da ih trazi po nivoima, nego sve pa da izbacujemo, al nisam uspela to da uradim
        mv.addObject("processes", zeljeni);
        return mv;
    }
    
    
}

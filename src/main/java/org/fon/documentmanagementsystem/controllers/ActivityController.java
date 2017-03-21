/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.domain.Proces;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.dto.UserDto;
import org.fon.documentmanagementsystem.services.ActivityService;
import org.fon.documentmanagementsystem.services.ProcesService;
import org.fon.documentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/activity/")
public class ActivityController {

    @Autowired
    ProcesService procesService;

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;

    @RequestMapping(path = "/add_new/{id}", method = RequestMethod.GET)
    public ModelAndView addActivity(@PathVariable("id") long id) {

        Proces target = procesService.findOne(id);

        ModelAndView mv = new ModelAndView("activity_add");
        mv.addObject("process", target);
        return mv;
    }

    @RequestMapping(path = "/add_new/{id}", method = RequestMethod.POST)
    public ModelAndView addNewActivity(@PathVariable("id") long id, String activityname, String activitysign, String activitydescription) {

        long idakt = activityService.findAll().size() + 1;

        Proces target = procesService.findOne(id);

        Aktivnost aktivnost = new Aktivnost();
        aktivnost.setId(idakt);
        aktivnost.setNaziv(activityname);
        aktivnost.setOznaka(activitysign);
        aktivnost.setOpis(activitydescription);
        aktivnost.setIdProcesa(target);
        
        activityService.save(aktivnost);

        ModelAndView mv = new ModelAndView("process_overview");

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();

        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        List<Proces> zeljeni = new ArrayList<>();
        for (Proces proces : sviProcesi) {
            if (proces.getNivo() == 1 && proces.getIdPodsistema().equals(podsistemusera)) {
                zeljeni.add(proces);
            }
        }
        mv.addObject("processes", zeljeni);
        return mv;
    }

}

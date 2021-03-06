/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.services.PodsistemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vukasin
 */
@Controller
@RequestMapping("/subsystems/")
public class PodsistemController {

    @Autowired
    private PodsistemService podsistemService;

    @RequestMapping(path = "/overview", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("subsystem_overview");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }

    @RequestMapping(path = "/add_new_subsystem", method = RequestMethod.GET)
    public ModelAndView addSubsystem() {
        ModelAndView mv = new ModelAndView("subsystem_add");
        System.out.println("U kontroleru sam");
        return mv;
    }

    @RequestMapping(path = "/add_new_subsystem", method = RequestMethod.POST)
    public ModelAndView addNewSubsystem(String subsystemname, String subsystemsign, String subsystemdescription) {
        Podsistem ps = new Podsistem();
//        int id1 = podsistemService.vratiId() + 1;
//        long x = id1;
//        ps.setId(x);
        ps.setNaziv(subsystemname);
        ps.setOznaka(subsystemsign);
        ps.setOpis(subsystemdescription);
        podsistemService.sacuvajPodsistem(ps);
        ModelAndView mv = new ModelAndView("subsystem_overview");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }
    
    @RequestMapping(path = "/add_new_subsystem/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") long id, String subsystemname, String subsystemdescription) {
        Podsistem ps = podsistemService.findOne(id);
        
        ps.setNaziv(subsystemname);
        ps.setOpis(subsystemdescription);
        podsistemService.sacuvajPodsistem(ps);
        ModelAndView mv = new ModelAndView("subsystem_overview");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }

    @RequestMapping(path = "/details/{id}", method = RequestMethod.GET)
    public ModelAndView showSubsystem(@PathVariable("id") long id
    ) {
        Podsistem podsistem = podsistemService.findOne(id);
        // if (podsistem == null) throw new CustomException("There is no company with id " + id, "404");
        // List<User> usersOfCompany = userService.findUsersOfCompany(company);
        // System.out.println("@@@@@@@@@@@ " + usersOfCompany);
        ModelAndView mv = new ModelAndView("subsystem_add");
        mv.addObject("subsystem", podsistem);
        // mv.addObject("users", usersOfCompany);
        return mv;
    }
}

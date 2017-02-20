/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.domain.Rola;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.services.PodsistemService;
import org.fon.documentmanagementsystem.services.RolaService;
import org.fon.documentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vukasin
 */
@Controller
@RequestMapping("/admins/")
public class UserController {

    @Autowired
    private PodsistemService podsistemService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RolaService rolaService;

    @RequestMapping(path = "/overview", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("admin_overview");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }

    @RequestMapping(path = "/add_new_admin", method = RequestMethod.GET)
    public ModelAndView addAdminPage() {
        ModelAndView mv = new ModelAndView("admin_add");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }

    @RequestMapping(path = "/add_new_admin", method = RequestMethod.POST)
    public ModelAndView addAdmin(String adminname, String adminlastname, String adminusername, String adminpass, String adminsubsystem) {
        
        User admin = new User(adminusername);
        admin.setIme(adminname);
        admin.setPassword(adminpass);
        admin.setPrezime(adminlastname);
        Rola rola = rolaService.findOne(1);
        admin.setIdRole(rola);
        
        int admsubs = Integer.parseInt(adminsubsystem);
        Podsistem subs = podsistemService.findOne(admsubs);
        admin.setIdPodsistema(subs);
        
        userService.save(admin);
        
        ModelAndView mv = new ModelAndView("admin_overview");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }
}

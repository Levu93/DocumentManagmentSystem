/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.domain.Rola;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.dto.UserDto;
import org.fon.documentmanagementsystem.services.PodsistemService;
import org.fon.documentmanagementsystem.services.RolaService;
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

    @RequestMapping(path = "adm/overview", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("admin_overview");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }

    @RequestMapping(path = "usr/user_overview", method = RequestMethod.GET)
    public ModelAndView userOverview() {

        ModelAndView mv = new ModelAndView("user_overview");

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());

        List<User> sviuseri = new ArrayList<>();
        sviuseri = userService.findAll();

        List<User> zeljeni = new ArrayList<>();

        for (User user : sviuseri) {
            if (user.getIdPodsistema() != null) {
                if (user.getIdPodsistema().getId().equals(userKojiCuva.getIdPodsistema().getId())) {
                    zeljeni.add(user);
                }
            }
        }
        zeljeni.remove(userKojiCuva);

        mv.addObject("users", zeljeni);
        return mv;
    }

    @RequestMapping(path = "adm/add_new_admin", method = RequestMethod.GET)
    public ModelAndView addAdminPage() {
        ModelAndView mv = new ModelAndView("admin_add");
        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();
        mv.addObject("subsystems", sviPodsistemi);
        return mv;
    }

    @RequestMapping(path = "usr/add_new_user", method = RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView mv = new ModelAndView("user_add");
        return mv;
    }

    @RequestMapping(path = "adm/add_new_admin", method = RequestMethod.POST)
    public ModelAndView addAdmin(String adminname, String adminlastname, String adminusername, String adminpass, String adminsubsystem) {

        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();

        User x = userService.findOne(adminusername);

        if (x != null) {
            ModelAndView mv = new ModelAndView("admin_add");
            mv.addObject("error", "Username already exists!!!");
            mv.addObject("ime", adminname);
            mv.addObject("prezime", adminlastname);

            mv.addObject("subsystems", sviPodsistemi);

            return mv;
        }
        if (adminpass == null || adminpass.isEmpty()) {
            ModelAndView mv = new ModelAndView("admin_add");
            mv.addObject("passgreska", true);
            mv.addObject("ime", adminname);
            mv.addObject("prezime", adminlastname);

            mv.addObject("subsystems", sviPodsistemi);

            return mv;
        }
        if (adminpass.length() < 8) {
            ModelAndView mv = new ModelAndView("admin_add");
            mv.addObject("passgreska", true);
            mv.addObject("ime", adminname);
            mv.addObject("prezime", adminlastname);

            mv.addObject("subsystems", sviPodsistemi);

            return mv;
        }

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());

        User user = new User(adminusername);
        user.setIme(adminname);
        user.setPassword(adminpass);
        user.setPrezime(adminlastname);

        ModelAndView mv = new ModelAndView();

        Rola rola = rolaService.findOne(2);
        user.setIdRole(rola);

        int admsubs = Integer.parseInt(adminsubsystem);
        Podsistem subs = podsistemService.findOne(admsubs);
        user.setIdPodsistema(subs);
        subs.getUserList().add(user);

        userService.save(user);
        podsistemService.sacuvajPodsistem(subs);
        mv = new ModelAndView("admin_overview");

        List<Podsistem> podsistemiposleCuvanja = podsistemService.findAll();

        mv.addObject("subsystems", podsistemiposleCuvanja);

        return mv;
    }

    @RequestMapping(path = "usr/add_new_user", method = RequestMethod.POST)
    public ModelAndView addUser(String adminname, String adminlastname, String adminusername, String adminpass, String adminsubsystem) {

        List<Podsistem> sviPodsistemi;
        sviPodsistemi = podsistemService.findAll();

        User x = userService.findOne(adminusername);

        if (x != null) {
            ModelAndView mv = new ModelAndView("user_add");
            mv.addObject("error", "Username already exists!!!");
            mv.addObject("ime", adminname);
            mv.addObject("prezime", adminlastname);

            mv.addObject("subsystems", sviPodsistemi);

            return mv;
        }
        
                if (adminpass == null || adminpass.isEmpty()) {
            ModelAndView mv = new ModelAndView("user_add");
            mv.addObject("passgreska", true);
            mv.addObject("ime", adminname);
            mv.addObject("prezime", adminlastname);

            return mv;
        }
        if (adminpass.length() < 8) {
            ModelAndView mv = new ModelAndView("user_add");
            mv.addObject("passgreska", true);
            mv.addObject("ime", adminname);
            mv.addObject("prezime", adminlastname);

            return mv;
        }
        
        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());

        User user = new User(adminusername);
        user.setIme(adminname);
        user.setPassword(adminpass);
        user.setPrezime(adminlastname);

        ModelAndView mv = new ModelAndView();

        Rola rola = rolaService.findOne(3);
        user.setIdRole(rola);
        user.setIdPodsistema(userKojiCuva.getIdPodsistema());
        userKojiCuva.getIdPodsistema().getUserList().add(user);

        userService.save(user);
        podsistemService.sacuvajPodsistem(userKojiCuva.getIdPodsistema());

        mv = new ModelAndView("user_overview");

        List<User> sviUseri;
        sviUseri = userService.findAll();

        List<User> zeljeni = new ArrayList<>();

        for (User us : sviUseri) {
            if (us.getIdPodsistema() != null) {
                if (us.getIdPodsistema().getId().equals(userKojiCuva.getIdPodsistema().getId())) {
                    zeljeni.add(us);
                }
            }
        }
        zeljeni.remove(userKojiCuva);

        mv.addObject("users", zeljeni);

        return mv;
    }

    @RequestMapping(path = "/adm/delete/{username}", method = RequestMethod.GET)
    public ModelAndView deleteFile(@PathVariable("username") String username) {

        User u = userService.findOne(username);

        userService.delete(u);

        Podsistem sub = u.getIdPodsistema();
        sub.getUserList().remove(u);
        podsistemService.sacuvajPodsistem(sub);

        ModelAndView mv = new ModelAndView("admin_overview");

        List<Podsistem> podsistemiposleCuvanja = podsistemService.findAll();

        mv.addObject("subsystems", podsistemiposleCuvanja);

        return mv;
    }

    @RequestMapping(path = "/usr/delete/{username}", method = RequestMethod.GET)
    public ModelAndView deleteUsers(@PathVariable("username") String username) {

        User u = userService.findOne(username);

        userService.delete(u);

        Podsistem sub = u.getIdPodsistema();
        sub.getUserList().remove(u);
        podsistemService.sacuvajPodsistem(sub);

        ModelAndView mv = new ModelAndView("user_overview");

        List<User> useripodsistema = userService.findAll();
        
        List<User> zeljeni = new ArrayList<>();
        
        for (User user : useripodsistema) {
            if (user.getIdPodsistema() != null) {
                if (user.getIdPodsistema().getId().equals(sub.getId())) {
                    zeljeni.add(user);
                }
            }
        }
        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        zeljeni.remove(userKojiCuva);
        mv.addObject("users", zeljeni);

        return mv;
    }
}

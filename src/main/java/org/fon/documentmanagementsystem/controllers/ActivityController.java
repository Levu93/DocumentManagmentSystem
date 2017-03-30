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

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ProcesService procesService;

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;

    @RequestMapping(path = "/adm/add_new", method = RequestMethod.GET)
    public ModelAndView addActivityNoSubprocess() {

        //nude se samo primitivni i iy tog podsistema
        List<Proces> sviProcesi = procesService.findAll();
        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();

        List<Proces> primitivni = new ArrayList<>();

        for (Proces proces : sviProcesi) {
            if (proces.getIdPodsistema().equals(podsistemusera) && proces.getPrimitivan()) {
                primitivni.add(proces);
            }
        }
        ModelAndView mv = new ModelAndView("activity_add");
        mv.addObject("procesi", primitivni);
        if (primitivni.isEmpty()) {
            mv = new ModelAndView("error", "error", "You can't add activities if there are no processes in the system!");
        }

        return mv;
    }

    @RequestMapping(path = "/adm/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") long id, String activityname, String activitysign, String activitydescription) {
        Aktivnost a = activityService.findOne(id);

        a.setNaziv(activityname);
        a.setOpis(activitydescription);
        a.setOznaka(activitysign);
        activityService.save(a);
        
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
        //ovde sam vise da ubacimo da ih trazi po nivoima, nego sve pa da izbacujemo, al nisam uspela to da uradim
        mv.addObject("processes", zeljeni);
        return mv;
    }

    @RequestMapping(path = "/adm/add_new/{id}", method = RequestMethod.GET)
    public ModelAndView addActivity(@PathVariable("id") long id) {

        Proces target = procesService.findOne(id);

        List<Proces> sviProcesi = procesService.findAll();
        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();

        List<Proces> primitivni = new ArrayList<>();

        for (Proces proces : sviProcesi) {
            if (proces.getIdPodsistema().equals(podsistemusera) && proces.getPrimitivan()) {
                primitivni.add(proces);
            }
        }

        ModelAndView mv = new ModelAndView("activity_add");
        mv.addObject("process", target);
        mv.addObject("procesi", primitivni);
        return mv;
    }

    @RequestMapping(path = "/adm/details/{id}", method = RequestMethod.GET)
    public ModelAndView activityDetails(@PathVariable("id") long id) {

        Aktivnost target = activityService.findOne(id);

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();

        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        List<Proces> zeljeni = new ArrayList<>();
        for (Proces proces : sviProcesi) {
            if (proces.getPrimitivan() && proces.getIdPodsistema().equals(podsistemusera)) {
                zeljeni.add(proces);
            }
        }

        ModelAndView mv = new ModelAndView("activity_add");
        mv.addObject("aktivnost", target);
        mv.addObject("procesi", zeljeni);
        return mv;
    }
    
    @RequestMapping(path = "/user/userdetails/{id}", method = RequestMethod.GET)
    public ModelAndView activityDetailsForUsers(@PathVariable("id") long id) {

        Aktivnost target = activityService.findOne(id);

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();

        ModelAndView mv = new ModelAndView("activity_details");
        mv.addObject("aktivnost", target);

        return mv;
    }

    @RequestMapping(path = "/adm/add_new/{id}", method = RequestMethod.POST)
    public ModelAndView addNewActivityForProcess(@PathVariable("id") long id, String activityname, String activitysign, String activitydescription) {

        //long idakt = activityService.findAll().size() + 1;

        Proces target = procesService.findOne(id);

        Aktivnost aktivnost = new Aktivnost();
        //aktivnost.setId(idakt);
        aktivnost.setNaziv(activityname);
        aktivnost.setOznaka(activitysign);
        aktivnost.setOpis(activitydescription);
        aktivnost.setIdProcesa(target);

        activityService.save(aktivnost);

        target.getAktivnostList().add(aktivnost);
        procesService.save(target);

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

    @RequestMapping(path = "/add_new", method = RequestMethod.POST)
    public ModelAndView addNewActivity(String activityname, String activitysign, String activitydescription, long procesactivity) {

        long idakt = activityService.findAll().size() + 1;

        Proces target = procesService.findOne(procesactivity);

        Aktivnost aktivnost = new Aktivnost();
        aktivnost.setId(idakt);
        aktivnost.setNaziv(activityname);
        aktivnost.setOznaka(activitysign);
        aktivnost.setOpis(activitydescription);
        aktivnost.setIdProcesa(target);

        activityService.save(aktivnost);

        target.getAktivnostList().add(aktivnost);
        procesService.save(target);

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

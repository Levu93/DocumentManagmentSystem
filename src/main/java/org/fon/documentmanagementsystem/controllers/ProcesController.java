/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.domain.Proces;
import org.fon.documentmanagementsystem.domain.Tipdokumenta;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.dto.TreeDto;
import org.fon.documentmanagementsystem.dto.UserDto;
import org.fon.documentmanagementsystem.services.PodsistemService;
import org.fon.documentmanagementsystem.services.ProcesService;
import org.fon.documentmanagementsystem.services.TipdokumentaService;
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
@RequestMapping("/processes/")
public class ProcesController {

    @Autowired
    ProcesService procesService;

    @Autowired
    UserService userService;

    @Autowired
    PodsistemService subsystemService;

    @Autowired
    TipdokumentaService tipDokumenataService;

    @RequestMapping(path = "adm/overview", method = RequestMethod.GET)
    public ModelAndView showAllProcesses() {

        ModelAndView mv = new ModelAndView("process_overview");

//        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User userKojiCuva = userService.findOne(userdetail.getUsername());
//        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();
//
//        List<Proces> sviProcesi;
//        sviProcesi = procesService.findAll();
//        List<Proces> zeljeni = new ArrayList<>();
//        for (Proces proces : sviProcesi) {
//            if (proces.getNivo() == 1 && proces.getIdPodsistema().equals(podsistemusera)) {
//                zeljeni.add(proces);
//            }
//        }
//        mv.addObject("processes", zeljeni);
        return mv;
    }

    @RequestMapping(path = "adm/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateProcess(@PathVariable("id") long id) {

        ModelAndView mv = new ModelAndView("process_add");
        Proces trazeni = procesService.findOne(id);
        mv.addObject("trazeni", trazeni);
        return mv;
    }

    @RequestMapping(path = "adm/add_new", method = RequestMethod.GET)
    public ModelAndView addProcessGetPage() {
        ModelAndView mv = new ModelAndView("process_add");
        return mv;
    }

    @RequestMapping(path = "adm/add_new_sub", method = RequestMethod.GET)
    public ModelAndView addSubProcessGetPage() {

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();

        ModelAndView mv = new ModelAndView("subprocesses_add");
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

    @RequestMapping(path = "adm/add_new_sub/{id}", method = RequestMethod.GET)
    public ModelAndView addSubproccessForProcess(@PathVariable("id") long id) {

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userKojiCuva = userService.findOne(userdetail.getUsername());
        Podsistem podsistemusera = userKojiCuva.getIdPodsistema();

        Proces target = procesService.findOne(id);

        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        List<Proces> zeljeni = new ArrayList<>();
        for (Proces proces : sviProcesi) {
            if (proces.getNivo() == 1 && proces.getIdPodsistema().equals(podsistemusera)) {
                zeljeni.add(proces);
            }
        }

        ModelAndView mv = new ModelAndView("subprocesses_add");

        mv.addObject("process", target);
        mv.addObject("processes", zeljeni);

        return mv;
    }

    @RequestMapping(path = "adm/add_new", method = RequestMethod.POST)
    public ModelAndView addNewProcess(String procesname, String processign, String procesdescription, boolean isprimitive) {

        Proces p = new Proces();
        p.setNaziv(procesname);
        p.setOznaka(processign);
        p.setOpis(procesdescription);
        p.setPrimitivan(isprimitive);

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(userdetail.getUsername());

        p.setIdPodsistema(user.getIdPodsistema());
        p.setNivo(1);

        procesService.save(p);

        ModelAndView mv = new ModelAndView("process_overview");
        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        mv.addObject("processes", sviProcesi);
        return mv;
    }

    @RequestMapping(path = "adm/add_new_tree", method = RequestMethod.POST)
    public String addNewProcessTree(String name, String sign, String description, boolean primitive) {
        Proces p = new Proces();
        p.setNaziv(name);
        p.setOznaka(sign);
        p.setOpis(description);
        p.setPrimitivan(primitive);

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(userdetail.getUsername());

        p.setIdPodsistema(user.getIdPodsistema());
        p.setNivo(1);
        procesService.save(p);
        user.getIdPodsistema().getProcesList().add(p);
        subsystemService.sacuvajPodsistem(user.getIdPodsistema());

        return "redirect:/processes/adm/overview";
    }

    @RequestMapping(path = "adm/add_new_sub_tree", method = RequestMethod.POST)
    public String addNewSubProcessTree(String name, String sign, String description, long parent, boolean primitive) {

        Proces subp = new Proces();
        subp.setNaziv(name);
        subp.setOznaka(sign);
        subp.setOpis(description);
        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(userdetail.getUsername());
        Proces parentProcess = procesService.findOne(parent);
        subp.setIdNadProcesa(parentProcess);
        subp.setNivo(parentProcess.getNivo() + 1);
        subp.setPrimitivan(primitive);
        subp.setIdPodsistema(user.getIdPodsistema()); //ovo sam dodala bez Sikice
        parentProcess.getProcesList().add(subp);
        user.getIdPodsistema().getProcesList().add(subp);
        subsystemService.sacuvajPodsistem(user.getIdPodsistema());
        return "redirect:/processes/adm/overview";
    }

    @RequestMapping(path = "adm/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") long id, String procesname, String procesdescription, boolean isprimitive) {

        Proces p = procesService.findOne(id);

        p.setNaziv(procesname);
        p.setOpis(procesdescription);
        p.setPrimitivan(isprimitive);

        procesService.save(p);
        Podsistem ps = p.getIdPodsistema();
        subsystemService.sacuvajPodsistem(ps);

        ModelAndView mv = new ModelAndView("process_overview");
        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        mv.addObject("processes", sviProcesi);
        return mv;
    }

    @RequestMapping(path = "adm/update", method = RequestMethod.POST)
    public String updateFromTree(long id, String name, String description, boolean primitive) {
        Proces p = procesService.findOne(id);
        p.setNaziv(name);
        p.setOpis(description);
        p.setPrimitivan(primitive);
        procesService.save(p);
        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(userdetail.getUsername());
       // user.getIdPodsistema().getProcesList().add(p);
        subsystemService.sacuvajPodsistem(user.getIdPodsistema());
       // Podsistem ps = p.getIdPodsistema();
       // subsystemService.sacuvajPodsistem(ps);
        return "redirect:/processes/adm/overview";
    }

    @RequestMapping(path = "usr/overviewusers", method = RequestMethod.GET)
    public ModelAndView getProccessOverviewForUsers() {
        ModelAndView mv = new ModelAndView("processesforusers");
        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        List<Proces> zeljeni = new ArrayList<>();
        for (Proces proces : sviProcesi) {
            if (proces.getNivo() == 1) {
                zeljeni.add(proces);
            }
        }
        List<Tipdokumenta> tipovi = tipDokumenataService.findAll();

        mv.addObject("documenttypes", tipovi);
        mv.addObject("processes", zeljeni);
        return mv;
    }

    @RequestMapping(path = "adm/add_new_sub", method = RequestMethod.POST)
    public ModelAndView addNewSubProcess(String procesname, String processign, String procesdescription, long procesparent, boolean isprimitive) {

        Proces subp = new Proces();
        subp.setNaziv(procesname);
        subp.setOznaka(processign);
        subp.setOpis(procesdescription);

        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findOne(userdetail.getUsername());

        subp.setIdPodsistema(user.getIdPodsistema());

        Proces p = procesService.findOne(procesparent);

        subp.setIdNadProcesa(p);
        subp.setNivo(p.getNivo() + 1);
        subp.setPrimitivan(isprimitive);
        procesService.save(subp);

        p.getProcesList().add(subp);
        procesService.save(p);

        ModelAndView mv = new ModelAndView("process_overview");
        List<Proces> sviProcesi;
        sviProcesi = procesService.findAll();
        mv.addObject("processes", sviProcesi);
        return mv;
    }

    @RequestMapping(path = "adm/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProcess(@PathVariable("id") long id) {
        Proces akt = procesService.findOne(id);
        ModelAndView mv = new ModelAndView();
        try {
            Podsistem p = akt.getIdPodsistema();
            procesService.delete(akt);
            p.getProcesList().remove(akt);
            subsystemService.sacuvajPodsistem(p);

            mv = new ModelAndView("admin_home");
            return mv;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            mv = new ModelAndView("error");
            mv.addObject("error", akt.getNaziv() + " cannot be deleted! This process has subprocesses or activities connected!!!");
        }
        return mv;
    }

}

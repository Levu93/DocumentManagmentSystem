/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.util.List;
import org.fon.documentmanagementsystem.domain.Tipdokumenta;
import org.fon.documentmanagementsystem.services.TipdokumentaService;
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
@RequestMapping("/documenttypes/")
public class TipdokumentaController {
    
    @Autowired
    private TipdokumentaService tipDokumentaService;
    
    @RequestMapping(path = "/overview", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("documenttype_overview");
        List<Tipdokumenta> sviTipoviDokumenata;
        sviTipoviDokumenata = tipDokumentaService.findAll();
        mv.addObject("documenttypes", sviTipoviDokumenata);
        return mv;
    }
    
    @RequestMapping(path = "/add_new", method = RequestMethod.GET)
    public ModelAndView addDocumentType() {
        ModelAndView mv = new ModelAndView("documenttype_add");
        System.out.println("U kontroleru sam");
        return mv;
    }
    
    @RequestMapping(path = "/add_new", method = RequestMethod.POST)
    public ModelAndView addNewSubsystem(String documenttypename) {
        Tipdokumenta tipdokumenta = new Tipdokumenta();
        int id1 = tipDokumentaService.vratiId() + 1;
        long x = id1;
        tipdokumenta.setIdTipaDokumenta(x);
        tipdokumenta.setNazivTipa(documenttypename);
        tipDokumentaService.sacuvajTipDokumenta(tipdokumenta);
        ModelAndView mv = new ModelAndView("documenttype_overview");
        List<Tipdokumenta> sviTipoviDokumenata;
        sviTipoviDokumenata = tipDokumentaService.findAll();
        mv.addObject("documenttypes", sviTipoviDokumenata);
        return mv;
    }
    
    @RequestMapping(path = "/details/{id}", method = RequestMethod.GET)
    public ModelAndView showDocumentType(@PathVariable("id") long id
    ) {
        Tipdokumenta tipdokumenta = tipDokumentaService.findOne(id);
        ModelAndView mv = new ModelAndView("documenttype_add");
        mv.addObject("documenttype", tipdokumenta);
        return mv;
    }
    
    @RequestMapping(path = "/add_new_documenttype/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") long id, String documenttypename) {
        Tipdokumenta tipdokumenta = tipDokumentaService.findOne(id);
        
        tipdokumenta.setNazivTipa(documenttypename);
        tipDokumentaService.sacuvajTipDokumenta(tipdokumenta);
        ModelAndView mv = new ModelAndView("documenttype_overview");
        List<Tipdokumenta> sviTipoviDokumenata;
        sviTipoviDokumenata = tipDokumentaService.findAll();
        mv.addObject("documenttypes", sviTipoviDokumenata);
        return mv;
    }
}

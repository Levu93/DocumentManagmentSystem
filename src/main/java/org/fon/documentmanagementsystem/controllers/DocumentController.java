/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.domain.Dokument;
import org.fon.documentmanagementsystem.domain.Podsistem;
import org.fon.documentmanagementsystem.domain.Proces;
import org.fon.documentmanagementsystem.domain.Tipdokumenta;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.dto.UserDto;
import org.fon.documentmanagementsystem.services.ActivityService;
import org.fon.documentmanagementsystem.services.DokumentService;
import org.fon.documentmanagementsystem.services.TipdokumentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author nevenac
 */
@Controller
@RequestMapping("/documents")
public class DocumentController {
    
    @Autowired
    DokumentService dokumentService;
    
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
    
    @RequestMapping(path = "/add_new/{id}", method = RequestMethod.POST)
    public ModelAndView addNewActivityForProcess(@PathVariable("id") long id, String documentname, String documentdescritption, long documenttype, MultipartFile file) {

        long idDokumenta = activityService.findAll().size() + 1;

        Aktivnost aktivnost = activityService.findOne(id);

        Dokument doc = new Dokument();
        doc.setIdDokumenta(idDokumenta);
        doc.setNaziv(documentname);
        doc.setNapomena(documentdescritption);
        doc.setDatumKreiranja(new Date());
        doc.setIdAktivnosti(aktivnost);
        
        Tipdokumenta docType = tipDokumentaService.findOne(documenttype);
        doc.setIdTipaDokumenta(docType);
        
        String putanja = "n/a";
        if (!daLiJePrazan(file)) {
            
        
            try {
                    byte[] bytes = file.getBytes();
                    putanja = "C:" + File.separator + "wamp" + File.separator + "www" + File.separator + "DMS"+ File.separator + "files";
                    File dir = new File(putanja);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    String nazivFajla = file.getOriginalFilename();
                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + nazivFajla);
                    try (BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile))) {
                        stream.write(bytes);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ModelAndView("error", "error", "Error uploading file! " + " => " + e.getMessage());

                }
        }
        
        doc.setFajl(putanja);
        dokumentService.save(doc);
        
        aktivnost.getDokumentList().add(doc);
        activityService.save(aktivnost);
        
        ModelAndView mv = new ModelAndView("process_overview");

        return mv;
    }
    
    //@RequestParam(value = "file") MultipartFile[] files
    
    private boolean daLiJePrazan(MultipartFile file) {
            if (file.isEmpty()) {
                System.out.println("Fajl prazan");
                return true;
            } else {
                System.out.println("naziv:" + file.getName());
            }
        return false;
    }
    
}

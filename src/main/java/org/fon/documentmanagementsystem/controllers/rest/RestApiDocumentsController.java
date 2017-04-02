/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers.rest;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.domain.Dokument;
import org.fon.documentmanagementsystem.domain.Proces;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.dto.TreeDto;
import org.fon.documentmanagementsystem.dto.UserDto;
import org.fon.documentmanagementsystem.services.ActivityService;
import org.fon.documentmanagementsystem.services.PodsistemService;
import org.fon.documentmanagementsystem.services.ProcesService;
import org.fon.documentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documents")
public class RestApiDocumentsController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ResponseEntity<List<TreeDto>> getDocuments(@PathVariable("id") long id) {
        System.out.println("U restu sam za dokumente");
        Aktivnost a = activityService.findOne(id);
        List<TreeDto> data = new ArrayList<>();
        if (a.getDokumentList().isEmpty()) {
            TreeDto nemadokumenata  = new TreeDto("0", id+"", "No documents", TreeDto.ACTIVITY_ICON, "", "");
            data.add(nemadokumenata);
        }else{
            
        }
        System.out.println("data"+data.size());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

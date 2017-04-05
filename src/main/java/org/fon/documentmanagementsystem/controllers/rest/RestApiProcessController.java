/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers.rest;

import java.util.ArrayList;
import java.util.List;
import org.fon.documentmanagementsystem.domain.Aktivnost;
import org.fon.documentmanagementsystem.domain.Proces;
import org.fon.documentmanagementsystem.domain.User;
import org.fon.documentmanagementsystem.dto.TreeDto;
import org.fon.documentmanagementsystem.dto.UserDto;
import org.fon.documentmanagementsystem.services.PodsistemService;
import org.fon.documentmanagementsystem.services.ProcesService;
import org.fon.documentmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/processes")
public class RestApiProcessController {

    @Autowired
    private PodsistemService companyService;
    @Autowired
    private ProcesService processService;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TreeDto>> getProcesses() {
        UserDto userdetail = (UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User activeUser = userService.findOne(userdetail.getUsername());
        System.out.println("U rest kontroleru sam");
        //uzima samo procese iz njegovog podsistema
        List<Proces> processes = activeUser.getIdPodsistema().getProcesList();
        
        List<TreeDto> data = new ArrayList<>();
        boolean hasChildren;
        for (Proces process : processes) {
            TreeDto p;
            String icon;
            icon = TreeDto.PROCESS_ICON;
            if (!process.getProcesList().isEmpty() || !process.getAktivnostList().isEmpty()) {
                hasChildren = true;
            } else {
                hasChildren = false;
            }
            if (process.getIdNadProcesa() == null) {
                p = new TreeDto(process.getId() + "p", "#", process.getNaziv(), icon, process.getPrimitivan(), process.getOznaka(), process.getOpis(), hasChildren);
            } else {
                p = new TreeDto(process.getId() + "p", process.getIdNadProcesa().getId() + "p", process.getNaziv(), icon, process.getPrimitivan(), process.getOznaka(), process.getOpis(), hasChildren);
            }
            data.add(p);
            if (process.getPrimitivan() && process.getAktivnostList() != null) {
                icon = TreeDto.ACTIVITY_ICON;
                for (Aktivnost activity : process.getAktivnostList()) {
                    p = new TreeDto(activity.getId() + "a", process.getId() + "p", activity.getNaziv(), icon, activity.getOznaka(), activity.getOpis());
                    data.add(p);
                }
            }
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}

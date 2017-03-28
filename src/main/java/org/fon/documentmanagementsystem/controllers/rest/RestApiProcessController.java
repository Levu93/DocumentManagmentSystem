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
        List<Proces> processes = activeUser.getIdPodsistema().getProcesList();
        List<TreeDto> data = new ArrayList<>();
        for (Proces process : processes) {
            TreeDto p;
            String icon;
            icon = TreeDto.PROCESS_ICON;
            if (process.getIdNadProcesa() == null) {
                p = new TreeDto(process.getId(), "#", process.getNaziv(), icon, process.getPrimitivan());
            } else {
                p = new TreeDto(process.getId(), process.getIdNadProcesa().getId() + "", process.getNaziv(), icon, process.getPrimitivan());
            }
            data.add(p);
            if (process.getPrimitivan() && process.getAktivnostList() != null) {
                icon = TreeDto.ACTIVITY_ICON;
                for (Aktivnost activity : process.getAktivnostList()) {
                    p = new TreeDto(activity.getId(), process.getId() + "", activity.getNaziv(), icon);
                    data.add(p);
                }
            }
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

//    @RequestMapping(path = "/process/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Process> showProcess(@PathVariable("id") long id) throws Exception {
//        Process process = processService.find(id);
//        if (process == null) throw new Exception("There is no process with id " + id);
//        return new ResponseEntity<>(process, HttpStatus.OK);
//    }
//
//    @RequestMapping(path = "/edit", method = RequestMethod.POST)
//    public ResponseEntity<String> editProcess(Authentication authentication, Long id, String name, boolean primitive) {
//        Process process = processService.find(id);
//        if (process == null) return new ResponseEntity<>("Process is null", HttpStatus.OK);
//        process.setName(name);
//        if (process.isPrimitive() != primitive && primitive) deleteProcessFromCompany(authentication, process);
//        if (process.isPrimitive() != primitive && !primitive) process.getActivityList().clear();
//        process.setPrimitive(primitive);
//        processService.save(process);
//        return new ResponseEntity<>("Process successfully edited", HttpStatus.OK);
//    }
//
//    private void deleteProcessFromCompany(Authentication authentication, Process process) {
//        UserDto user = (UserDto) authentication.getPrincipal();
//        Company company = userService.findOne(user.getUsername()).getCompany();
//        List<Process> processes = company.getProcesses();
//        deleteChildren(process, processes, true);
//        companyService.save(company);
//    }
//
//    private void deleteChildren(Process process, List<Process> processes, boolean root) {
//        List<Process> children = getChildren(process, processes);
//        for (Process child : children) {
//            deleteChildren(child, processes, false);
//        }
//        if (!root) processes.remove(process);
//    }
//
//    private List<Process> getChildren(Process p, List<Process> lista) {
//        List<Process> children = new ArrayList<>();
//        for (Process process : lista) {
//            if (p != null && p.equals(process.getParent())) children.add(process);
//        }
//        return children;
//    }
//    
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<MessageDto> handleError(Exception ex, WebRequest request) {
//        ex.printStackTrace();
//        return new ResponseEntity<>(new MessageDto(MessageDto.MESSAGE_TYPE_ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//    }
}

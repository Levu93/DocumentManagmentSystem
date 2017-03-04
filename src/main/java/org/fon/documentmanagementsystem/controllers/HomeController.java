/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import org.fon.documentmanagementsystem.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vukasin
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(Authentication authentication){
        //return new ModelAndView("admin_home");
        UserDto userDto = (UserDto) authentication.getPrincipal();
        switch (userDto.getRola().getNazivRole()) {
            case "ADMIN":
                return new ModelAndView("admin_home");
            case "USER":
                return new ModelAndView("user_home");
            case "SUPERADMIN":
                return new ModelAndView("superadmin_home");
            default:
                return new ModelAndView("login");
        }
    }
    
    @RequestMapping(path = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(){
        return new ModelAndView("error","error", "You do not have permission to access this page!");
    }
}

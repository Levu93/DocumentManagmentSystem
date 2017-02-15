/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fon.documentmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Vukasin
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }
}

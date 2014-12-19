/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author root
 */
@Controller
public class HomeController {
    
    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping(value = "/welcome")
    public String home() {
        return "home";
    }
    
    @RequestMapping(value = "/member")
    public String memberArea(ModelMap model) {
        model.addAttribute("message", request.getParameter("message"));
        return "halaman-signin-member";
    }
    
    @RequestMapping(value = "/help")
    public String help() {
        return "halaman-help-home";
    }
    
    @RequestMapping(value = "loginmember", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginMember(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "redirect:/loginmember";
    }
    
    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginPegawai(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "redirect:/login";
    }
    
}

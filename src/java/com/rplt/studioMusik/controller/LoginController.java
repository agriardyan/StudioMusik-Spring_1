/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.controller;

import com.rplt.studioMusik.member.IMemberDAO;
import com.rplt.studioMusik.member.Member;
import com.rplt.studioMusik.pegawai.IPegawaiDAO;
import com.rplt.studioMusik.pegawai.Pegawai;
import javax.servlet.http.HttpSession;
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
public class LoginController {
    
    @Autowired
    private IPegawaiDAO<Pegawai> pegawai;
    
    @Autowired
    private IMemberDAO<Member> member;
    
    @Autowired
    private HttpSession session;
    
    @RequestMapping(value = "/pegawai", method = RequestMethod.POST)
    public String validateLoginPegawai(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        
        int validateLogin = pegawai.validateLogin(username, password);
        
        switch (validateLogin) {
                    case 0:
                        //unregistered
                        model.addAttribute("message", "Unregistered username!");
                        return "index";
                    case 1:
                        //wrong password
                        model.addAttribute("message", "Wrong password!");
                        return "index";
                    case 2:
                        session.setAttribute("role", "Operator");
                        session.setAttribute("name", username);
                        session.setAttribute("username", username);
                        return "redirect:/operator/halamanutamaoperator";
                    case 3:
                        session.setAttribute("role", "Admin");
                        session.setAttribute("name", username);
                        session.setAttribute("username", username);
                        return "redirect:/owner/halamanutamaowner";
                    default:
                        break;
                }
        
        return null;
    }
    
    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String validateLoginMember(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        
        int validateLogin = member.validateLogin(username, password);
        
        switch (validateLogin) {
                    case 0:
                        //unregistered
                        model.addAttribute("message", "Unregistered username!");
                        return "index";
                    case 1:
                        //wrong password
                        model.addAttribute("message", "Wrong password!");
                        return "index";
                    case 2:
                        session.setAttribute("name", username);
                        session.setAttribute("username", username);
                        return "redirect:/member/halamanutamamember";
                    default:
                        break;
                }
        
        return null;
    }
    
    
}

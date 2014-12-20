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
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private IPegawaiDAO<Pegawai> pegawai;
    
    @Autowired
    private IMemberDAO<Member> member;
    
    @Autowired
    private HttpSession session;
    
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String validateLoginPegawai(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        
        int validateLogin = pegawai.validateLogin(username, password);
        
        switch (validateLogin) {
                    case 0:
                        //unregistered
                        model.addAttribute("messagePegawai", "Unregistered username!");
                        return "index";
                    case 1:
                        //wrong password
                        model.addAttribute("tempName", username);
                        model.addAttribute("messagePegawai", "Wrong password!");
                        return "index";
                    case 2:
                        session.setAttribute("role", "OPERATOR");
                        session.setAttribute("name", pegawai.getNamaByUser(username.toUpperCase()));
                        session.setAttribute("username", username.toUpperCase());
                        return "redirect:/operator/halamanutamaoperator";
                    case 3:
                        session.setAttribute("role", "OWNER");
                        session.setAttribute("name", pegawai.getNamaByUser(username.toUpperCase()));
                        session.setAttribute("username", username.toUpperCase());
                        return "redirect:/owner/halamanutamaowner";
                    default:
                        break;
                }
        
        return null;
    }
}

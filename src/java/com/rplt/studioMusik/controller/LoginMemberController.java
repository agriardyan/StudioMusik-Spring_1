/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.controller;

import com.rplt.studioMusik.member.IMemberDAO;
import com.rplt.studioMusik.member.Member;
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
@RequestMapping("/loginmember")
public class LoginMemberController {
    
    @Autowired
    private IMemberDAO<Member> member;
    
    @Autowired
    private HttpSession session;
    
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String validateLoginMember(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
        
        int validateLogin = member.validateLogin(username, password);
        
        switch (validateLogin) {
                    case 0:
                        //unregistered
                        model.addAttribute("message", "Unregistered username!");
                        return "redirect:home/member";
                    case 1:
                        //wrong password
                        model.addAttribute("message", "Wrong password!");
                        return "redirect:home/member";
                    case 2:
                        session.setAttribute("name", member.getNamaByUser(username).toUpperCase());
                        session.setAttribute("noTelp", member.getNoTelpByUser(username));
                        session.setAttribute("username", username.toUpperCase());
                        return "redirect:/member/halamanutamamember";
                    default:
                        break;
                }
        
        return null;
    }
    
}

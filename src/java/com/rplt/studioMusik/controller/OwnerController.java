/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.controller;

import com.rplt.studioMusik.dataPersewaan.IPersewaanStudioMusikDAO;
import com.rplt.studioMusik.dataPersewaan.PersewaanStudioMusik;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author root
 */
@Controller
public class OwnerController {
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpSession session;

    @Autowired
    private IPersewaanStudioMusikDAO<PersewaanStudioMusik> persewaanStudioMusik;
    
    
    @RequestMapping(method = RequestMethod.POST)
    public String logout() {
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        return "redirect:/home/welcome";
    }
    
    @RequestMapping(value = "/halamanutamaowner")
    public String halamanUtamaOwner() {        
        return "halaman-utama-owner";
    }
    
    @RequestMapping(value = "/lihatlaporan")
    public String lihatLaporan(ModelMap model) {        
        String bulan = request.getParameter("bulan");
        String tahun = request.getParameter("tahun");
        
        List<PersewaanStudioMusik> dataListByMonth = persewaanStudioMusik.getDataListByMonth(bulan, tahun);
        
        model.addAttribute("bulan", bulan);
        model.addAttribute("tahun", tahun);
        model.addAttribute("dataListByMonth", dataListByMonth);
        
        return "halaman-utama-owner";
    }
    
    @RequestMapping(value = "/tampilLaporan")
    public String tampilLaporan(ModelMap model) {
        
        return "halaman-utama-owner";
    }
    
}

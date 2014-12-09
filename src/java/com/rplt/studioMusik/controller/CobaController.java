/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.controller;

import com.rplt.studioMusik.pegawai.IPegawaiDAO;
import com.rplt.studioMusik.pegawai.Pegawai;
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
public class CobaController {
    
    @Autowired
    private IPegawaiDAO<Pegawai> pegawai;
    
    @RequestMapping(value = "/coba.htm", method = RequestMethod.GET)
    public String print(ModelMap model) {
        model.addAttribute("list", pegawai.getDataList());
        return "coba";
    }
    
    @RequestMapping(value = "/coba.htm", method = RequestMethod.POST)
    public String pindah() {
        return "halaman-utama-operator";
    }
    
}

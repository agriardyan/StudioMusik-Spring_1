/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.controller;

import com.rplt.studioMusik.dataPersewaan.IPersewaanStudioMusikDAO;
import com.rplt.studioMusik.dataPersewaan.PersewaanStudioMusik;
import com.rplt.studioMusik.model.DatabaseConnection;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
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
    
    @Autowired
    private ServletConfig servletConfig;
    
    
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
    public String lihatLaporan(ModelMap model, HttpServletResponse response) {        
        String tanggalAwal = request.getParameter("tanggalAwal").toUpperCase();
        String tanggalAkhir = request.getParameter("tanggalAkhir").toUpperCase();
        
//        List<PersewaanStudioMusik> dataListByMonth = persewaanStudioMusik.getDataListByMonth(tanggalAwal, tanggalAkhir);
//        
//        model.addAttribute("bulan", tanggalAwal);
//        model.addAttribute("tahun", tanggalAkhir);
//        model.addAttribute("dataListByMonth", dataListByMonth);
        
        Connection conn = DatabaseConnection.getmConnection();
//            File reportFile = new File(application.getRealPath("Coba.jasper"));//your report_name.jasper file
        File reportFile = new File(servletConfig.getServletContext()
                .getRealPath("/resources/report/laporan_pemasukan.jasper"));

        Map parameters = new HashMap();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("TANGGAL_AWAL", tanggalAwal);
        params.put("TANGGAL_AKHIR", tanggalAkhir);
        byte[] bytes = null;
        try {
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), params, conn);
        } catch (JRException ex) {
            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);

        try {
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();
        } catch (IOException ex) {
            Logger.getLogger(OperatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "halaman-cetakReport-owner";
    }
    
    @RequestMapping(value = "/tampilLaporan")
    public String tampilLaporan(ModelMap model) {
        
        return "halaman-utama-owner";
    }
    
}

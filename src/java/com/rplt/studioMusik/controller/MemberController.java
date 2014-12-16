/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rplt.studioMusik.controller;

import com.rplt.studioMusik.dataPersewaan.IPersewaanStudioMusikDAO;
import com.rplt.studioMusik.dataPersewaan.PersewaanStudioMusik;
import com.rplt.studioMusik.member.IMemberDAO;
import com.rplt.studioMusik.member.Member;
import com.rplt.studioMusik.studioMusik.IStudioMusikDAO;
import com.rplt.studioMusik.studioMusik.StudioMusik;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
public class MemberController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private IMemberDAO<Member> member;

    @Autowired
    private IPersewaanStudioMusikDAO<PersewaanStudioMusik> persewaanStudioMusik;

    @Autowired
    private IStudioMusikDAO<StudioMusik> studioMusik;
    
    @Autowired
    private ServletConfig servletConfig;

    @RequestMapping(method = RequestMethod.POST)
    public String logout() {
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        return "redirect:/home/welcome";
    }

    @RequestMapping(value = "/halamanutamamember", method = {RequestMethod.GET, RequestMethod.POST})
    public String halamanUtamaMember(ModelMap model) {
        model.addAttribute("disable", "disabled");
        return "halaman-utama-member";
    }

    @RequestMapping(value = "/cekJadwal", method = RequestMethod.GET)
    public String cekJadwal(ModelMap model) {

        String tanggalSewa = request.getParameter("tanggalSewa");
        String jamSewa = request.getParameter("jamSewa");
        String durasiSewa = request.getParameter("durasiSewa");
        String studio = request.getParameter("studio");

        PersewaanStudioMusik pw = new PersewaanStudioMusik();
        pw.setmMulaiSewa(tanggalSewa);
        pw.setmSelesaiSewa(jamSewa);
        pw.setmDurasi(Integer.parseInt(durasiSewa));
        pw.setmKodeStudio(studio);

        boolean cek = persewaanStudioMusik.cekKetersediaanJadwal(pw);
        if (cek) {
            int biayaUnfmt = persewaanStudioMusik.hitungBiayaSewa(Integer.parseInt(durasiSewa), studio);
            DecimalFormat df = new DecimalFormat("###,###.00");
            String biaya = df.format(biayaUnfmt);
            biaya = biaya.replace(".", "&");
            biaya = biaya.replace(",", ".");
            biaya = biaya.replace("&", ",");
            model.replace("disable", "disabled", "");
            model.addAttribute("tanggalSewa", tanggalSewa);
            model.addAttribute("jamSewa", jamSewa);
            model.addAttribute("durasiSewa", durasiSewa);
            model.addAttribute("studio", studio);
            model.addAttribute("biaya", "Biaya sewa sebesar : Rp " + biaya);
            model.addAttribute("ketersediaan", "Studio Tersedia!");
            model.addAttribute("biayaunfmt", biayaUnfmt);
            model.addAttribute("disable", "");
        } else {
            model.addAttribute("ketersediaan", "Studio tidak tersedia pada waktu tersebut!");
            model.addAttribute("disable", "disabled");
        }

        return "halaman-utama-member";
    }

    @RequestMapping(value = "/summarysewa")
    public String summarySewa(ModelMap model) {
        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();
        String jamSewa = request.getParameter("jamSewa");
        String durasiSewa = request.getParameter("durasiSewa");
        String studio = request.getParameter("studio");
        String namaPenyewa = request.getParameter("namaPenyewa").toUpperCase();
        String noTelp = request.getParameter("noTelp");
        String biaya = request.getParameter("biaya");
        String biayaunfmt = request.getParameter("biayaunfmt");

        String[] splitTglSewa = tanggalSewa.split("[-]");
        String[] splitJamSewa = jamSewa.split("[:]");

//        Calendar calendar = new GregorianCalendar(Integer.parseInt(splitTglSewa[2]), Integer.parseInt(splitTglSewa[1]), Integer.parseInt(splitTglSewa[0]), Integer.parseInt(splitJamSewa[0]) + Integer.parseInt(durasiSewa), Integer.parseInt(splitJamSewa[1]));
        
        Calendar calendar = new GregorianCalendar(2000, 1, Integer.parseInt(splitTglSewa[0]), Integer.parseInt(splitJamSewa[0]) + Integer.parseInt(durasiSewa), Integer.parseInt(splitJamSewa[1]));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String jamSelesai = sdf.format(calendar.getTime());

        String namaStudio = studioMusik.getNamaStudio(studio);

        String saldo = member.getSaldo(session.getAttribute("username").toString().toUpperCase());
        String saldoUnformatted = saldo;
        
        saldoUnformatted = saldoUnformatted.replace(".", "");
        saldoUnformatted = saldoUnformatted.replace(",", "");
        
        saldo = saldo.replace(".", "&");
        saldo = saldo.replace(",", ".");
        saldo = saldo.replace("&", ",");

        model.addAttribute("tanggalSewa", tanggalSewa);
        model.addAttribute("jamSewa", jamSewa);
        model.addAttribute("durasiSewa", durasiSewa);
        model.addAttribute("jamSelesai", jamSelesai);
        model.addAttribute("studio", studio);
        model.addAttribute("namaStudio", namaStudio);
        model.addAttribute("namaPenyewa", namaPenyewa);
        model.addAttribute("noTelp", noTelp);
        model.addAttribute("biaya", biaya);
        model.addAttribute("biayaunfmt", biayaunfmt);
        model.addAttribute("saldo", saldo);

        return "halaman-summary-member";
    }

    @RequestMapping(value = "/simpan", method = RequestMethod.POST)
    public String simpanData(HttpServletResponse response) {
        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();
        String jamSewa = request.getParameter("jamSewa");
        String durasiSewa = request.getParameter("durasiSewa");
        String jamSelesai = request.getParameter("jamSelesai");
        String studio = request.getParameter("studio");
        String namaPenyewa = request.getParameter("namaPenyewa").toUpperCase();
        String noTelp = request.getParameter("noTelp");
        String biaya = request.getParameter("biaya");
        String biayaunfmt = request.getParameter("biayaunfmt");

        PersewaanStudioMusik pw = new PersewaanStudioMusik();
        pw.setmMulaiSewa(tanggalSewa+ " " + jamSewa);
        pw.setmSelesaiSewa(tanggalSewa+ " " + jamSelesai);
        pw.setmDurasi(Integer.parseInt(durasiSewa));
        pw.setmKodeStudio(studio);
        pw.setmNamaPenyewa(namaPenyewa);
        pw.setmNomorTeleponPenyewa(noTelp);
        pw.setmBiayaPelunasan(Integer.parseInt(biayaunfmt));

        persewaanStudioMusik.simpanData(pw);
        
        String jdbcURL = null;
        String username = null;
        String password = null;

        Connection conn = null;
        try {
            jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
            username = "mhs125314109";
            password = "mhs125314109";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//            File reportFile = new File(application.getRealPath("Coba.jasper"));//your report_name.jasper file
        File reportFile = new File(servletConfig.getServletContext()
                .getRealPath("/resources/report/nota_persewaan.jasper"));

        Map parameters = new HashMap();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("P_KODESEWA", pw.getmKodeSewa());
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

        return "halaman-cetakNota-member";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String registrasiMember() {
        return "under-construction";
    }

    @RequestMapping(value = "/topup", method = RequestMethod.GET)
    public String topUpSaldoMember() {
        return "under-construction";
    }

}

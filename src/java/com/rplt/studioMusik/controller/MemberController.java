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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @RequestMapping(method = RequestMethod.POST)
    public String logout() {
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        return "redirect:/index.htm";
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
    public String simpanData() {
        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();
        String jamSewa = request.getParameter("jamSewa");
        String durasiSewa = request.getParameter("durasiSewa");
        String studio = request.getParameter("studio");
        String namaPenyewa = request.getParameter("namaPenyewa").toUpperCase();
        String noTelp = request.getParameter("noTelp");
        String biaya = request.getParameter("biaya");
        String biayaunfmt = request.getParameter("biayaunfmt");

        PersewaanStudioMusik pw = new PersewaanStudioMusik();
        pw.setmMulaiSewa(tanggalSewa);
        pw.setmSelesaiSewa(jamSewa);
        pw.setmDurasi(Integer.parseInt(durasiSewa));
        pw.setmKodeStudio(studio);
        pw.setmNamaPenyewa(namaPenyewa);
        pw.setmNomorTeleponPenyewa(noTelp);
        pw.setmBiayaPelunasan(Integer.parseInt(biayaunfmt));

        persewaanStudioMusik.simpanData(pw);

        return "halaman-cetakNota-operator";
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

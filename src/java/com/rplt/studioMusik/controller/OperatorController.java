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
import com.rplt.studioMusik.model.DatabaseConnection;
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
 * @author Agustinus Agri
 */
@Controller
public class OperatorController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private IPersewaanStudioMusikDAO<PersewaanStudioMusik> persewaanStudioMusik;

    @Autowired
    private IStudioMusikDAO<StudioMusik> studioMusik;

    @Autowired
    private IMemberDAO<Member> member;

    @Autowired
    private ServletConfig servletConfig;

    @RequestMapping(method = RequestMethod.POST)
    public String logout() {
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        return "redirect:/home/welcome";
    }

    @RequestMapping(value = "/halamanutamaoperator", method = {RequestMethod.GET, RequestMethod.POST})
    public String halamanUtamaOperator(ModelMap model) {
        model.addAttribute("disable", "disabled");
        return "halaman-utama-operator-alternate";
    }

    @RequestMapping(value = "/halamanLihatJadwal", method = {RequestMethod.GET})
    public String halamanLihatJadwal(ModelMap model) {
        return "halaman-lihatJadwal-operator";
    }

    @RequestMapping(value = "/lihatJadwal", method = {RequestMethod.GET})
    public String lihatJadwal(ModelMap model) {
        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();

        List<PersewaanStudioMusik> dataListByDate = persewaanStudioMusik.getDataListByMonth(tanggalSewa);

        model.addAttribute("tanggalSewa", tanggalSewa);
        model.addAttribute("dataListByDate", dataListByDate);

        return "halaman-lihatJadwal-operator";
    }

    @RequestMapping(value = "/cekJadwal", method = RequestMethod.GET)
    public String cekJadwal(ModelMap model) {

        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();
        String jamSewa = request.getParameter("jamSewa");
        String durasiSewa = request.getParameter("durasiSewa");
        String studio = request.getParameter("studio");

        PersewaanStudioMusik pw = new PersewaanStudioMusik();
        pw.setmJamMulaiSewa(jamSewa);
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
            model.addAttribute("biaya", biaya);
            model.addAttribute("ketersediaan", 1);
            model.addAttribute("biayaunfmt", biayaUnfmt);
            model.addAttribute("message", "Studio Tersedia!");
            model.addAttribute("disable", "");
        } else {
            model.addAttribute("ketersediaan", 0);
            model.addAttribute("message", "Studio tidak tersedia pada waktu tersebut!");
            model.addAttribute("disable", "disabled");
        }

        return "halaman-utama-operator-alternate";
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

        return "halaman-summary-operator";
    }

    @RequestMapping(value = "/revisi", method = {RequestMethod.GET, RequestMethod.POST})
    public String revisi(ModelMap model) {
//        model.addAttribute("disable", "disabled");
        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();
        String jamSewa = request.getParameter("jamSewa");
        String durasiSewa = request.getParameter("durasiSewa");
        String studio = request.getParameter("studio");
        String namaPenyewa = request.getParameter("namaPenyewa").toUpperCase();
        String noTelp = request.getParameter("noTelp");
        String biaya = request.getParameter("biaya");
        String biayaunfmt = request.getParameter("biayaunfmt");

        model.addAttribute("tanggalSewa", tanggalSewa);
        model.addAttribute("jamSewa", jamSewa);
        model.addAttribute("durasiSewa", durasiSewa);
        model.addAttribute("studio", studio);
        model.addAttribute("biaya", biaya);
        model.addAttribute("ketersediaan", 1);
        model.addAttribute("biayaunfmt", biayaunfmt);
        model.addAttribute("disable", "");
        model.addAttribute("namaPenyewa", namaPenyewa);
        model.addAttribute("noTelp", noTelp);
        model.addAttribute("message", "Studio Tersedia!");

        return "halaman-utama-operator-alternate";
    }

    @RequestMapping(value = "/simpan", method = RequestMethod.POST)
    public String simpanData(ModelMap model, HttpServletResponse response) {
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
        pw.setmMulaiSewa(tanggalSewa + " " + jamSewa);
        pw.setmSelesaiSewa(tanggalSewa + " " + jamSelesai);
        pw.setmDurasi(Integer.parseInt(durasiSewa));
        pw.setmKodeStudio(studio);
        pw.setmNamaPenyewa(namaPenyewa);
        pw.setmNomorTeleponPenyewa(noTelp);
        pw.setmBiayaPelunasan(Integer.parseInt(biayaunfmt));

        persewaanStudioMusik.simpanData(pw);

        model.addAttribute("kodeSewa", pw.getmKodeSewa());

        String jdbcURL = null;
        String username = null;
        String password = null;

        return "halaman-cetakNota-operator";
    }

    @RequestMapping(value = "/cetakNota", method = RequestMethod.GET)
    public String cetakNota(HttpServletResponse response) {
        Connection conn = DatabaseConnection.getmConnection();
//            File reportFile = new File(application.getRealPath("Coba.jasper"));//your report_name.jasper file
        File reportFile = new File(servletConfig.getServletContext()
                .getRealPath("/resources/report/nota_persewaan.jasper"));

        Map parameters = new HashMap();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("P_KODESEWA", request.getParameter("kodeSewa"));
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
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "halaman-cetakNota-operator";
    }

    @RequestMapping(value = "/registrasi", method = RequestMethod.GET)
    public String registrasiMember() {
        return "halaman-registrasiMember-operator";
    }

    @RequestMapping(value = "/simpanMember", method = RequestMethod.POST)
    public String simpanMember() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nama = request.getParameter("nama");
        String alamat = request.getParameter("alamat");
        String tglLahir = request.getParameter("tanggalLahir");
        String noTelp = request.getParameter("telepon");
        String email = request.getParameter("email");
        int saldo = Integer.parseInt(request.getParameter("saldo"));
        String tempatLahir = request.getParameter("tempatLahir");

        Member m = new Member();
        m.setmUsernameMember(username);
        m.setmPaswordMember(password);
        m.setmNamaMember(nama);
        m.setmAlamatMember(alamat);
        m.setmTempatTanggalLahir(tglLahir);
        m.setmNomorTelepon(noTelp);
        m.setmEmailMember(email);
        m.setmSaldoMember(saldo);
        m.setmTempatLahirMember(tempatLahir);

        member.simpanData(m);

        return "halaman-konfirmasiRegistrasi-operator";
    }

    @RequestMapping(value = "/cariUser", method = RequestMethod.POST)
    public String cariUser(ModelMap model) {
        String username = request.getParameter("user").toUpperCase();
        List<Member> memberList = null;
        try {
            memberList = member.getDataListbyUser(username);
            model.addAttribute("user", memberList.get(0).getmUsernameMember());
            model.addAttribute("id", memberList.get(0).getmKodeMember());
            model.addAttribute("nama", memberList.get(0).getmNamaMember());
            model.addAttribute("saldo", memberList.get(0).getmSaldoMember());

            return "halaman-topUpSaldoMember-operator";
        } catch (Exception e) {
            model.addAttribute("message", "Data tidak ditemukan");

            return "halaman-topUpSaldoMember-operator";
        }

    }

    @RequestMapping(value = "/topup", method = RequestMethod.GET)
    public String topUpSaldoMember(ModelMap model) {

        return "halaman-topUpSaldoMember-operator";
    }

    @RequestMapping(value = "/updateSaldo", method = RequestMethod.POST)
    public String updateSaldo(ModelMap model) {
        String user = request.getParameter("userT");
        int pValue = Integer.parseInt(request.getParameter("saldoT")) + Integer.parseInt(request.getParameter("saldo"));
        member.updateTambahSaldo(user, pValue);
        List<Member> memberList = member.getDataListbyUser(user);
        model.addAttribute("user", memberList.get(0).getmUsernameMember());
        model.addAttribute("id", memberList.get(0).getmKodeMember());
        model.addAttribute("nama", memberList.get(0).getmNamaMember());
        model.addAttribute("saldo", memberList.get(0).getmSaldoMember());

        return "halaman-topUpSaldoMember-operator";
    }

//    @RequestMapping(value = "/pelunasan", method = RequestMethod.GET)
//    public String pelunasan() {
//        return "halaman-pelunasan-operator";
//    }
}

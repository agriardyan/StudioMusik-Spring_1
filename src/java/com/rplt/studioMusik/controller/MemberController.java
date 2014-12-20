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
        session.removeAttribute("noTelp");
        session.invalidate();
        return "redirect:/home/welcome";
    }

    @RequestMapping(value = "/halamanutamamember", method = {RequestMethod.GET, RequestMethod.POST})
    public String halamanUtamaMember(ModelMap model) {
        model.addAttribute("disable", "disabled");
        return "halaman-utama-member";
    }

    @RequestMapping(value = "/halamanLihatJadwal", method = {RequestMethod.GET})
    public String halamanLihatJadwal(ModelMap model) {
        return "halaman-lihatJadwal-member";
    }

    @RequestMapping(value = "/lihatJadwal", method = {RequestMethod.GET})
    public String lihatJadwal(ModelMap model) {
        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();

        List<PersewaanStudioMusik> dataListByDate = persewaanStudioMusik.getDataListByMonth(tanggalSewa);

        model.addAttribute("tanggalSewa", tanggalSewa);
        model.addAttribute("dataListByDate", dataListByDate);

        return "halaman-lihatJadwal-member";
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

            String saldo = member.getSaldo(session.getAttribute("username").toString());

            String saldoFmt = df.format(Integer.parseInt(saldo));

            saldoFmt = saldoFmt.replace(".", "&");
            saldoFmt = saldoFmt.replace(",", ".");
            saldoFmt = saldoFmt.replace("&", ",");

            int sisaSaldo = member.simulateKurangSaldo(session.getAttribute("username").toString(), biayaUnfmt);

            if (sisaSaldo < 0) {
                model.addAttribute("ketersediaan", -1);
                model.addAttribute("biaya", biaya);
                model.addAttribute("message", "Saldo anda tidak mencukupi! Silakan top up saldo.");
                model.addAttribute("saldoNow", saldoFmt);
                model.addAttribute("disable", "disabled");
                return "halaman-utama-member";
            }

            model.replace("disable", "disabled", "");
            model.addAttribute("tanggalSewa", tanggalSewa);
            model.addAttribute("jamSewa", jamSewa);
            model.addAttribute("durasiSewa", durasiSewa);
            model.addAttribute("studio", studio);
            model.addAttribute("biaya", biaya);
            model.addAttribute("ketersediaan", 1);
            model.addAttribute("message", "Studio Tersedia!");
            model.addAttribute("biayaunfmt", biayaUnfmt);
            model.addAttribute("disable", "");
            model.addAttribute("saldoNow", saldoFmt);
        } else {
            model.addAttribute("ketersediaan", 0);
            model.addAttribute("message", "Studio tidak tersedia pada waktu tersebut!");
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

        int sisaSaldo = member.simulateKurangSaldo(session.getAttribute("username").toString(), Integer.parseInt(biayaunfmt));
        DecimalFormat df = new DecimalFormat("###,###.00");

        String remainSaldo = df.format(sisaSaldo);

        remainSaldo = remainSaldo.replace(".", "&");
        remainSaldo = remainSaldo.replace(",", ".");
        remainSaldo = remainSaldo.replace("&", ",");

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
        model.addAttribute("sisaSaldo", sisaSaldo);
        model.addAttribute("remainSaldo", remainSaldo);

        return "halaman-summary-member";
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
        model.addAttribute("disable", "disabled");
        model.addAttribute("namaPenyewa", namaPenyewa);
        model.addAttribute("noTelp", noTelp);
        model.addAttribute("message", "Studio Tersedia!");

        return "halaman-utama-member";
    }

    @RequestMapping(value = "/simpan", method = RequestMethod.POST)
    public String simpanData(ModelMap model) {
        String tanggalSewa = request.getParameter("tanggalSewa").toUpperCase();
        String jamSewa = request.getParameter("jamSewa");
        String durasiSewa = request.getParameter("durasiSewa");
        String jamSelesai = request.getParameter("jamSelesai");
        String studio = request.getParameter("studio");
        String namaPenyewa = request.getParameter("namaPenyewa").toUpperCase();
        String noTelp = request.getParameter("noTelp");
        String biaya = request.getParameter("biaya");
        String biayaunfmt = request.getParameter("biayaunfmt");
        String saldo = request.getParameter("sisaSaldo");

        System.err.println("JAM SEWA : " + jamSewa);
        System.err.println("JAM SELESAI : " + jamSelesai);

        PersewaanStudioMusik pw = new PersewaanStudioMusik();
        pw.setmMulaiSewa(tanggalSewa + " " + jamSewa);
        pw.setmSelesaiSewa(tanggalSewa + " " + jamSelesai);
        System.err.println("MULAI SEWA : " + pw.getmMulaiSewa());
        pw.setmDurasi(Integer.parseInt(durasiSewa));
        pw.setmKodeStudio(studio);
        pw.setmNamaPenyewa(namaPenyewa);
        pw.setmNomorTeleponPenyewa(noTelp);
        pw.setmBiayaPelunasan(Integer.parseInt(biayaunfmt));

        persewaanStudioMusik.simpanData(pw);
        model.addAttribute("kodeSewa", pw.getmKodeSewa());

        member.updateKurangSaldo(session.getAttribute("username").toString(), Integer.parseInt(saldo));

        return "halaman-cetakNota-member";
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

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateMember(ModelMap model) {
        System.out.println("ENTERING UPDATE");
        List<Member> dataListbyUser = member.getDataListbyUser(session.getAttribute("username").toString());
        String ttl = dataListbyUser.get(0).getmTempatTanggalLahir();
        String tempatLahir = dataListbyUser.get(0).getmTempatLahirMember();
        String alamat = dataListbyUser.get(0).getmAlamatMember();
        String telepon = dataListbyUser.get(0).getmNomorTelepon();
        String email = dataListbyUser.get(0).getmEmailMember();

        model.addAttribute("ttl", ttl);
        model.addAttribute("tempatLahir", tempatLahir);
        model.addAttribute("alamat", alamat);
        model.addAttribute("noTelp", telepon);
        model.addAttribute("email", email);
        return "halaman-updateMember-member";
    }

    @RequestMapping(value = "/validateUpdate", method = RequestMethod.POST)
    public String validateUpdateMember(ModelMap model) {
        List<Member> dataListbyUser = member.getDataListbyUser(session.getAttribute("username").toString());
        String nama = session.getAttribute("name").toString();
        String username = session.getAttribute("username").toString();
        String ttl = request.getParameter("tanggalLahir").toUpperCase();
        String tempatLahir = request.getParameter("tempatLahir");;
        String alamat = request.getParameter("alamat");
        String telepon = request.getParameter("telepon");
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        System.out.println("ENTERING VALIDATE UPDATE");

        if (!dataListbyUser.get(0).getmPaswordMember().equals(oldPassword)) {
            System.out.println("ENTERING PASSWORD NOT MATCH");
            model.addAttribute("ttl", ttl);
            model.addAttribute("tempatLahir", tempatLahir);
            model.addAttribute("alamat", alamat);
            model.addAttribute("noTelp", telepon);
            model.addAttribute("email", email);
            model.addAttribute("error", "Password lama anda salah!");
            return "halaman-updateMember-member";
        }

        String newPassword = request.getParameter("password");

        Member m = new Member();
        m.setmTempatTanggalLahir(ttl);
        m.setmTempatLahirMember(tempatLahir);
        m.setmAlamatMember(alamat);
        m.setmNomorTelepon(telepon);
        m.setmEmailMember(email);
        m.setmPaswordMember(newPassword);
        m.setmUsernameMember(username);

        member.updateDataMember(m);

//        model.addAttribute("ttl", ttl);
//        model.addAttribute("tempatLahir", tempatLahir);
//        model.addAttribute("alamat", alamat);
//        model.addAttribute("noTelp", telepon);
//        model.addAttribute("email", email);
        return "halaman-suksesUpdate-member";
    }

    @RequestMapping(value = "/lihatData", method = RequestMethod.GET)
    public String lihatData(ModelMap model) {
        List<Member> dataListbyUser = member.getDataListbyUser(session.getAttribute("username").toString());
        String ttl = dataListbyUser.get(0).getmTempatTanggalLahir();
        String tempatLahir = dataListbyUser.get(0).getmTempatLahirMember();
        String alamat = dataListbyUser.get(0).getmAlamatMember();
        String telepon = dataListbyUser.get(0).getmNomorTelepon();
        String email = dataListbyUser.get(0).getmEmailMember();

        DecimalFormat df = new DecimalFormat("###,###.00");

        String saldo = member.getSaldo(session.getAttribute("username").toString());

        String saldoFmt = df.format(Integer.parseInt(saldo));

        saldoFmt = saldoFmt.replace(".", "&");
        saldoFmt = saldoFmt.replace(",", ".");
        saldoFmt = saldoFmt.replace("&", ",");

        model.addAttribute("ttl", ttl);
        model.addAttribute("tempatLahir", tempatLahir);
        model.addAttribute("alamat", alamat);
        model.addAttribute("noTelp", telepon);
        model.addAttribute("email", email);
        model.addAttribute("saldoNow", saldoFmt);
        return "halaman-lihatData-member";
    }

    @RequestMapping(value = "/topup", method = RequestMethod.GET)
    public String topUpSaldoMember() {
        return "under-construction";
    }

}

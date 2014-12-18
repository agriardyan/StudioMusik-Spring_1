/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rplt.studioMusik.dataPersewaan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Agustinus Agri
 */
@Repository
public class PersewaanStudioMusikDAO implements IPersewaanStudioMusikDAO<PersewaanStudioMusik> {

    @Autowired
    private DataSource dataSource;

    @Override
    public void simpanData(PersewaanStudioMusik pPersewaanStudioMusik) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String kode = getGeneratedKodeSewa();

        System.out.println("kode : " + kode);

        String sql = "insert into PERSEWAAN_STUDIO_MUSIK values(?, ?, ?, ?, to_date(?, 'dd-MON-yyyy HH24:MI'), to_date(?, 'dd-MON-yyyy HH24:MI'), ?)";

        System.out.println(sql);

        jdbcTemplate.update(sql,
                new Object[]{
                    kode,
                    pPersewaanStudioMusik.getmKodeStudio(),
                    pPersewaanStudioMusik.getmNamaPenyewa(),
                    pPersewaanStudioMusik.getmNomorTeleponPenyewa(),
                    pPersewaanStudioMusik.getmMulaiSewa(),
                    pPersewaanStudioMusik.getmSelesaiSewa(),
                    pPersewaanStudioMusik.getmBiayaPelunasan()
                });

        System.out.println(pPersewaanStudioMusik.getmMulaiSewa() + "sd" + pPersewaanStudioMusik.getmSelesaiSewa());

        pPersewaanStudioMusik.setmKodeSewa(kode);

        System.out.println(pPersewaanStudioMusik.getmKodeSewa());

    }

    @Override
    public boolean cekKetersediaanJadwal(PersewaanStudioMusik pPersewaanStudioMusik) {
        List<PersewaanStudioMusik> pegawaiList = new ArrayList<PersewaanStudioMusik>();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT to_char((to_date(?, 'HH24:MI') + ? / 24), 'HH24:MI') FROM dual";

        String jamMulai = pPersewaanStudioMusik.getmJamMulaiSewa();

        String jamSelesai = jdbcTemplate.queryForObject(sql, new Object[]{jamMulai, pPersewaanStudioMusik.getmDurasi()}, String.class);

        System.out.println(jamSelesai);

        sql = "select * from PERSEWAAN_STUDIO_MUSIK where KODE_STUDIO = ? "
                + "and to_char(MULAI_SEWA, 'dd-MON-yyyy') = ? "
                + "and "
                + "(to_char(MULAI_SEWA, 'hh24:mi') BETWEEN ? AND ? "
                + "and to_char(SELESAI_SEWA, 'hh24:mi') > ? "
                + "and to_char(MULAI_SEWA, 'hh24:mi') < ? )";

        sql = "SELECT * FROM PERSEWAAN_STUDIO_MUSIK WHERE KODE_STUDIO = ? "
                + "AND to_char(MULAI_SEWA, 'dd-MON-yyyy') = ? "
                + "AND to_char(mulai_sewa,'HH24:MI') BETWEEN ? AND ? "
                + "AND "
                + "(to_char(SELESAI_SEWA,'HH24:MI') > ? AND "
                + "to_char(MULAI_SEWA,'HH24:MI') < ?)";

        System.out.println(pPersewaanStudioMusik.getmMulaiSewa() + " " + jamMulai + "sampai" + jamSelesai);

        pegawaiList = jdbcTemplate.query(sql,
                new Object[]{
                    pPersewaanStudioMusik.getmKodeStudio(),
                    pPersewaanStudioMusik.getmMulaiSewa().toUpperCase(),
                    pPersewaanStudioMusik.getmJamMulaiSewa(),
                    jamSelesai,
                    pPersewaanStudioMusik.getmJamMulaiSewa(),
                    jamSelesai
                },
                new PersewaanStudioMusikRowMapper());

        System.out.println("list size : " + pegawaiList.size());

        if (pegawaiList.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int hitungBiayaSewa(int pDurasi, String pKodeStudio) {
        String sql = "SELECT (? * tarif_per_jam) FROM studio_musik WHERE kode_studio = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, new Object[]{pDurasi, pKodeStudio}, Integer.class);
    }

    @Override
    public String getGeneratedKodeSewa() {
        String sql = "SELECT to_char(max(kode_sewa) + 1, 'FM099999') FROM persewaan_studio_musik";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = jdbcTemplate.queryForObject(sql, String.class);
        if (query == null) {
            return "000001";
        } else {
            return query;
        }
    }

    @Override
    public List<PersewaanStudioMusik> getDataListByMonth(String pDate) {

        String sql = "select KODE_SEWA, KODE_STUDIO, NAMA_PENYEWA, NOMOR_TELEPON, to_char(MULAI_SEWA, 'dd-MON-yyyy HH24:MI'), to_char(SELESAI_SEWA, 'dd-MON-yyyy HH24:MI'), BIAYA_PELUNASAN "
                + "from PERSEWAAN_STUDIO_MUSIK "
                + "WHERE to_char(mulai_sewa, 'dd-MON-yyyy') = ? ORDER BY mulai_sewa ASC";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(sql, new Object[]{pDate}, new PersewaanStudioMusikRowMapper());
    }

    public static class PersewaanStudioMusikRowMapper implements RowMapper<PersewaanStudioMusik> {

        @Override
        public PersewaanStudioMusik mapRow(ResultSet rs, int i) throws SQLException {
            PersewaanStudioMusikExtractor persewaanStudioMusikExtractor = new PersewaanStudioMusikExtractor();
            return persewaanStudioMusikExtractor.extractData(rs);
        }
    }

    public static class PersewaanStudioMusikExtractor implements ResultSetExtractor<PersewaanStudioMusik> {

        @Override
        public PersewaanStudioMusik extractData(ResultSet rs) throws SQLException, DataAccessException {
            PersewaanStudioMusik persewaanStudioMusik = new PersewaanStudioMusik();

            persewaanStudioMusik.setmKodeSewa(rs.getString(1));
            persewaanStudioMusik.setmKodeStudio(rs.getString(2));
            persewaanStudioMusik.setmNamaPenyewa(rs.getString(3));
            persewaanStudioMusik.setmNomorTeleponPenyewa(rs.getString(4));
            persewaanStudioMusik.setmMulaiSewa(rs.getString(5));
            persewaanStudioMusik.setmSelesaiSewa(rs.getString(6));
            persewaanStudioMusik.setmBiayaPelunasan(Integer.parseInt(rs.getString(7)));

            return persewaanStudioMusik;
        }

    }

}

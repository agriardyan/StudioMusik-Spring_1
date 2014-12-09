/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rplt.studioMusik.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 *
 * @author root
 */
public class DataPersewaanStudioMusik {

    private String mKodeSewa;
    private String mKodeStudio;
    private String mNamaPenyewa;
    private String mNomorTeleponPenyewa;
    private String mTanggalSewa;
    private String mJamSewa;
    private String mJamSelesai;
    private int mDurasi;
    private int mBiayaPelunasan;
    private String mStatusPelunasan;

    public static class STATUS_PELUNASAN {

        public static final String UANG_MUKA = "UANG MUKA";
        public static final String LUNAS = "LUNAS";
    }

    public DataPersewaanStudioMusik() {
    }

    public String getmKodeSewa() {
        return mKodeSewa;
    }

    public void setmKodeSewa(String mKodeSewa) {
        this.mKodeSewa = mKodeSewa;
    }

    public String getmKodeStudio() {
        return mKodeStudio;
    }

    public void setmKodeStudio(String mKodeStudio) {
        this.mKodeStudio = mKodeStudio;
    }

    public String getmNamaPenyewa() {
        return mNamaPenyewa.toUpperCase();
    }

    public void setmNamaPenyewa(String mNamaPenyewa) {
        this.mNamaPenyewa = mNamaPenyewa;
    }

    public String getmNomorTeleponPenyewa() {
        return mNomorTeleponPenyewa;
    }

    public void setmNomorTeleponPenyewa(String mNomorTeleponPenyewa) {
        this.mNomorTeleponPenyewa = mNomorTeleponPenyewa;
    }

    public String getmTanggalSewa() {
        return mTanggalSewa.toUpperCase();
    }

    public void setmTanggalSewa(String mTanggalSewa) {
        this.mTanggalSewa = mTanggalSewa;
    }

    public String getmJamSewa() {
        return mJamSewa;
    }

    public void setmJamSewa(String mJamSewa) {
        this.mJamSewa = mJamSewa;
    }

    public int getmDurasi() {
        return mDurasi;
    }

    public void setmDurasi(int mDurasi) {
        this.mDurasi = mDurasi;
    }

    public int getmBiayaPelunasan() {
        return mBiayaPelunasan;
    }

    public void setmBiayaPelunasan(int mBiayaPelunasan) {
        this.mBiayaPelunasan = mBiayaPelunasan;
    }

    public String getmStatusPelunasan() {
        return mStatusPelunasan.toUpperCase();
    }

    public void setmStatusPelunasan(String mStatusPelunasan) {
        this.mStatusPelunasan = mStatusPelunasan;
    }

    public void setmJamSelesai(String mJamSelesai) {
        this.mJamSelesai = mJamSelesai;
    }

    public String getmJamSelesai() {
        return mJamSelesai;
    }

    public static String getGeneratedKodeSewa() {

        DataSource dataSource = DatabaseConnection.getmDataSource();

        String sql = "SELECT to_char(max(kode_sewa) + 1, 'FM099999') FROM data_persewaan_studio_musik";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = jdbcTemplate.queryForObject(sql, String.class);
        if (query == null) {
            return "000001";
        } else {
            return query;
        }
    }
    
    public static String hitungBiayaSewa(int pDurasi, String pKodeStudio)
    {
        DataSource dataSource = DatabaseConnection.getmDataSource();

        String sql = "SELECT ? / 60 * tarif_per_jam FROM studio_musik WHERE kode_studio = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = jdbcTemplate.queryForObject(sql, new Object[]{ pDurasi, pKodeStudio }, String.class);
        return query;
    }

    private static void hitungJamSelesai(DataPersewaanStudioMusik pDataPersewaanStudioMusik) {
        DataSource dataSource = DatabaseConnection.getmDataSource();
        List<DataPersewaanStudioMusik> pegawaiList = new ArrayList<DataPersewaanStudioMusik>();

        String sql = "UPDATE data_persewaan_studio_musik SET "
                + " jam_selesai = "
                + "to_date((SELECT to_char((JAM_SEWA + ? / 1440), 'HH24:MI') FROM data_persewaan_studio_musik WHERE kode_sewa = ?), 'HH24:MI') "
                + "WHERE kode_sewa = ?";

        System.out.println(sql);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, new Object[]{
            pDataPersewaanStudioMusik.getmDurasi(),
            pDataPersewaanStudioMusik.getmKodeSewa(),
            pDataPersewaanStudioMusik.getmKodeSewa()
        });
    }

    public static void simpanData(DataPersewaanStudioMusik pDataPersewaanStudioMusik) {
        DataSource dataSource = DatabaseConnection.getmDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String kode = getGeneratedKodeSewa();

        String sql = "INSERT INTO data_persewaan_studio_musik (KODE_SEWA, KODE_STUDIO, NAMA_PENYEWA, NOMOR_TELEPON, TANGGAL_SEWA, JAM_SEWA, JAM_SELESAI, BIAYA_PELUNASAN, STATUS_PELUNASAN) "
                + "VALUES (?, ?, ?, ?, ?, TO_DATE(?, 'HH24:MI'), TO_DATE('00:00', 'HH24:MI'), ?, ?)";

        System.out.println(sql);

        jdbcTemplate.update(sql,
                new Object[]{
                    kode,
                    pDataPersewaanStudioMusik.getmKodeStudio(),
                    pDataPersewaanStudioMusik.getmNamaPenyewa(),
                    pDataPersewaanStudioMusik.getmNomorTeleponPenyewa(),
                    pDataPersewaanStudioMusik.getmTanggalSewa(),
                    pDataPersewaanStudioMusik.getmJamSewa(),
                    pDataPersewaanStudioMusik.getmBiayaPelunasan(),
                    pDataPersewaanStudioMusik.getmStatusPelunasan()
                });

        pDataPersewaanStudioMusik.setmKodeSewa(kode);

        hitungJamSelesai(pDataPersewaanStudioMusik);
    }

    public static List<DataPersewaanStudioMusik> getDataList() {
        DataSource dataSource = DatabaseConnection.getmDataSource();
        List<DataPersewaanStudioMusik> pegawaiList = new ArrayList<DataPersewaanStudioMusik>();

        String sql = "SELECT * FROM data_persewaan_studio_musik";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        pegawaiList = jdbcTemplate.query(sql, new DataPersewaanStudioMusikRowMapper());
        return pegawaiList;
    }

    public static List<DataPersewaanStudioMusik> getDataListByMonth(String pBulan, String pTahun) {
        DataSource dataSource = DatabaseConnection.getmDataSource();
        List<DataPersewaanStudioMusik> pegawaiList = new ArrayList<DataPersewaanStudioMusik>();

        String sql = "SELECT kode_sewa, kode_studio, nama_penyewa, nomor_telepon, to_char(tanggal_sewa, 'dd-FMMONTH-yyyy'), to_char(jam_sewa, 'HH24:MI'), to_char(jam_selesai, 'HH24:MI'), biaya_pelunasan, status_pelunasan "
                + "FROM data_persewaan_studio_musik "
                + "WHERE to_char(tanggal_sewa, 'mm-yyyy') = '" + pBulan + "-" + pTahun + "' ORDER BY kode_sewa ASC";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        pegawaiList = jdbcTemplate.query(sql, new DataPersewaanStudioMusikRowMapper());
        return pegawaiList;
    }
    
    public static List<DataPersewaanStudioMusik> getDataListByKode(String pKode) {
        DataSource dataSource = DatabaseConnection.getmDataSource();
        List<DataPersewaanStudioMusik> pegawaiList = new ArrayList<DataPersewaanStudioMusik>();

        String sql = "SELECT kode_sewa, kode_studio, nama_penyewa, nomor_telepon, to_char(tanggal_sewa, 'dd-FMMONTH-yyyy'), to_char(jam_sewa, 'HH24:MI'), to_char(jam_selesai, 'HH24:MI'), biaya_pelunasan, status_pelunasan "
                + "FROM data_persewaan_studio_musik "
                + "WHERE kode_sewa = ? ORDER BY kode_sewa ASC";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        pegawaiList = jdbcTemplate.query(sql, new DataPersewaanStudioMusikRowMapper(), pKode);
        return pegawaiList;
    }
    
    public static String hitungTotalPemasukan(String pBulan, String pTahun)
    {
        DataSource dataSource = DatabaseConnection.getmDataSource();

        String sql = "SELECT sum(biaya_pelunasan) FROM data_persewaan_studio_musik WHERE to_char(tanggal_sewa, 'mm-yyyy') = '" + pBulan + "-" + pTahun + "' ORDER BY kode_sewa ASC";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = jdbcTemplate.queryForObject(sql, String.class);
        return query;
    }

    public static boolean cekKetersediaanJadwal(DataPersewaanStudioMusik pDataPersewaanStudioMusik) {

        DataSource dataSource = DatabaseConnection.getmDataSource();
        List<DataPersewaanStudioMusik> pegawaiList = new ArrayList<DataPersewaanStudioMusik>();

        String jamSewa = pDataPersewaanStudioMusik.getmJamSewa();
        String durasi = String.valueOf(pDataPersewaanStudioMusik.getmDurasi());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT to_char((to_date(?, 'HH24:MI') + ? / 1440), 'HH24:MI') FROM dual";

        String jamSelesai = jdbcTemplate.queryForObject(sql, new Object[]{jamSewa, durasi}, String.class);

        sql = "SELECT * FROM DATA_PERSEWAAN_STUDIO_MUSIK WHERE "
                + "kode_studio = ? "
                + "AND tanggal_sewa = ? "
                + "AND ("
                + "to_char(jam_sewa,'HH24:MI') "
                + "BETWEEN ? AND ? "
                + "AND "
                + "(to_char(jam_selesai,'HH24:MI') > ? AND "
                + "to_char(jam_sewa,'HH24:MI') < ?)"
                + ")";

        System.out.println(sql);

        pegawaiList = jdbcTemplate.query(sql,
                new Object[]{
                    pDataPersewaanStudioMusik.getmKodeStudio(),
                    pDataPersewaanStudioMusik.getmTanggalSewa(),
                    pDataPersewaanStudioMusik.getmJamSewa(),
                    jamSelesai,
                    pDataPersewaanStudioMusik.getmJamSewa(),
                    jamSelesai
                },
                new DataPersewaanStudioMusikRowMapper());

        if (pegawaiList.isEmpty()) {
            return true;
        }
        
        return false;

    }
    
    public static void updateDataPelunasan(String pKode) {
        DataSource dataSource = DatabaseConnection.getmDataSource();

        String sql = "UPDATE data_persewaan_studio_musik SET "
                + "status_pelunasan = ? "
                + "WHERE kode_sewa = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql,
                new Object[]{
                    DataPersewaanStudioMusik.STATUS_PELUNASAN.LUNAS,
                    pKode
                });
    }

    public static void deleteData(String pKodeSewa) {
        DataSource dataSource = DatabaseConnection.getmDataSource();

        String sql = "DELETE FROM data_persewaan_studio_musik WHERE kode_sewa = " + pKodeSewa;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql);
    }

    public static class DataPersewaanStudioMusikRowMapper implements RowMapper<DataPersewaanStudioMusik> {

        @Override
        public DataPersewaanStudioMusik mapRow(ResultSet rs, int i) throws SQLException {
            DataPersewaanStudioMusikExtractor dataPersewaanStudioMusikExtractor = new DataPersewaanStudioMusikExtractor();
            return dataPersewaanStudioMusikExtractor.extractData(rs);
        }

        public static class DataPersewaanStudioMusikExtractor implements ResultSetExtractor<DataPersewaanStudioMusik> {

            @Override
            public DataPersewaanStudioMusik extractData(ResultSet rs) throws SQLException, DataAccessException {
                DataPersewaanStudioMusik dataPersewaanStudioMusik = new DataPersewaanStudioMusik();

                dataPersewaanStudioMusik.setmKodeSewa(rs.getString(1));
                dataPersewaanStudioMusik.setmKodeStudio(rs.getString(2));
                dataPersewaanStudioMusik.setmNamaPenyewa(rs.getString(3));
                dataPersewaanStudioMusik.setmNomorTeleponPenyewa(rs.getString(4));
                dataPersewaanStudioMusik.setmTanggalSewa(rs.getString(5));
                dataPersewaanStudioMusik.setmJamSewa(rs.getString(6));
                dataPersewaanStudioMusik.setmJamSelesai(rs.getString(7));
                dataPersewaanStudioMusik.setmBiayaPelunasan(Integer.parseInt(rs.getString(8)));
                dataPersewaanStudioMusik.setmStatusPelunasan(rs.getString(9));

                return dataPersewaanStudioMusik;
            }

        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.pegawai;

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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */
@Repository
public class PegawaiDAO implements IPegawaiDAO<Pegawai> {
    
    @Autowired
    private DataSource dataSource;

    @Override
    public String getNamaByUser(String pUsername) {
        String sql = "SELECT nama_pegawai FROM pegawai_studio_musik WHERE username_pegawai = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, String.class, pUsername);
    }
    
    /**
     * 1 : OPERATOR 2 : ADMIN
     */
    public static class ROLE {
        public static final String OPERATOR = "OPERATOR";
        public static final String OWNER = "OWNER";
    }

    @Override
    public List<Pegawai> getDataList() {
        List<Pegawai> pegawaiList = new ArrayList<Pegawai>();

        String sql = "SELECT * FROM pegawai_studio_musik";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        pegawaiList = jdbcTemplate.query(sql, new PegawaiRowMapper());
        return pegawaiList;
    }

    @Override
    public int validateLogin(String pUsername, String pPassword) {
        List<Pegawai> pegawaiList = new ArrayList<Pegawai>();

        String sql = "SELECT * FROM pegawai_studio_musik WHERE username_pegawai = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        pegawaiList = jdbcTemplate.query(sql, new Object[]{pUsername.toUpperCase()}, new PegawaiRowMapper());

        if (!pegawaiList.isEmpty()) {
            String username = pegawaiList.get(0).getmUsernamePegawai();
            String password = pegawaiList.get(0).getmPaswordPegawai();
            String role = pegawaiList.get(0).getmRolePegawai();
            if (pUsername.equalsIgnoreCase(username) && pPassword.equals(password)) {
                System.out.println("ROLE : " + role);
                if (role.equalsIgnoreCase(ROLE.OPERATOR)) {
                    return 2;
                } else if (role.equalsIgnoreCase(ROLE.OWNER)) {
                    return 3;
                }
            } else {
                System.out.println("WRONG USERNAME/PASSWORD/ROLE");
                return 1;
            }
        } else {
            System.out.println("UNREGISTERED USERNAME");
            return 0;
        }
        
        return -1;
    }
    
    public static class PegawaiRowMapper implements RowMapper<Pegawai> {

        @Override
        public Pegawai mapRow(ResultSet rs, int i) throws SQLException {
            PegawaiExtractor pegawaiExtractor = new PegawaiExtractor();
            return pegawaiExtractor.extractData(rs);
        }


    }

    public static class PegawaiExtractor implements ResultSetExtractor<Pegawai> {

        @Override
        public Pegawai extractData(ResultSet rs) throws SQLException, DataAccessException {
            Pegawai pegawai = new Pegawai();

            pegawai.setmKodePegawai(rs.getString(1));
            pegawai.setmUsernamePegawai(rs.getString(2));
            pegawai.setmPaswordPegawai(rs.getString(3));
            pegawai.setmNamaPegawai(rs.getString(4));
            pegawai.setmTempatTanggalLahir(rs.getString(5));
            pegawai.setmAlamatPegawai(rs.getString(6));
            pegawai.setmEmailPegawai(rs.getString(7));
            pegawai.setmNomorTelepon(rs.getString(8));
            pegawai.setmRolePegawai(rs.getString(9));

            return pegawai;
        }

    }
    
}

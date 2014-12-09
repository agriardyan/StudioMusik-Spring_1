/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rplt.studioMusik.studioMusik;

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
 * @author root
 */
@Repository
public class StudioMusikDAO implements IStudioMusikDAO<StudioMusik> {

    @Autowired
    private DataSource dataSource;

    @Override
    public void simpanData(StudioMusik pStudioMusik) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO studio_musik VALUES(?, ?, ?)";

        jdbcTemplate.update(sql,
                new Object[]{
                    pStudioMusik.getmKodeStudio(),
                    pStudioMusik.getmNamaStudio(),
                    pStudioMusik.getmTarifPerJam()
                });
    }

    @Override
    public void updateData(StudioMusik pStudioMusik) {

        String sql = "UPDATE studio_musik SET "
                + "nama_studio = ?, "
                + "tarif_per_jam = ? "
                + "WHERE kode_studio = ?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql,
                new Object[]{
                    pStudioMusik.getmNamaStudio(),
                    pStudioMusik.getmTarifPerJam(),
                    pStudioMusik.getmKodeStudio()
                });
    }

    @Override
    public void deleteData(String pKodeStudio) {

        String sql = "DELETE FROM studio_musik WHERE kode_studio = " + pKodeStudio;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql);
    }

    @Override
    public List<StudioMusik> getDataList() {
        List studioList = new ArrayList();

        String sql = "SELECT * FROM studio_musik";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        studioList = jdbcTemplate.query(sql, new StudioMusikRowMapper());
        return studioList;
    }

    @Override
    public String getNamaStudio(String pKode) {
        String namaStudio = "";

        String sql = "SELECT nama_studio FROM studio_musik WHERE kode_studio = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        namaStudio = jdbcTemplate.queryForObject(sql, new Object[]{pKode}, String.class);
        return namaStudio;
    }

    public static class StudioMusikRowMapper implements RowMapper<StudioMusik> {

        @Override
        public StudioMusik mapRow(ResultSet rs, int i) throws SQLException {
            StudioMusikExtractor studioMusikExtractor = new StudioMusikExtractor();
            return studioMusikExtractor.extractData(rs);
        }
    }

    public static class StudioMusikExtractor implements ResultSetExtractor<StudioMusik> {

        @Override
        public StudioMusik extractData(ResultSet rs) throws SQLException, DataAccessException {
            StudioMusik studioMusik = new StudioMusik();

            studioMusik.setmKodeStudio(rs.getString(1));
            studioMusik.setmNamaStudio(rs.getString(2));
            studioMusik.setmTarifPerJam(rs.getInt(3));

            return studioMusik;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.member;

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
public class MemberDAO implements IMemberDAO<Member> {
    
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Member> getDataList() {
        List<Member> memberList = new ArrayList<Member>();

        String sql = "SELECT * FROM member_studio_musik";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        memberList = jdbcTemplate.query(sql, new MemberRowMapper());
        return memberList;
    }

    @Override
    public int validateLogin(String pUsername, String pPassword) {
        List<Member> memberList = new ArrayList<Member>();

        String sql = "SELECT * FROM member_studio_musik WHERE username_member = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        memberList = jdbcTemplate.query(sql, new Object[] {pUsername.toUpperCase()}, new MemberRowMapper());

        if (!memberList.isEmpty()) {
            String username = memberList.get(0).getmUsernameMember();
            String password = memberList.get(0).getmPaswordMember();
            if (pUsername.equalsIgnoreCase(username) && pPassword.equals(password)) {
                return 2;
            } else {
                System.out.println("WRONG USERNAME/PASSWORD/ROLE");
                return 1;
            }
        } else {
            System.out.println("UNREGISTERED USERNAME");
            return 0;
        }
        
    }

    @Override
    public String getSaldo(String pUsername) {
        String saldo = null;

        String sql = "SELECT saldo_member FROM member_studio_musik WHERE username_member = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        saldo = jdbcTemplate.queryForObject(sql, new Object[]{pUsername}, String.class);
        return saldo;
    }
    
    public static class MemberRowMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int i) throws SQLException {
            MemberExtractor memberExtractor = new MemberExtractor();
            return memberExtractor.extractData(rs);
        }


    }

    public static class MemberExtractor implements ResultSetExtractor<Member> {

        @Override
        public Member extractData(ResultSet rs) throws SQLException, DataAccessException {
            Member member = new Member();

            member.setmKodeMember(rs.getString(1));
            member.setmUsernameMember(rs.getString(2));
            member.setmPaswordMember(rs.getString(3));
            member.setmNamaMember(rs.getString(4));
            member.setmTempatTanggalLahir(rs.getString(5));
            member.setmAlamatMember(rs.getString(6));
            member.setmEmailMember(rs.getString(7));
            member.setmNomorTelepon(rs.getString(8));
            member.setmSaldoMember(rs.getInt(9));

            return member;
        }

    }

    
    
}

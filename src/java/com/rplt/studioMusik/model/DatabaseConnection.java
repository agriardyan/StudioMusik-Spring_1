/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Agustinus Agri
 */
public class DatabaseConnection {

    public DatabaseConnection() {
    }

    public static DataSource getmDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();  
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");  
        dataSource.setUrl("jdbc:oracle:thin:@172.23.9.185:1521:orcl");
        dataSource.setUsername("mhs125314109");
        dataSource.setPassword("mhs125314109");
        
        return dataSource;
    }
    
    public static Connection getmConnection() {
        String jdbcURL = null;
        String username = null;
        String password = null;

        Connection conn = null;
        try {
            jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
//            jdbcURL = "jdbc:oracle:thin:@172.23.9.185:1521:orcl";
            username = "mhs125314109";
            password = "mhs125314109";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcURL, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
    
}

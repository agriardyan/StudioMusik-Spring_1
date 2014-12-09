/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.model;

import java.sql.Connection;
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
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("mhs125314109");
        dataSource.setPassword("mhs125314109");
        
        return dataSource;
    }
    
}

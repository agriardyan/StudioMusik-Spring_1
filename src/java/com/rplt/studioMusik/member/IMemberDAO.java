/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.member;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Agustinus Agri
 * @param <T>
 */
@Repository
public interface IMemberDAO<T> {
    
    public List<T> getDataList();
    public int validateLogin(String pUsername, String pPassword);
    public String getSaldo(String pUsername);
    
}

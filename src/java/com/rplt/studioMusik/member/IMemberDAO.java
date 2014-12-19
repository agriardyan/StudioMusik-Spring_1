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
    
    public String getGeneratedKodeMember();
    public void simpanData(T pT);
    public List<T> getDataList();
    public List<T> getDataListbyUser(String pUsername);
    public int validateLogin(String pUsername, String pPassword);
    public String getSaldo(String pUsername);
    public String getNamaByUser(String pUsername);
    public String getNoTelpByUser(String pUsername);
    public int simulateKurangSaldo(String pUsername, int pValue);
    public void updateTambahSaldo(String pUsername, int pValue);
    public void updateKurangSaldo(String pUsername, int pValue);
    public void updateDataMember(T pT);
    
    
}

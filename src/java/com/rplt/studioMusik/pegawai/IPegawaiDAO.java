/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rplt.studioMusik.pegawai;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */
@Repository
public interface IPegawaiDAO<T> {

    public List<T> getDataList();
    public String getNamaByUser(String pUsername);
    public int validateLogin(String pUsername, String pPassword);

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.studioMusik;

import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author root
 */
@Repository
public interface IStudioMusikDAO<T> {
    
    public void simpanData(T pT);
    public void updateData(T pT);
    public void deleteData(String pKode);
    public List<T> getDataList();
    public String getNamaStudio(String pKode);
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.studioMusik;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class StudioMusik implements Serializable {
    
    private String mKodeStudio;  
    private String mNamaStudio;  
    private int mTarifPerJam;  

    public StudioMusik() {
    }

    public String getmKodeStudio() {
        return mKodeStudio;
    }

    public void setmKodeStudio(String mKodeStudio) {
        this.mKodeStudio = mKodeStudio;
    }

    public String getmNamaStudio() {
        return mNamaStudio;
    }

    public void setmNamaStudio(String mNamaStudio) {
        this.mNamaStudio = mNamaStudio;
    }

    public int getmTarifPerJam() {
        return mTarifPerJam;
    }

    public void setmTarifPerJam(int mTarifPerJam) {
        this.mTarifPerJam = mTarifPerJam;
    }
    
    
}

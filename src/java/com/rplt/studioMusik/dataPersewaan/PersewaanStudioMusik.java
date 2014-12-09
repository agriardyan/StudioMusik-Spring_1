/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.dataPersewaan;

import java.io.Serializable;

/**
 *
 * @author Agustinus Agri
 */
public class PersewaanStudioMusik {
    
    private String mKodeSewa;
    private String mKodeStudio;
    private String mNamaPenyewa;
    private String mNomorTeleponPenyewa;
    private String mMulaiSewa;
    private String mSelesaiSewa;
    private String mJamMulaiSewa;
    private String mJamSelesaiSewa;
    private int mDurasi;
    private int mBiayaPelunasan;

    public PersewaanStudioMusik() {
    }

    public String getmKodeSewa() {
        return mKodeSewa;
    }

    public void setmKodeSewa(String mKodeSewa) {
        this.mKodeSewa = mKodeSewa;
    }

    public String getmKodeStudio() {
        return mKodeStudio;
    }

    public void setmKodeStudio(String mKodeStudio) {
        this.mKodeStudio = mKodeStudio;
    }

    public String getmNamaPenyewa() {
        return mNamaPenyewa;
    }

    public void setmNamaPenyewa(String mNamaPenyewa) {
        this.mNamaPenyewa = mNamaPenyewa;
    }

    public String getmNomorTeleponPenyewa() {
        return mNomorTeleponPenyewa;
    }

    public void setmNomorTeleponPenyewa(String mNomorTeleponPenyewa) {
        this.mNomorTeleponPenyewa = mNomorTeleponPenyewa;
    }

    public String getmMulaiSewa() {
        return mMulaiSewa;
    }

    public void setmMulaiSewa(String mMulaiSewa) {
        this.mMulaiSewa = mMulaiSewa;
    }

    public String getmSelesaiSewa() {
        return mSelesaiSewa;
    }

    public void setmSelesaiSewa(String mSelesaiSewa) {
        this.mSelesaiSewa = mSelesaiSewa;
    }

    public String getmJamMulaiSewa() {
        return mJamMulaiSewa;
    }

    public void setmJamMulaiSewa(String mJamMulaiSewa) {
        this.mJamMulaiSewa = mJamMulaiSewa;
    }

    public String getmJamSelesaiSewa() {
        return mJamSelesaiSewa;
    }

    public void setmJamSelesaiSewa(String mJamSelesaiSewa) {
        this.mJamSelesaiSewa = mJamSelesaiSewa;
    }

    public int getmDurasi() {
        return mDurasi;
    }

    public void setmDurasi(int mDurasi) {
        this.mDurasi = mDurasi;
    }

    public int getmBiayaPelunasan() {
        return mBiayaPelunasan;
    }

    public void setmBiayaPelunasan(int mBiayaPelunasan) {
        this.mBiayaPelunasan = mBiayaPelunasan;
    }

    @Override
    public String toString() {
        return "PersewaanStudioMusik{" + "mKodeSewa=" + mKodeSewa + ", mKodeStudio=" + mKodeStudio + ", mNamaPenyewa=" + mNamaPenyewa + ", mNomorTeleponPenyewa=" + mNomorTeleponPenyewa + ", mMulaiSewa=" + mMulaiSewa + ", mSelesaiSewa=" + mSelesaiSewa + ", mDurasi=" + mDurasi + ", mBiayaPelunasan=" + mBiayaPelunasan + '}';
    }

    
}

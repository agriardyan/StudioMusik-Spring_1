/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.pegawai;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class Pegawai implements Serializable {
    
    private String mKodePegawai;
    private String mUsernamePegawai;
    private String mPaswordPegawai;
    private String mNamaPegawai;
    private String mTempatTanggalLahir;
    private String mAlamatPegawai;
    private String mEmailPegawai;
    private String mNomorTelepon;
    private String mRolePegawai;

    public Pegawai() {
    }

    public String getmKodePegawai() {
        return mKodePegawai;
    }

    public void setmKodePegawai(String mKodePegawai) {
        this.mKodePegawai = mKodePegawai;
    }

    public String getmUsernamePegawai() {
        return mUsernamePegawai;
    }

    public void setmUsernamePegawai(String mUsernamePegawai) {
        this.mUsernamePegawai = mUsernamePegawai;
    }

    public String getmPaswordPegawai() {
        return mPaswordPegawai;
    }

    public void setmPaswordPegawai(String mPaswordPegawai) {
        this.mPaswordPegawai = mPaswordPegawai;
    }

    public String getmNamaPegawai() {
        return mNamaPegawai;
    }

    public void setmNamaPegawai(String mNamaPegawai) {
        this.mNamaPegawai = mNamaPegawai;
    }

    public String getmTempatTanggalLahir() {
        return mTempatTanggalLahir;
    }

    public void setmTempatTanggalLahir(String mTempatTanggalLahir) {
        this.mTempatTanggalLahir = mTempatTanggalLahir;
    }

    public String getmAlamatPegawai() {
        return mAlamatPegawai;
    }

    public void setmAlamatPegawai(String mAlamatPegawai) {
        this.mAlamatPegawai = mAlamatPegawai;
    }

    public String getmEmailPegawai() {
        return mEmailPegawai;
    }

    public void setmEmailPegawai(String mEmailPegawai) {
        this.mEmailPegawai = mEmailPegawai;
    }

    public String getmNomorTelepon() {
        return mNomorTelepon;
    }

    public void setmNomorTelepon(String mNomorTelepon) {
        this.mNomorTelepon = mNomorTelepon;
    }

    public String getmRolePegawai() {
        return mRolePegawai;
    }

    public void setmRolePegawai(String mRolePegawai) {
        this.mRolePegawai = mRolePegawai;
    }

    @Override
    public String toString() {
        return "Pegawai{" + "mKodePegawai=" + mKodePegawai + ", mUsernamePegawai=" + mUsernamePegawai + ", mPaswordPegawai=" + mPaswordPegawai + ", mNamaPegawai=" + mNamaPegawai + ", mTempatTanggalLahir=" + mTempatTanggalLahir + ", mAlamatPegawai=" + mAlamatPegawai + ", mEmailPegawai=" + mEmailPegawai + ", mNomorTelepon=" + mNomorTelepon + ", mRolePegawai=" + mRolePegawai + '}';
    }
    
    
    
}

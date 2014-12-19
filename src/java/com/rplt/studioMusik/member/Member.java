/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.member;

/**
 *
 * @author Agustinus Agri
 */
public class Member {
    
    private String mKodeMember;
    private String mUsernameMember;
    private String mPaswordMember;
    private String mNamaMember;
    private String mTempatTanggalLahir;
    private String mAlamatMember;
    private String mEmailMember;
    private String mNomorTelepon;
    private String mTempatLahirMember;
    private int mSaldoMember;

    public Member() {
    }

    public String getmKodeMember() {
        return mKodeMember;
    }

    public void setmKodeMember(String mKodeMember) {
        this.mKodeMember = mKodeMember;
    }

    public String getmUsernameMember() {
        return mUsernameMember;
    }

    public void setmUsernameMember(String mUsernameMember) {
        this.mUsernameMember = mUsernameMember;
    }

    public String getmPaswordMember() {
        return mPaswordMember;
    }

    public void setmPaswordMember(String mPaswordMember) {
        this.mPaswordMember = mPaswordMember;
    }

    public String getmNamaMember() {
        return mNamaMember;
    }

    public void setmNamaMember(String mNamaMember) {
        this.mNamaMember = mNamaMember;
    }

    public String getmTempatTanggalLahir() {
        return mTempatTanggalLahir;
    }

    public void setmTempatTanggalLahir(String mTempatTanggalLahir) {
        this.mTempatTanggalLahir = mTempatTanggalLahir;
    }

    public String getmAlamatMember() {
        return mAlamatMember;
    }

    public void setmAlamatMember(String mAlamatMember) {
        this.mAlamatMember = mAlamatMember;
    }

    public String getmEmailMember() {
        return mEmailMember;
    }

    public void setmEmailMember(String mEmailMember) {
        this.mEmailMember = mEmailMember;
    }

    public String getmNomorTelepon() {
        return mNomorTelepon;
    }

    public void setmNomorTelepon(String mNomorTelepon) {
        this.mNomorTelepon = mNomorTelepon;
    }

    public int getmSaldoMember() {
        return mSaldoMember;
    }

    public void setmSaldoMember(int mSaldoMember) {
        this.mSaldoMember = mSaldoMember;
    }

    public String getmTempatLahirMember() {
        return mTempatLahirMember;
    }

    public void setmTempatLahirMember(String mTempatLahirMember) {
        this.mTempatLahirMember = mTempatLahirMember;
    }

    @Override
    public String toString() {
        return "Member{" + "mKodeMember=" + mKodeMember + ", mUsernameMember=" + mUsernameMember + ", mPaswordMember=" + mPaswordMember + ", mNamaMember=" + mNamaMember + ", mTempatTanggalLahir=" + mTempatTanggalLahir + ", mAlamatMember=" + mAlamatMember + ", mEmailMember=" + mEmailMember + ", mNomorTelepon=" + mNomorTelepon + ", mSaldoMember=" + mSaldoMember + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.testPackage;

import com.rplt.studioMusik.model.DataPegawai;
import com.rplt.studioMusik.model.DataPersewaanStudioMusik;
import com.rplt.studioMusik.model.DatabaseConnection;
import com.rplt.studioMusik.model.StudioMusik;
import java.util.List;

/**
 *
 * @author root
 */
public class TestClass {
    
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        
        List<StudioMusik> dataList = StudioMusik.getDataList();
        
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println(dataList.get(i).getmNamaStudio());
        }
        
        //cek ketersediaan
        
        DataPersewaanStudioMusik dpsm = new DataPersewaanStudioMusik();
//        String generatedKodeSewa = dpsm.getGeneratedKodeSewa();
//        System.out.println(generatedKodeSewa);
        
//        simpanData();
        
//        getDataList();
//        
//        testLogin();
//        
        getDataListByMonth("11", "2014");
        
        dpsm.setmKodeStudio("103");
        dpsm.setmTanggalSewa("20-NOV-14");
        dpsm.setmJamSewa("14:00");
        dpsm.setmDurasi(60);
//        
        boolean testCekKetersediaanJadwal = testCekKetersediaanJadwal(dpsm);
        System.out.println(testCekKetersediaanJadwal);
    }
    
    public static boolean testCekKetersediaanJadwal(DataPersewaanStudioMusik pDataPersewaanStudioMusik)
    {
        return DataPersewaanStudioMusik.cekKetersediaanJadwal(pDataPersewaanStudioMusik);
    }
    
    public static void simpanData()
    {
        DataPersewaanStudioMusik dpsm = new DataPersewaanStudioMusik();
        dpsm.setmKodeStudio("101");
        dpsm.setmNamaPenyewa("MISTER BRO");
        dpsm.setmNomorTeleponPenyewa("085648612365");
        dpsm.setmTanggalSewa("21-NOV-14");
        dpsm.setmJamSewa("18:00");
        dpsm.setmDurasi(60);
        dpsm.setmBiayaPelunasan(30000);
        dpsm.setmStatusPelunasan("BOOKING");
        
        DataPersewaanStudioMusik.simpanData(dpsm);
        
        System.out.println("sukses menyimpan");
        
    }
    
    public static void getDataList()
    {
        List<DataPersewaanStudioMusik> dataList = DataPersewaanStudioMusik.getDataList();
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println(dataList.get(i).getmKodeSewa() + "\t" + dataList.get(i).getmNamaPenyewa());
        }           
    }
    
    public static void getDataListByMonth(String bulan, String tahun)
    {
        List<DataPersewaanStudioMusik> dataList = DataPersewaanStudioMusik.getDataListByMonth(bulan, tahun);
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println(dataList.get(i).getmKodeSewa() + "\t" + dataList.get(i).getmNamaPenyewa());
        }   
    }
    
    public static void testLogin()
    {
        int login = DataPegawai.validateLoginCredential("P003", "P003", "operator");
        System.out.println(login);
    }
    
}

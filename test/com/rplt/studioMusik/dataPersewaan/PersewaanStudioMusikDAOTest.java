/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rplt.studioMusik.dataPersewaan;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author root
 */
public class PersewaanStudioMusikDAOTest {
    
    public PersewaanStudioMusikDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of simpanData method, of class PersewaanStudioMusikDAO.
     */
    @Test
    public void testSimpanData() {
        System.out.println("simpanData");
        PersewaanStudioMusik pPersewaanStudioMusik = null;
        PersewaanStudioMusikDAO instance = new PersewaanStudioMusikDAO();
        instance.simpanData(pPersewaanStudioMusik);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cekKetersediaanJadwal method, of class PersewaanStudioMusikDAO.
     */
    @Test
    public void testCekKetersediaanJadwal() {
        System.out.println("cekKetersediaanJadwal");
        PersewaanStudioMusik pPersewaanStudioMusik = null;
        PersewaanStudioMusikDAO instance = new PersewaanStudioMusikDAO();
        boolean expResult = false;
        boolean result = instance.cekKetersediaanJadwal(pPersewaanStudioMusik);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hitungBiayaSewa method, of class PersewaanStudioMusikDAO.
     */
    @Test
    public void testHitungBiayaSewa() {
        System.out.println("hitungBiayaSewa");
        int pDurasi = 0;
        String pKodeStudio = "";
        PersewaanStudioMusikDAO instance = new PersewaanStudioMusikDAO();
        String expResult = "";
        String result = instance.hitungBiayaSewa(pDurasi, pKodeStudio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeneratedKodeSewa method, of class PersewaanStudioMusikDAO.
     */
    @Test
    public void testGetGeneratedKodeSewa() {
        System.out.println("getGeneratedKodeSewa");
        PersewaanStudioMusikDAO instance = new PersewaanStudioMusikDAO();
        String expResult = "";
        String result = instance.getGeneratedKodeSewa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hitungJamSelesai method, of class PersewaanStudioMusikDAO.
     */
    @Test
    public void testHitungJamSelesai() {
        System.out.println("hitungJamSelesai");
        PersewaanStudioMusik pPersewaanStudioMusik = null;
        PersewaanStudioMusikDAO instance = new PersewaanStudioMusikDAO();
//        instance.hitungJamSelesai(pPersewaanStudioMusik);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataListByMonth method, of class PersewaanStudioMusikDAO.
     */
    @Test
    public void testGetDataListByMonth() {
        System.out.println("getDataListByMonth");
        String pBulan = "";
        String pTahun = "";
        PersewaanStudioMusikDAO instance = new PersewaanStudioMusikDAO();
        List<PersewaanStudioMusik> expResult = null;
        List<PersewaanStudioMusik> result = instance.getDataListByMonth(pBulan, pTahun);
//        assertEquals(expResult, result);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}

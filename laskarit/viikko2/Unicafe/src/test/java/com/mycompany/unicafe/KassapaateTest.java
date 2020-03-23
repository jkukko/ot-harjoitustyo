package com.mycompany.unicafe;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void luotuKassapaateOnOlemassa() {
         assertTrue(paate!=null);
    }
    
    @Test
    public void kassassaOikeaMaaraRahaa() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void laskeeOikeinEdullisetLounaat() {
        paate.syoEdullisesti(250);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }

    @Test
    public void laskeeOikeinMaukkatLounaat() {
        paate.syoMaukkaasti(400);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiPalauttaaOikeanSumman() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaRahanJosLiianVahan() {
        assertEquals(50, paate.syoMaukkaasti(50));
    }

    @Test
    public void syoEdullisestiPalauttaaOikeanSumman() {
        assertEquals(100, paate.syoEdullisesti(340));
    }
    
    @Test
    public void syoEdullisestiPalauttaaRahanJosLiianVahan() {
        assertEquals(50, paate.syoEdullisesti(50));
    }
    
    @Test
    public void syoEdullisestiOnnistuuKunMaksetaanKortilla() {
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoEdullisestiEiOnnistuKunMaksetaanKortilla() {
        kortti = new Maksukortti(100);
        assertEquals(false, paate.syoEdullisesti(kortti));
    }

    @Test
    public void syoMaukkaastiOnnistuuKunMaksetaanKortilla() {
        assertEquals(true, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void syoMaukkaastiEiOnnistuKunMaksetaanKortilla() {
        kortti = new Maksukortti(100);
        assertEquals(false, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void rahanLataaminenKortilleOnnistuu() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
    }

    @Test
    public void rahanLataaminenKortilleEiOnnistuu() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }

}

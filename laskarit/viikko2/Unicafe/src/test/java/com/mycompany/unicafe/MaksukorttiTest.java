package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test 
    public void saldoAlussaOikein(){
        String vastaus=kortti.toString();
        assertEquals("saldo: 0.10", vastaus);
    }
    
    @Test
    public void lataaminenKasvattaaSaldoa(){
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeKunRahaaTarpeeksi(){
        kortti.otaRahaa(8);
        assertEquals("saldo: 0.2", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuKunRahaaEiTarpeeksi(){
        kortti.otaRahaa(100);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoRiittaa(){
        assertEquals(true, kortti.otaRahaa(10));
        
    }
    
    @Test
    public void saldoEiRiita(){
        assertEquals(false, kortti.otaRahaa(100));
        
    }

}

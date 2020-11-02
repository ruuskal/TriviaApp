package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
       
    @Before
    public void setUp() {
        kassa=new Kassapaate();
        kortti=new Maksukortti(500);
    }


    @Test
    public void kassaOlemassa(){
        assertTrue(kassa!=null);
    }
    
    @Test
    public void alussaRahaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void alussaMaukkaitaOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void alussaEdullisiaOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKasvattaaKassaaKunRahaRiittaa() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaKassaaKunRahaRiittaa() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiEiKasvataKunRahaEiRiittaa() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiEiKasvataKunRahaEiRiittaa() {
        kassa.syoMaukkaasti(399);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiPalauttaaOikein() {
        assertEquals(60, kassa.syoEdullisesti(300));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaOikein() {
        assertEquals(200, kassa.syoMaukkaasti(600));
    }
    
    //oletetaan maksukortti toimivaksi tästä eteenpäin
    @Test
    public void veloitetaanEdullinenKunKortillaRiittavastiRahaa(){
        boolean vastaus = kassa.syoEdullisesti(kortti);
        assertEquals("saldo: 2.60", kortti.toString());
        assertEquals(true, vastaus);
    }
    
    @Test
    public void veloitetaanMaukasKunKortillaRiittavastiRahaa(){
        boolean vastaus=kassa.syoMaukkaasti(kortti);
        assertEquals("saldo: 1.0", kortti.toString());
        assertEquals(true, vastaus);
    }
    
    @Test
    public void edullisetLisaantyyKunRahaRiittaaKortilla(){
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaatLisaantyyKunRahaRiittaaKortilla(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaEdullisetEiMuutu(){
        kortti.otaRahaa(300);
        boolean vastaus=kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(200, kortti.saldo());
        assertEquals(false, vastaus);
    }
    
    @Test
    public void kortillaEiTarpeeksiRahaaMaukkaatEiMuutu(){
        kortti.otaRahaa(300);
        boolean vastaus=kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(200, kortti.saldo());
        assertEquals(false, vastaus);
    }
    
    @Test
    public void kassanRahatEiMuutuKoritllaEdullisiaOstettaessa(){
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassanRahatEiMuutuKoritllaMaukkaitaOstettaessa(){
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void latausKasvattaaKassanRahaa(){
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
        
    }
    
    @Test
    public void latausMuuttaaKortinSaldoa(){
        kassa.lataaRahaaKortille(kortti, 200);
        assertEquals(700, kortti.saldo());
    }
    
    @Test
    public void negatiivistaSummaaEiVoiLadata(){
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
}

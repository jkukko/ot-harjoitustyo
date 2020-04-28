/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManagement.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author kukkojoo
 */
public class ProductTest {
    
    Product tuote;
    
    @Before
    public void setUp() {
        this.tuote = new Product("Maito");
    }
    
    @Test
    public void konstruktoriAsettaaTuotteelleOikeanNimen() {
        assertEquals("Maito", this.tuote.getName());
    }
    
    @Test
    public void tuoteToStringTulostaaOikein() {
        assertEquals("Maito", this.tuote.toString());
    }
    
    @Test
    public void tuotteenNimiVoidaanAntaa() {
        this.tuote.setName("Normimaito");
        assertEquals("Normimaito", this.tuote.getName());
    }
    
    
}

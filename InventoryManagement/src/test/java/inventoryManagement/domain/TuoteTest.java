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
public class TuoteTest {
    
    Tuote tuote;
    
    @Before
    public void setUp() {
        this.tuote = new Tuote(1, "Maito");
    }
    
    @Test
    public void konstruktoriAsettaaTuotteelleOikeanNimen() {
        assertEquals("Maito", this.tuote.getNimi());
    }
    
    public TuoteTest() {
    }
    
}

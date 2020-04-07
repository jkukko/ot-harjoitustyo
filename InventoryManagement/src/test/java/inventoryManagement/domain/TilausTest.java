package inventoryManagement.domain;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TilausTest {
    
    Tilaus tilaus;
    
    @Before
    public void setUp() {
        this.tilaus = new Tilaus(new Tuote("Maito"), new Date(), true, 10);
    }
    
    @Test
    public void konstruktoriAntaaTuotteelleOikeanBoolean() {
        assertEquals(true, this.tilaus.isSisaanTuleva());
    }
}

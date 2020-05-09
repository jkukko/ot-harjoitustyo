package inventoryManagement.domain;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class OrderTest {
    
    Order tilaus;
    
    @Before
    public void setUp() {
        this.tilaus = new Order(0, new Product("Maito"), "01-01-2020", true, 10);
    }
    
    @Test
    public void konstruktoriAntaaTuotteelleOikeanBoolean() {
        assertEquals(true, this.tilaus.isIsIncomingOrder());
    }
}

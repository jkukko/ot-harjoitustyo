package inventoryManagement.domain;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventoryTest {
    
    Inventory varasto;
    
   
    @Before
    public void setUp() {
        this.varasto = new Inventory();
    }
    
    @Test
    public void sisaanTulevaTilausKirjataanVarastoon() {
        Product tuote = new Product("Maito");
        Order tilaus = new Order(tuote, new Date(2020, 3, 26), true, 10);
        this.varasto.addOrder(tilaus);
        assertEquals(1, this.varasto.getOrders().size());
    }
    
    @Test
    public void kuluvaVaratoLaskentaTehdaanOikein() {
        Product tuote = new Product("Maito");
        Order sisaanTulevaTilaus1 = new Order(tuote, new Date(2020, 3, 26), true, 10);
        Order sisaanTulevaTilaus2 = new Order(tuote, new Date(2020, 3, 26), true, 10);
        Order ulosTulevaTilaus1 = new Order(tuote, new Date(2020, 3, 26), false, 5);
        Order ulosTulevaTilaus2 = new Order(tuote, new Date(2020, 3, 26), false, 3);
        varasto.addOrder(sisaanTulevaTilaus1);
        varasto.addOrder(sisaanTulevaTilaus2);
        varasto.addOrder(ulosTulevaTilaus1);
        varasto.addOrder(ulosTulevaTilaus2);
        assertEquals(12, this.varasto.getProductCurrentAmount(tuote));
        
    }
    
}

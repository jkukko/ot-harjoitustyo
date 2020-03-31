package inventoryManagement.domain;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {
    
    Varasto varasto;
    
   
    @Before
    public void setUp() {
        this.varasto = new Varasto();
    }
    
    @Test
    public void sisaanTulevaTilausKirjataanVarastoon() {
        Tuote tuote = new Tuote(1, "Maito");
        Tilaus tilaus = new Tilaus(tuote, new Date(2020, 3, 26), true, 10);
        this.varasto.kirjaaTilaus(tilaus);
        assertEquals(1, this.varasto.getTilaukset().size());
    }
    
    @Test
    public void kuluvaVaratoLaskentaTehdaanOikein() {
        Tuote tuote = new Tuote(1, "Maito");
        Tilaus sisaanTulevaTilaus1 = new Tilaus(tuote, new Date(2020, 3, 26), true, 10);
        Tilaus sisaanTulevaTilaus2 = new Tilaus(tuote, new Date(2020, 3, 26), true, 10);
        Tilaus ulosTulevaTilaus1 = new Tilaus(tuote, new Date(2020, 3, 26), false, 5);
        Tilaus ulosTulevaTilaus2 = new Tilaus(tuote, new Date(2020, 3, 26), false, 3);
        varasto.kirjaaTilaus(sisaanTulevaTilaus1);
        varasto.kirjaaTilaus(sisaanTulevaTilaus2);
        varasto.kirjaaTilaus(ulosTulevaTilaus1);
        varasto.kirjaaTilaus(ulosTulevaTilaus2);
        assertEquals(12, this.varasto.getTuotteenKuluvaMaara(tuote));
        
    }
    
}

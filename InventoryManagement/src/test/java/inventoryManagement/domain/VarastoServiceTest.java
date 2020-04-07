package inventoryManagement.domain;

import inventoryManagement.dao.ArrayListTilausDao;
import inventoryManagement.dao.ArrayListTuoteDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class VarastoServiceTest {
    
    VarastoService varastoService;
    
    @Before
    public void setUp() {
        this.varastoService = new VarastoService(new ArrayListTilausDao(), new ArrayListTuoteDao());
    }
    
    @Test
    public void varastoonVoiKirjataTuotteita() {
        this.varastoService.kirjaaTilaus("Maito", 10);
        assertEquals(1, this.varastoService.palautaTuotteidenLKM());
    }
    
    @Test
    public void lataaHistoriaOikeastiLataaHistorian() {
        this.varastoService.lataaHistoria();
        assertEquals(2, this.varastoService.palautaTuotteidenLKM());
    }
    
    
}

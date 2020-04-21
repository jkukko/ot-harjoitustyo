package inventoryManagement.domain;

import inventoryManagement.dao.ArrayListTilausDao;
import inventoryManagement.dao.ArrayListTuoteDao;
import java.util.ArrayList;
import java.util.List;
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
    
    @Test
    public void otaVarastotaMaaraMikaSiellaOn() {
        this.varastoService.kirjaaTilaus("Banaani", 10);
        assertEquals(5, this.varastoService.otaVarastosta("Banaani", 5));
    }
    
    @Test
    public void yritetaanOttaaTuoteVarastostaMitaEiOleSiella() {
        assertEquals(0, this.varastoService.otaVarastosta("Banaani", 5));
    }
    
    @Test
    public void palauttaaListanTuoteidenNimista() {
        this.varastoService.kirjaaTilaus("Banaani", 5);
        this.varastoService.kirjaaTilaus("Maito", 3);
        List<String> lista = new ArrayList<>();
        lista.add("Maito");
        lista.add("Banaani");
        assertEquals(lista, this.varastoService.listaTuoteNimista());
    }
    
    @Test
    public void palauttaaTuotteetJotkaAlleRajan() {
        this.varastoService.kirjaaTilaus("Maito", 5);
        this.varastoService.changeSafetyStock("Maito", 10);

        assertEquals(1, this.varastoService.palautaTuotteetJotkaAlleRajan().size());
    }
}

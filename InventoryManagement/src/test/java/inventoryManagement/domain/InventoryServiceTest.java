package inventoryManagement.domain;

import inventoryManagement.dao.ArrayListOrderDao;
import inventoryManagement.dao.ArrayListProductDao;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class InventoryServiceTest {
    
    InventoryService varastoService;
    
    @Before
    public void setUp() {
        this.varastoService = new InventoryService(new ArrayListOrderDao(), new ArrayListProductDao());
    }
    
    @Test
    public void varastoonVoiKirjataTuotteita() {
        this.varastoService.incomingOrder("Maito", 10);
        assertEquals(1, this.varastoService.getCountOfProductsInInventory());
    }
    
    @Test
    public void lataaHistoriaOikeastiLataaHistorian() {
        this.varastoService.loadHistory();
        assertEquals(2, this.varastoService.getCountOfProductsInInventory());
    }
    
    @Test
    public void otaVarastotaMaaraMikaSiellaOn() {
        this.varastoService.incomingOrder("Banaani", 10);
        assertEquals(5, this.varastoService.outGoingOrder("Banaani", 5));
    }
    
    @Test
    public void yritetaanOttaaTuoteVarastostaMitaEiOleSiella() {
        assertEquals(0, this.varastoService.outGoingOrder("Banaani", 5));
    }
    
    @Test
    public void palauttaaListanTuoteidenNimista() {
        this.varastoService.incomingOrder("Banaani", 5);
        this.varastoService.incomingOrder("Maito", 3);
        List<String> lista = new ArrayList<>();
        lista.add("Maito");
        lista.add("Banaani");
        assertEquals(lista, this.varastoService.getListOfProductNames());
    }
    
    @Test
    public void palauttaaTuotteetJotkaAlleRajan() {
        this.varastoService.incomingOrder("Maito", 5);
        this.varastoService.changeSafetyStock("Maito", 10);

        assertEquals(1, this.varastoService.getProductsThatAreUnderLimit().size());
    }
}

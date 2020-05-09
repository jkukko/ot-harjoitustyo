package inventoryManagement.domain;


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
        FakeProductDao productDao = new FakeProductDao();
        FakeUserDao userDao = new FakeUserDao();
        FakeOrderDao orderDao = new FakeOrderDao();
        
        this.varastoService = new InventoryService(orderDao, productDao, userDao);
    }
    
    @Test
    public void varastoonVoiKirjataTuotteita() {
        this.varastoService.incomingOrder("Maito", 10);
        assertEquals(1, this.varastoService.getProducts().size());
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
        lista.add("Banaani");
        lista.add("Maito");
        assertEquals(lista, this.varastoService.getListOfProductNames());
    }
    
    @Test
    public void createNewUser() {
        this.varastoService.create("test", "test");
        assertEquals(true, this.varastoService.checkUsername("test"));
    }
    
    @Test
    public void loginWorks() {
        this.varastoService.create("test", "test");
        assertEquals(true, this.varastoService.login("test", "test"));
    }
    
}

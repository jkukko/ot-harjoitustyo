/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManagement.dao;

import inventoryManagement.domain.Product;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;


/**
 *
 * @author kukkojoo
 */
public class FileProductDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File productFile;
    ProductDao dao;
    
    @Before
    public void setUp() throws Exception {
        this.productFile = this.testFolder.newFile("testfile_products.csv");
        try (FileWriter file = new FileWriter(this.productFile.getAbsolutePath())) {
            file.write("a,5,20\n");
        }
        this.dao = new FileProductDao(productFile.getAbsolutePath());
    }

    @Test
    public void productIsInFile() {
        assertEquals("a", this.dao.findByName("a").getName());
    }
    
    @Test
    public void canChangeSafetyStockLimit() {
        this.dao.changeSafetyLimit("a", 10);
        String value = Integer.toString(this.dao.getSafetyStoct("a"));
        assertEquals("10", Integer.toString(this.dao.getSafetyStoct("a")));
    }

    @Test
    public void canCurrentStock() {
        this.dao.changeCurrentStock("a", 100);
        String value = Integer.toString(this.dao.findByName("a").getCurrentStock());
        assertEquals("100", Integer.toString(this.dao.findByName("a").getCurrentStock()));
    }
    
    @Test
    public void cannotFindProductThatDoNotExist() {
        assertEquals(null, this.dao.findByName("b"));
    }
    
    @Test
    public void canCreateAProduct() {
       Product product = new Product("b");
       this.dao.create(product);
       assertEquals(product, this.dao.findByName("b"));
    }
    
    @Test
    public void doNotCreateNewProductIfItExist() {
        Product product = new Product("b");
        this.dao.create(product);
        assertEquals(product, this.dao.findByName("b"));
    }
    
}

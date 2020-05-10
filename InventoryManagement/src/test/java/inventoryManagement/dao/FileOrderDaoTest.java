/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManagement.dao;

import inventoryManagement.domain.FakeProductDao;
import inventoryManagement.domain.Order;
import inventoryManagement.domain.Product;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author kukkojoo
 */
public class FileOrderDaoTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File orderFile;
    OrderDao orderDao;
    ProductDao productDao;

    
    @Before
    public void setUp() throws Exception {
        this.orderFile = this.testFolder.newFile("testfile_orders.csv");
        this.productDao = new FakeProductDao();
        this.productDao.create(new Product("a"));
        try (FileWriter file = new FileWriter(this.orderFile.getAbsolutePath())) {
            file.write("1,a,2020-05-10,1,10\n");
        }
        
        this.orderDao = new FileOrderDao(this.orderFile.getAbsolutePath(), productDao);
    }
    
    @Test
    public void canCreateOrder() {
        this.productDao.create(new Product("b"));
        Product product = new Product("b");
        this.orderDao.create(new Order(2, product, "2020-05-10", true, 10));
        assertEquals(product, this.orderDao.findByTuoteName("b").get(0).getProduct());
    }

    
}

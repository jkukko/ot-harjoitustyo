package inventoryManagement.domain;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import inventoryManagement.dao.ProductDao;
import inventoryManagement.dao.OrderDao;
import inventoryManagement.dao.UserDao;
/**
 * 
 * This class is responsible of application logic
 */

public class InventoryService {
    
    private OrderDao orderDao;
    private ProductDao productDao;
    private UserDao userdao;
    private SimpleDateFormat format;
    private int id;
    
    public InventoryService(OrderDao tilausDao, ProductDao tuoteDao, UserDao userDao) {
        this.orderDao = tilausDao;
        this.productDao = tuoteDao;
        this.userdao = userDao;
        this.format = new SimpleDateFormat("yyyy-MM-dd");
        this.id = this.orderDao.lstId() + 1;
        
    }
    
    /**
     * Adding incoming order to Inventory
     * @param name  product name
     * @param amount amount of product in order
     * @return order
     */
    
    public Order incomingOrder(String name, int amount) {
        Date date = new Date();
        if (this.productDao.findByName(name) == null) {
            this.productDao.create(new Product(name));
        }
        Product product = this.productDao.findByName(name);
        int currentAmount = product.getCurrentStock();
        this.productDao.changeCurrentStock(name, currentAmount + amount);
        Order order = orderDao.create(new Order(this.id, product, format.format(date), true, amount));
        this.id++;
        return order;
    }
    
    /**
     * taking product from inventory
     * @param name product name
     * @param amount amount of product in order
     * @return 
     */

    public int outGoingOrder(String name, int amount) {
        Date date = new Date();
        
        if (this.productDao.findByName(name) == null) {
            return 0;
        }
        
        Product product = this.productDao.findByName(name);
        int currentAmount = product.getCurrentStock();
        if (currentAmount < amount) {
            Order ulosMenevaTilaus = new Order(this.id, product, this.format.format(date), false, currentAmount);
            this.orderDao.create(ulosMenevaTilaus);
            this.id++;
            this.productDao.changeCurrentStock(name, 0);
            this.productDao.changeCurrentStock(name, 0);
            return currentAmount;
        }
        Order order = new Order(this.id, product, this.format.format(date), false, amount);
        orderDao.create(order);
        this.id++;
        this.productDao.changeCurrentStock(name, currentAmount - amount);
        this.productDao.changeCurrentStock(name, currentAmount - amount);
        return amount;
           
    }

    /**
     * Return all Product names as a list
     * @return List of product names as string List
     */
    
    public List<String> getListOfProductNames() {
        List<String> productNames = new ArrayList<>();
        List<Product> products = this.productDao.getAll();
        for (Product product : products) {
            productNames.add(product.getName());
        }
        return productNames;
    }

    /**
     * Return all Products that are saved
     * @return List of products
     */
    
    public List<Product> getProducts() {
        return this.productDao.getAll();
    }
    
    /**
     * Return specific current safety stock level
     * @param name   Product name
     * @return current safety stock level
     */
    
    public int getSafetyStockLimit(String name) {
        return this.productDao.getSafetyStoct(name);
    }
    
    /**
     * Change specific safety stock level
     * @param name  Product name
     * @param amount  new safety stock level
     */
    
    public void changeSafetyStock(String name, int amount) {
        this.productDao.changeSafetyLimit(name, amount);
    }
      
    /**
     * Login into Inventory Management by using specific username and password
     * @param username usersname
     * @param password password
     * @return if username and password are allready create, then returns true else false
     */
    
    public Boolean login(String username, String password) {
        return this.userdao.login(username, password);
    }
    
    /**
     * Create new user
     * @param username  username
     * @param password  password
     */
    
    public void create(String username, String password) {
        this.userdao.create(new User(username, password));
    }
    
    /**
     * 
     * @param username  username
     * @return if this username is already save to system then returns true else false
     */
    
    public Boolean checkUsername(String username) {
        return this.userdao.checkUsername(username);
    }
    
    public List<Order> getListOfOrderByProductName(String name) {
        return this.orderDao.findByTuoteName(name);
    }
}

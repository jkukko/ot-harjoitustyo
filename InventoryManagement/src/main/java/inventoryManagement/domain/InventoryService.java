package inventoryManagement.domain;

import java.io.File;
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
    private HashMap<Product, Integer> currentSituation;
    private SimpleDateFormat format;
    private int id;
    
    public InventoryService(OrderDao tilausDao, ProductDao tuoteDao, UserDao userDao) {
        this.orderDao = tilausDao;
        this.productDao = tuoteDao;
        this.userdao = userDao;
        this.currentSituation = new HashMap<>();
        this.format = new SimpleDateFormat("yyyy-MM-dd");
        this.id = this.orderDao.lstId();
        
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
     * incoming order in specific day
     * @param name product name
     * @param amount amount of product in order
     * @param date specific registration date
     * @return 
     */
    
    private Order incomingOrderSpecificDay(String name, int amount, Date date) {
        Date specificDay = date;
        if (this.productDao.findByName(name) == null) {
            this.productDao.create(new Product(name));
        }
        Product product = this.productDao.findByName(name);
        int currentAmount = product.getCurrentStock();
        this.productDao.changeCurrentStock(name, currentAmount+ amount);
        Order order = orderDao.create(new Order(this.id, product, format.format(specificDay), true, amount));
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
     * taking product in specific date
     * @param name product name
     * @param amount amount of product in order
     * @param date spefic registration date
     * @return 
     */
    
    public int outGoingOrderSpecificDay(String name, int amount, Date date) {
        Date specificDay = date;
        if (this.productDao.findByName(name) == null) {
            return 0;
        }
        Product product = this.productDao.findByName(name);
        int currentAmount = product.getCurrentStock();
        if (currentAmount < amount) {
            Order order = new Order(this.id, product, this.format.format(specificDay), false, currentAmount);
            orderDao.create(order);
            this.id++;
            this.productDao.changeCurrentStock(name, 0);
            return currentAmount;
        }
        Order order = new Order(this.id, product, this.format.format(specificDay), false, amount);
        orderDao.create(order);
        this.id++;
        this.productDao.changeCurrentStock(name, currentAmount - amount);
        return amount;           
    }    
    
    /**
     * Print current inventory: product name and amount
     */
    
    public void printCurrentInventory() {
        for (Map.Entry<Product, Integer> entry : currentSituation.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " " + value);
            
        }
    }
    
    /**
     * Print orders of specific product
     * @param name product name
     */
    
    public void printProductOrders(String name) {
        List<Order> orders = new ArrayList<>();
        orders = this.orderDao.findByTuoteName(name);
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }
    
    public void loadHistory() {
        
        try (Scanner scanner = new Scanner(Paths.get("history.csv"))) {
            
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] palat = row.split(",");
                Boolean b = Boolean.parseBoolean(palat[4]);
                int amount = Integer.parseInt(palat[5]);
                int year = Integer.parseInt(palat[1]) - 1900;
                int month = Integer.parseInt(palat[2]) - 1;
                int day = Integer.parseInt(palat[3]);
                Date date = new Date(year, month, day);
                if (b == true) {
                    incomingOrderSpecificDay(palat[0], amount, date);
                } else {
                    outGoingOrderSpecificDay(palat[0], amount, date);
                }
                    
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
    
    public int getCountOfProductsInInventory() {
        return this.productDao.getAll().size();
    }
    
    public List<Product> getProductsThatAreUnderLimit() {
        List<Product> list = new ArrayList<>();
        List<Product> products = this.productDao.getAll();
        for (int i = 0; i < products.size(); i++) {
            
            if (products.get(i).getDifference() <= 0) {
                list.add(products.get(i));
            }
            
        }
        return list;
    }

    public HashMap<Product, Integer> getCurrentSituation() {
        return currentSituation;
    }
    
    public List<String> getListOfProductNames() {
        List<String> productNames = new ArrayList<>();
        List<Product> products = this.productDao.getAll();
        for (Product product : products) {
            productNames.add(product.getName());
        }
        return productNames;
    }
    
    public List<Product> getProducts() {
        return this.productDao.getAll();
    }
    
    public int getSafetyStockLimit(String name) {
        return this.productDao.getSafetyStoct(name);
    }
    
    public void changeSafetyStock(String name, int amount) {
        this.productDao.changeSafetyLimit(name, amount);
    }
/*    
    public ObservableList<XYChart.Data<Date, Integer>> dataForVisualisation(String name) {
        List<Order> orders = this.orderDao.findByTuoteName(name);
        ObservableList<XYChart.Data<Date, Integer>> values = FXCollections.observableArrayList();
        
        for (int i = 0; i < orders.size(); i++) {
            Order t = orders.get(i);
            
            if (t.isIsIncomingOrder() == true) {
                values.add(new XYChart.Data<Date, Integer>(t.getDate(), t.getAmount()));
            }
            
        }
        
        return values;
    }
*/    
    
    public void visualizeOrder(String productName) {
        System.out.println(productName);
        List<Order> orders = this.orderDao.findByTuoteName(productName);
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).toString());
        }
    }
    
    public Boolean login(String username, String password) {
        return this.userdao.login(username, password);
    }
    
    public void create(String username, String password) {
        this.userdao.create(new User(username, password));
    }
    
    public Boolean checkUsername(String username) {
        return this.userdao.checkUsername(username);
    }
}

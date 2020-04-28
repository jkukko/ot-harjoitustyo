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


public class InventoryService {
    
    private OrderDao orderDao;
    private ProductDao productDao;
    private HashMap<Product, Integer> currentSituation;
    
    public InventoryService(OrderDao tilausDao, ProductDao tuoteDao) {
        this.orderDao = tilausDao;
        this.productDao = tuoteDao;
        this.currentSituation = new HashMap<>();
    }
    
    public Order incomingOrder(String name, int amount) {
        Date date = new Date();
        if (this.productDao.findByName(name) == null) {
            this.productDao.create(new Product(name));
        }
        
        Product product = this.productDao.findByName(name);
        
        if (this.currentSituation.containsKey(product)) {
            int currentAmount = this.currentSituation.get(product);
            this.currentSituation.put(product, currentAmount + amount);
            this.productDao.changeCurrentStock(name, currentAmount + amount);
        } else {
            this.currentSituation.put(product, amount);
            this.productDao.changeCurrentStock(name, amount);
        }
        
        return orderDao.create(new Order(product, date, true, amount));
    }
    
    private Order incomingOrderSpecificDay(String name, int amount, Date date) {
        Date specificDay = date;
        if (this.productDao.findByName(name) == null) {
            this.productDao.create(new Product(name));
        }
        
        Product product = this.productDao.findByName(name);
        
        if (this.currentSituation.containsKey(product)) {
            int currentAmount = this.currentSituation.get(product);
            this.currentSituation.put(product, currentAmount + amount);
            this.productDao.changeCurrentStock(name, currentAmount + amount);
        } else {
            this.currentSituation.put(product, amount);
            this.productDao.changeCurrentStock(name, amount);
        }
        
        return orderDao.create(new Order(product, specificDay, true, amount));
    }


    public int outGoingOrder(String name, int amount) {
        Date date = new Date();
        
        if (this.productDao.findByName(name) == null) {
            return 0;
        }
        
        Product product = this.productDao.findByName(name);
        int currentAmount = this.currentSituation.get(product);
        if (currentAmount < amount) {
            Order ulosMenevaTilaus = new Order(product, date, false, currentAmount);
            orderDao.create(ulosMenevaTilaus);
            this.currentSituation.put(product, 0);
            this.productDao.changeCurrentStock(name, 0);
            return currentAmount;
        }
        Order order = new Order(product, date, false, amount);
        orderDao.create(order);
        this.currentSituation.put(product, currentAmount - amount);
        this.productDao.changeCurrentStock(name, currentAmount - amount);
        return amount;
           
    }
    
    public int outGoingOrderSpecificDay(String name, int amount, Date date) {
        Date specificDay = date;
        
        if (this.productDao.findByName(name) == null) {
            return 0;
        }
        
        Product product = this.productDao.findByName(name);
        int currentAmount = this.currentSituation.get(product);
        if (currentAmount < amount) {
            Order order = new Order(product, specificDay, false, currentAmount);
            orderDao.create(order);
            this.currentSituation.put(product, 0);
            this.productDao.changeCurrentStock(name, 0);
            return currentAmount;
        }
        Order order = new Order(product, specificDay, false, amount);
        orderDao.create(order);
        this.currentSituation.put(product, currentAmount - amount);
        this.productDao.changeCurrentStock(name, currentAmount - amount);
        return amount;
           
    }    
    
    public void printCurrentInventory() {
        for (Map.Entry<Product, Integer> entry : currentSituation.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " " + value);
            
        }
    }
    
    public void printProductOrders(String name) {
        List<Order> orders = this.orderDao.findByTuoteName(name);
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
        List<String> product = new ArrayList<>();
        
        for (Map.Entry<Product, Integer> entry : currentSituation.entrySet()) {
            Product key = entry.getKey();
            product.add(key.getName());
        }
        
        return product;
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
    
}

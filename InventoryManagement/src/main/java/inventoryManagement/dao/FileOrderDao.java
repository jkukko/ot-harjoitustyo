/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManagement.dao;

import inventoryManagement.domain.Order;
import inventoryManagement.domain.Product;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kukkojoo
 */
public class FileOrderDao implements OrderDao {
    private List<Order> orders;
    private ProductDao fileProductDao;
    private String file;
    
    public FileOrderDao(String file, ProductDao fileProductDao) {
        this.orders = new ArrayList<>();
        this.file = file;
        this.fileProductDao = fileProductDao;
        try {
            Scanner scanner = new Scanner(new File(this.file));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                Product product = this.fileProductDao.findByName(parts[1]);
                String date = parts[2];
                Boolean b = Boolean.parseBoolean(parts[3]);
                int amount = Integer.parseInt(parts[4]);
                int id = Integer.parseInt(parts[0]);
                Order order = new Order(id, product, date, b, amount);
                this.orders.add(order);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Order create(Order order) {
        this.orders.add(order);
        save();
        return order;
    }

    @Override
    public List<Order> findByTuoteName(String name) {
        List<Order> ordersByProductName = new ArrayList<>();
        for (int i = 0; i < this.orders.size(); i++) {
            
            if (this.orders.get(i).getProduct().getName().equals(name)) {
                ordersByProductName.add(this.orders.get(i));
            }
        }
        return ordersByProductName;
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    private void save() {
        try (FileWriter writer = new FileWriter(new File(this.file))) {
            for (Order order : this.orders) {
                writer.write(
                        order.getId() + "," +
                        order.getProduct().getName() + "," + 
                        order.getDate() + "," +
                        order.isIsIncomingOrder() + "," +
                        order.getAmount() + "\n"
                    );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int lstId() {
        if (this.orders.size()==0) {
            return 0;
        }
        int id = this.orders.get(this.orders.size()-1).getId();
        return id;
    }
    
}

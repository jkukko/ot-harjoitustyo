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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author kukkojoo
 */
public class FileOrderDao implements OrderDao {
    private List<Order> orders;
    private FileProductDao fileProductDao;
    private String file;
    
    public FileOrderDao(String file, FileProductDao fileProductDao) {
        this.orders = new ArrayList<>();
        this.file = file;
        this.fileProductDao = fileProductDao;
        try {
            Scanner scanner = new Scanner(new File(this.file));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                Product product = this.fileProductDao.findByName(parts[0]);
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(parts[1]);
                Boolean b = Boolean.parseBoolean(parts[2]);
                int amount = Integer.parseInt(parts[3]);
                Order order = new Order(product, date, b, amount);
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
        return this.orders;
    }

    @Override
    public List<Order> getAll() {
        return this.orders;
    }

    private void save() {
        try (FileWriter writer = new FileWriter(new File(this.file))) {
            for (Order order : this.orders) {
                writer.write(
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
    
}

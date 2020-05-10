/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventoryManagement.dao;

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
public class FileProductDao implements ProductDao {
    private List<Product> products;
    private String file;
    
    
    public FileProductDao(String file) {
        this.products = new ArrayList<>();
        this.file = file;
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                Product product = new Product(
                        parts[0], 
                        Integer.parseInt(parts[1]), 
                        Integer.parseInt(parts[2])
                );
                this.products.add(product);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    /**
     * Can create new product aka save it
     * @param product 
     * @return product
     */

    @Override
    public Product create(Product product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
            save();
        }
        return product;        
    }
    
    /**
     * Find product by product name
     * @param name product
     * @return product
     */

    @Override
    public Product findByName(String name) {
        try {
            for (int i = 0; i < this.products.size(); i++) {
                if (this.products.get(i).getName().equals(name)) {
                    return this.products.get(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    /**
     * Return all products
     * @return List of products
     */

    @Override
    public List<Product> getAll() {
        return this.products;
    }
    
    /**
     * Change safetystock by a specific product
     * @param name product name
     * @param amount safety stock amount
     * @return product
     */

    @Override
    public Product changeSafetyLimit(String name, int amount) {
        Product product = this.findByName(name);
        product.setSafetyAmmount(amount);
        product.setDifference(product.getCurrentStock() - amount);
        save();
        return product;        
    }
    
    /**
     * Return safety stocl by specific product
     * @param name product
     * @return int
     */

    @Override
    public Integer getSafetyStoct(String name) {
        Product t = this.findByName(name);
        return t.getSafetyAmmount();
    }
    
    /**
     * Change current stock a specific product
     * @param name product
     * @param amount
     * @return product
     */

    @Override
    public Product changeCurrentStock(String name, int amount) {
        Product t = findByName(name);
        t.setCurrentStock(amount);
        t.setDifference(amount - t.getSafetyAmmount());
        save();
        return t;
    }

    private void save() {
        try (FileWriter writer = new FileWriter(new File(this.file))) {
            for (Product product : products) {
                writer.write(
                        product.getName() + "," +
                        product.getSafetyAmmount() + "," + 
                        product.getCurrentStock() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}

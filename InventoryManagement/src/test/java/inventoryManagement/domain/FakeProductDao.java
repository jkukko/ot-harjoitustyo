package inventoryManagement.domain;

import inventoryManagement.dao.ProductDao;
import java.util.ArrayList;
import java.util.List;

public class FakeProductDao implements ProductDao {
    List<Product> products;
    
    public FakeProductDao() {
        this.products = new ArrayList<>();
    }

    @Override
    public Product create(Product product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
        }
        return product; 
    }

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
    

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public Product changeSafetyLimit(String name, int amount) {
        Product product = this.findByName(name);
        product.setSafetyAmmount(amount);
        product.setDifference(product.getCurrentStock() - amount);
        return product;        
    }

    @Override
    public Integer getSafetyStoct(String name) {
        Product product = this.findByName(name);
        return product.getSafetyAmmount();
    }

    @Override
    public Product changeCurrentStock(String name, int amount) {
        Product product = findByName(name);
        product.setCurrentStock(amount);
        product.setDifference(amount - product.getSafetyAmmount());
        return product;

    }
    
}

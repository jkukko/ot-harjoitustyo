package inventoryManagement.dao;

import inventoryManagement.domain.Product;
import java.util.ArrayList;
import java.util.List;

public class ArrayListProductDao implements ProductDao {
    
    private List<Product> products;
    
    public ArrayListProductDao() {
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
        for (int i = 0; i < this.products.size(); i++) {
            
            if (this.products.get(i).getName().equals(name)) {
                return this.products.get(i);
            }
            
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        return this.products;
    }

    @Override
    public Product changeSafetyLimit(String name, int amount) {
        Product t = this.findByName(name);
        t.setSafetyAmmount(amount);
        t.setDifference(t.getCurrentStock() - amount);
        return t;
    }

    @Override
    public Integer getSafetyStoct(String name) {
        Product t = this.findByName(name);
        return t.getSafetyAmmount();
    }

    @Override
    public Product changeCurrentStock(String name, int amount) {
        Product t = findByName(name);
        t.setCurrentStock(amount);
        t.setDifference(amount - t.getSafetyAmmount());
        return t;
    }
    
}

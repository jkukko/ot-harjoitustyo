package inventoryManagement.dao;

import inventoryManagement.domain.Product;
import java.util.List;

public interface ProductDao {
    
    Product create(Product tuote);
    
    Product findByName(String name);
    
    List<Product> getAll();
    
    Product changeSafetyLimit(String name, int amount);
    
    Integer getSafetyStoct(String name);
    
    Product changeCurrentStock(String name, int amount);
    
}

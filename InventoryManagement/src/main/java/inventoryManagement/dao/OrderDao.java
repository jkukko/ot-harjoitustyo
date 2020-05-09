package inventoryManagement.dao;

import inventoryManagement.domain.Order;
import java.util.List;

public interface OrderDao {
    
    Order create(Order order);
    
    List<Order> findByTuoteName(String name);
    
    List<Order> getAll();
    
    int lstId();
    
}

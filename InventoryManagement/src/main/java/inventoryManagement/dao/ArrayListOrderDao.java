package inventoryManagement.dao;

import inventoryManagement.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class ArrayListOrderDao implements OrderDao {
    private List<Order> orders;
    
    public ArrayListOrderDao() {
        this.orders = new ArrayList<>();
    }
    
    @Override
    public Order create(Order order) {
        this.orders.add(order);
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

    @Override
    public int lstId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

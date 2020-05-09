package inventoryManagement.domain;

import inventoryManagement.dao.OrderDao;
import java.util.ArrayList;
import java.util.List;

public class FakeOrderDao implements OrderDao {
    List<Order> orders;
    
    public FakeOrderDao() {
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
        if (this.orders.size()==0) {
            return 0;
        }
        int id = this.orders.get(this.orders.size()-1).getId();
        return id;
    }
    
}

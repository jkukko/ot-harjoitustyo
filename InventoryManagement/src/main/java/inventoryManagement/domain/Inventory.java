package inventoryManagement.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private ArrayList<Order> orders;
    private Map<Product, Integer> currentSituation;
   
    public Inventory() {
        this.orders = new ArrayList<>();
        this.currentSituation = new HashMap<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Map<Product, Integer> getCurrentSituation() {
        return currentSituation;
    }
     
    public void addOrder(Order t) {
        // Jos tilaus on ulosmenevä ja se suurempi kuin varstossa oleva maara
        if (t.isIsIncomingOrder() == false &&
                this.currentSituation.get(t.getProduct()) < t.getAmount()) {
            // tähän lisätään myöhemmin error message 
            return;
        }
        this.orders.add(t);
        updateCurrentSituation(t);
        
    }
    
    private void updateCurrentSituation(Order t) {
        
        if (t.isIsIncomingOrder() == true) {    
            if (this.currentSituation.containsKey(t.getProduct())) {
                int newAmount = this.currentSituation.get(t.getProduct()) + t.getAmount();
                this.currentSituation.put(t.getProduct(), newAmount);
            } else {
                this.currentSituation.put(t.getProduct(), t.getAmount());
            }
        } else {
            int newAmount = this.currentSituation.get(t.getProduct()) - t.getAmount();
            this.currentSituation.put(t.getProduct(), newAmount);
        }
   
    }
    
    public void printCurrentInventory() {
        for (Map.Entry<Product, Integer> entry : currentSituation.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key.getName() + ", " + value);
        }
    }
    
    public int getProductCurrentAmount(Product t) {
        return this.currentSituation.get(t);
    }
    
    
}

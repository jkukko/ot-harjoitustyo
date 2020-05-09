package inventoryManagement.domain;

import java.util.Date;


public class Order {
    private int id;
    private Product product;
    private String date;
    private boolean isIncomingOrder;
    private Integer amount;
    
    public Order(int id, Product product, String date, boolean isIncomingOrder, int amount) {
        this.id = id;
        this.product = product;
        this.date = date;
        this.isIncomingOrder = isIncomingOrder;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public String getDate() {
        return date;
    }

    public boolean isIsIncomingOrder() {
        return isIncomingOrder;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsIncomingOrder(boolean isIncomingOrder) {
        this.isIncomingOrder = isIncomingOrder;
    }

    @Override
    public String toString() {
        return "Tilaus{" + "id=" + id + "tuote=" + product + ", paiva=" + date + ", sisaanTuleva=" + isIncomingOrder + ", maara=" + amount + '}';
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
  
}

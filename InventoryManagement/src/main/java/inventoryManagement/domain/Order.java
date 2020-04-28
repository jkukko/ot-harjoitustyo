package inventoryManagement.domain;

import java.util.Date;


public class Order {
    private Product product;
    private Date date;
    private boolean isIncomingOrder;
    private Integer amount;
    
    public Order(Product product, Date date, boolean isIncomingOrder, int amount) {
        this.product = product;
        this.date = date;
        this.isIncomingOrder = isIncomingOrder;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public Date getDate() {
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIsIncomingOrder(boolean isIncomingOrder) {
        this.isIncomingOrder = isIncomingOrder;
    }

    @Override
    public String toString() {
        return "Tilaus{" + "tuote=" + product + ", paiva=" + date + ", sisaanTuleva=" + isIncomingOrder + ", maara=" + amount + '}';
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
  
}

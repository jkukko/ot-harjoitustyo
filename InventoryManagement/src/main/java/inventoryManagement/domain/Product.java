package inventoryManagement.domain;

public class Product {
    private String name;
    private int safetyAmmount;
    private int currentStock;
    private int difference;
    
    public Product(String name) {
        this.name = name;
        this.safetyAmmount = 0;
        this.currentStock = 0;
        this.difference = 0;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public String getName() {
        return name;
    }

    public int getSafetyAmmount() {
        return safetyAmmount;
    }

    public void setSafetyAmmount(int safetyAmmount) {
        this.safetyAmmount = safetyAmmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}

package inventoryManagement.domain;

public class Tuote {
    private String nimi;
    private int safetyAmmount;
    private int currentStock;
    private int difference;
    
    public Tuote(String nimi) {
        this.nimi = nimi;
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


    public String getNimi() {
        return nimi;
    }

    public int getSafetyAmmount() {
        return safetyAmmount;
    }

    public void setSafetyAmmount(int safetyAmmount) {
        this.safetyAmmount = safetyAmmount;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public String toString() {
        return this.nimi;
    }
    
}

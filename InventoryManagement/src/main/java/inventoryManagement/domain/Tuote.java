package inventoryManagement.domain;

public class Tuote {
    private String nimi;
    private int safetyAmmount;
    
    public Tuote(String nimi) {
        this.nimi = nimi;
        safetyAmmount = 0;
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

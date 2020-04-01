package inventoryManagement.domain;

public class Tuote {
    private String nimi;
    
    public Tuote(String nimi) {
        this.nimi = nimi;
    }


    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    @Override
    public String toString() {
        return this.nimi;
    }
    
}

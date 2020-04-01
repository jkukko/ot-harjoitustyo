package inventoryManagement.domain;

import java.util.Date;


public class Tilaus {
    private Tuote tuote;
    private Date paiva;
    private boolean sisaanTuleva;
    private Integer maara;
    
    public Tilaus(Tuote tuote, Date paiva, boolean sisaanTuleva, int maara) {
        this.tuote = tuote;
        this.paiva = paiva;
        this.sisaanTuleva = sisaanTuleva;
        this.maara = maara;
    }

    public Tuote getTuote() {
        return tuote;
    }

    public Date getPaiva() {
        return paiva;
    }

    public boolean isSisaanTuleva() {
        return sisaanTuleva;
    }

    public Integer getMaara() {
        return maara;
    }

    public void setTuote(Tuote tuote) {
        this.tuote = tuote;
    }

    public void setPaiva(Date paiva) {
        this.paiva = paiva;
    }

    public void setSisaanTuleva(boolean sisaanTuleva) {
        this.sisaanTuleva = sisaanTuleva;
    }

    @Override
    public String toString() {
        return "Tilaus{" + "tuote=" + tuote + ", paiva=" + paiva + ", sisaanTuleva=" + sisaanTuleva + ", maara=" + maara + '}';
    }

    public void setMaara(Integer maara) {
        this.maara = maara;
    }
  
}

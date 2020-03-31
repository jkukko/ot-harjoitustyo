package inventoryManagement.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Varasto {
    private ArrayList<Tilaus> tilaukset;
    private Map<Tuote, Integer> kuluvaTilanne;
   
    public Varasto() {
        this.tilaukset = new ArrayList<>();
        this.kuluvaTilanne = new HashMap<>();
    }

    public List<Tilaus> getTilaukset() {
        return tilaukset;
    }

    public Map<Tuote, Integer> getKuluvaTilanne() {
        return kuluvaTilanne;
    }
     
    public void kirjaaTilaus(Tilaus t) {
        // Jos tilaus on ulosmenevä ja se suurempi kuin varstossa oleva maara
        if (t.isSisaanTuleva() == false &&
                this.kuluvaTilanne.get(t.getTuote()) < t.getMaara()) {
            // tähän lisätään myöhemmin error message 
            return;
        }
        this.tilaukset.add(t);
        paivitaKuluvaTilanne(t);
        
    }
    
    private void paivitaKuluvaTilanne(Tilaus t) {
        
        if (t.isSisaanTuleva() == true) {    
            if (this.kuluvaTilanne.containsKey(t.getTuote())) {
                int uusiMaara = this.kuluvaTilanne.get(t.getTuote()) + t.getMaara();
                this.kuluvaTilanne.put(t.getTuote(), uusiMaara);
            } else {
                this.kuluvaTilanne.put(t.getTuote(), t.getMaara());
            }
        } else {
            int uusiMaara = this.kuluvaTilanne.get(t.getTuote()) - t.getMaara();
            this.kuluvaTilanne.put(t.getTuote(), uusiMaara);
        }
   
    }
    
    public void tulostaNykyinenVarasto() {
        for (Map.Entry<Tuote, Integer> entry : kuluvaTilanne.entrySet()) {
            Tuote key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key.getNimi() + ", " + value);
        }
    }
    
    public int getTuotteenKuluvaMaara(Tuote t) {
        int luku = this.kuluvaTilanne.get(t);
        return this.kuluvaTilanne.get(t);
    }
    
    
}
